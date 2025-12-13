package Game;

import Controllers.LVinputController;
import Controllers.LVMovementController;
import Entities.Dragon;
import Entities.Hero;
import Entities.Monster;
import Enums.Direction;
import Factories.ArmorFactory;
import Factories.DragonFactory;
import Factories.ExoskeletonFactory;
import Factories.PaladinFactory;
import Factories.SorcererFactory;
import Factories.SpiritFactory;
import Factories.WarriorFactory;
import Factories.WeaponFactory;
import Interfaces.Positionable;
import Interfaces.HasHero;
import Interfaces.HasMonster;
import Interfaces.HasSpawnPosition;
import Seeders.EntitySeeder;
import Utility.Stats;
import WorldSets.Maps.Arena;
import WorldSets.Space;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Legends of Valor main game loop.
 * This is a lightweight implementation that focuses on the core lane rules,
 * movement, teleport, and recall. Combat and shopping can be layered on later.
 */
public class LegendsOfValor extends GameController {

    private static final int ARENA_ROWS = 8;
    private static final int ARENA_COLS = 8;

    private final GameUI ui = new GameUI();
    private final Arena arena = new Arena(ARENA_ROWS, ARENA_COLS);

    private final List<Hero> warriors = new ArrayList<>();
    private final List<Hero> paladins = new ArrayList<>();
    private final List<Hero> sorcerers = new ArrayList<>();
    private final List<Monster> monsterTemplates = new ArrayList<>();

    private final List<UnitToken> heroTokens = new ArrayList<>();
    private final List<UnitToken> monsterTokens = new ArrayList<>();

    private int round = 1;
    private int spawnFrequency = 4;

    private static class UnitToken implements Positionable, HasHero, HasMonster, HasSpawnPosition {
        private final Hero hero;
        private final Monster monster;
        private final int spawnRow;
        private final int spawnCol;
        private int row;
        private int col;

        UnitToken(Hero hero, int row, int col) {
            this.hero = hero;
            this.monster = null;
            this.row = row;
            this.col = col;
            this.spawnRow = row;
            this.spawnCol = col;
        }

        UnitToken(Monster monster, int row, int col) {
            this.hero = null;
            this.monster = monster;
            this.row = row;
            this.col = col;
            this.spawnRow = row;
            this.spawnCol = col;
        }

        public boolean isHero() {
            return hero != null;
        }

        public Hero getHero() {
            return hero;
        }

        public Monster getMonster() {
            return monster;
        }

        @Override
        public Hero getHeroEntity() {
            return hero;
        }

        @Override
        public Monster getMonsterEntity() {
            return monster;
        }

        @Override
        public int getSpawnRow() {
            return spawnRow;
        }

        @Override
        public int getSpawnCol() {
            return spawnCol;
        }

        @Override
        public int getRow() {
            return row;
        }

        @Override
        public int getCol() {
            return col;
        }

        @Override
        public void setPosition(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    @Override
    public void startGame() {
        introduceGame();
        loadGameData();
        selectDifficulty();
        chooseHeroesAndLanes();
        spawnInitialMonsters();
        gameLoop();
    }

    @Override
    protected void gameLoop() {
        boolean gameOver = false;

        while (!gameOver) {
            System.out.println(renderBoard());
            System.out.printf("---- Round %d ----%n", round);

            gameOver = heroesTurn();
            if (gameOver) {
                break;
            }

            endOfRoundRecovery();
            respawnFallenHeroes();
            spawnNewMonstersIfNeeded();
            round++;
        }

        System.out.println("Game over");
    }

    private void selectDifficulty() {
        System.out.println("Select difficulty: 1) Easy 2) Medium 3) Hard");
        int choice = ui.askInt();
        switch (choice) {
            case 1:
                spawnFrequency = 6;
                break;
            case 3:
                spawnFrequency = 2;
                break;
            default:
                spawnFrequency = 4;
                break;
        }
    }

    private void chooseHeroesAndLanes() {
        List<Hero> pool = new ArrayList<>();
        pool.addAll(warriors);
        pool.addAll(paladins);
        pool.addAll(sorcerers);

        boolean[] laneTaken = new boolean[]{false, false, false};

        for (int pick = 0; pick < 3; pick++) {
            System.out.println("Choose 3 heroes (hero# lane#). Lanes: 1=Top, 2=Mid, 3=Bottom.");
            for (int i = 0; i < pool.size(); i++) {
                Hero hero = pool.get(i);
                System.out.printf("%d) %s%n", i + 1, hero.getName());
            }

            System.out.printf("Select hero %d and lane (e.g., 2 1): ", pick + 1);
            int heroIndex = ui.askInt() - 1;
            int lane = ui.askInt() - 1;

            if (heroIndex < 0 || heroIndex >= pool.size()) {
                System.out.println("Invalid hero selection, try again.");
                pick--;
                continue;
            }

            if (lane < 0 || lane > 2) {
                System.out.println("Invalid lane number, try again.");
                pick--;
                continue;
            }

            if (laneTaken[lane]) {
                System.out.println("Lane already has a hero. Pick another lane.");
                pick--;
                continue;
            }

            Hero chosen = pool.remove(heroIndex);
            laneTaken[lane] = true;

            int col = laneToColumn(lane);
            int row = ARENA_ROWS - 1; // hero nexus row
            UnitToken token = new UnitToken(chosen, row, col);
            heroTokens.add(token);
        }
    }

    private void spawnInitialMonsters() {
        for (int lane = 0; lane < 3; lane++) {
            Monster monster = createMonsterForLane();
            int row = 0; // monster nexus row
            int col = laneToColumn(lane);
            monsterTokens.add(new UnitToken(monster, row, col));
        }
    }

    private boolean heroesTurn() {
        for (UnitToken heroToken : heroTokens) {
            if (heroToken.getHero().getStats().getHealth() <= 0) {
                continue; // waits for respawn
            }

            LVMovementController movementController = new LVMovementController(
                    arena,
                    heroToken,
                    heroTokens,
                    monsterTokens,
                    true
            );

            LVinputController inputController = new LVinputController(ui, movementController, heroTokens);

            boolean turnDone = false;
            while (!turnDone) {
                System.out.printf("Hero turn: %s at (%d,%d)%n",
                        heroToken.getHero().getName(), heroToken.getRow(), heroToken.getCol());
                inputController.printValidCommands();
                String command = inputController.getInput();
                LVinputController.TurnStatus status = inputController.handleCommand(command);
                System.out.println(renderBoard());

                if (status == LVinputController.TurnStatus.QUIT) {
                    return true;
                }
                if (status == LVinputController.TurnStatus.CONSUMED) {
                    turnDone = true;
                } else {
                    System.out.println("Try a different action.");
                }
            }

            if (heroReachedMonsterNexus()) {
                System.out.println("Heroes reached the monster nexus. You win!");
                return true;
            }

            monstersTurn();
            if (monsterReachedHeroNexus()) {
                System.out.println("A monster reached the hero nexus. Monsters win!");
                return true;
            }
        }
        return false;
    }

    private void monstersTurn() {
        for (UnitToken monsterToken : new ArrayList<UnitToken>(monsterTokens)) {
            LVMovementController movementController = new LVMovementController(
                    arena,
                    monsterToken,
                    heroTokens,
                    monsterTokens,
                    false
            );

            // If a hero is adjacent, pause movement to simulate an attack opportunity
            UnitToken adjacentHero = findAdjacentHero(monsterToken);
            if (adjacentHero != null) {
                System.out.printf("%s engages %s near (%d,%d)%n",
                        monsterToken.getMonster().getName(),
                        adjacentHero.getHero().getName(),
                        monsterToken.getRow(),
                        monsterToken.getCol());
                continue;
            }

            movementController.move(Enums.Direction.DOWN);
        }
    }

    private UnitToken findAdjacentHero(UnitToken monsterToken) {
        for (UnitToken hero : heroTokens) {
            if (hero.getHero().getStats().getHealth() <= 0) {
                continue;
            }
            int dr = Math.abs(hero.getRow() - monsterToken.getRow());
            int dc = Math.abs(hero.getCol() - monsterToken.getCol());
            if (dr + dc == 1) {
                return hero;
            }
        }
        return null;
    }

    private void endOfRoundRecovery() {
        for (UnitToken heroToken : heroTokens) {
            Stats stats = heroToken.getHero().getStats();
            if (stats.getHealth() <= 0) {
                continue;
            }
            int heal = (int) Math.max(1, Math.floor(stats.getMax_health() * 0.10));
            int mana = (int) Math.max(1, Math.floor(stats.getMax_mana() * 0.10));
            stats.setHealth(stats.getHealth() + heal);
            stats.setMana(stats.getMana() + mana);
        }
    }

    private void respawnFallenHeroes() {
        for (UnitToken heroToken : heroTokens) {
            Stats stats = heroToken.getHero().getStats();
            if (stats.getHealth() > 0) {
                continue;
            }

            stats.setHealth(stats.getMax_health());
            stats.setMana(stats.getMax_mana());
            heroToken.setPosition(heroToken.getSpawnRow(), heroToken.getSpawnCol());
            System.out.printf("%s has respawned at their nexus.%n", heroToken.getHero().getName());
        }
    }

    private void spawnNewMonstersIfNeeded() {
        if (round == 0 || round % spawnFrequency != 0) {
            return;
        }

        for (int lane = 0; lane < 3; lane++) {
            int row = 0;
            int col = laneToColumn(lane);
            if (isMonsterOccupied(row, col)) {
                continue;
            }
            Monster monster = createMonsterForLane();
            UnitToken token = new UnitToken(monster, row, col);
            monsterTokens.add(token);
            System.out.printf("A new %s spawns in lane %d.%n", monster.getName(), lane + 1);
        }
    }

    private boolean heroReachedMonsterNexus() {
        for (UnitToken heroToken : heroTokens) {
            if (heroToken.getRow() == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean monsterReachedHeroNexus() {
        for (UnitToken monsterToken : monsterTokens) {
            if (monsterToken.getRow() == ARENA_ROWS - 1) {
                return true;
            }
        }
        return false;
    }

    private String renderBoard() {
        return arena.render(heroTokens, monsterTokens);
    }

    private boolean inBounds(int row, int col) {
        return row >= 0 && row < ARENA_ROWS && col >= 0 && col < ARENA_COLS;
    }

    private boolean isMonsterOccupied(int row, int col) {
        for (UnitToken token : monsterTokens) {
            if (token.getRow() == row && token.getCol() == col) {
                return true;
            }
        }
        return false;
    }

    private Monster createMonsterForLane() {
        if (monsterTemplates.isEmpty()) {
            // fallback stub
            return new Dragon("DefaultDragon", 100, 50, 0.2, 0.1, 1);
        }
        Monster template = monsterTemplates.get(new Random().nextInt(monsterTemplates.size()));
        Monster monster = template.copy();

        int avgHeroLevel = averageHeroLevel();
        monster.getLevelObj().setCurrentLevel(avgHeroLevel);
        monster.rescaleStatsForLevel();
        return monster;
    }

    private int averageHeroLevel() {
        int total = 0;
        for (UnitToken heroToken : heroTokens) {
            total += heroToken.getHero().getLevelObj().getCurrentLevel();
        }
        return heroTokens.isEmpty() ? 1 : Math.max(1, total / heroTokens.size());
    }

    private int laneToColumn(int laneIndex) {
        switch (laneIndex) {
            case 0:
                return 0;
            case 1:
                return 3;
            default:
                return 6;
        }
    }

    @Override
    protected void introduceGame() {
        System.out.println("Welcome to Legends of Valor!");
        System.out.println("Guide your three heroes up the lanes and reach the monster nexus.");
    }

    @Override
    protected void loadGameData() {
        EntitySeeder seeder = new EntitySeeder(
                new WarriorFactory(),
                new PaladinFactory(),
                new SorcererFactory(),
                new DragonFactory(),
                new ExoskeletonFactory(),
                new SpiritFactory(),
                new WeaponFactory(),
                new ArmorFactory()
        );

        warriors.addAll(seeder.seedWarriors("src/TextFiles/warriors.txt"));
        paladins.addAll(seeder.seedPaladins("src/TextFiles/Paladins.txt"));
        sorcerers.addAll(seeder.seedSorcerers("src/TextFiles/Sorcerers.txt"));

        monsterTemplates.addAll(seeder.seedDragons("src/TextFiles/Dragons.txt"));
        monsterTemplates.addAll(seeder.seedExoSkeletons("src/TextFiles/Exoskeletons.txt"));
        monsterTemplates.addAll(seeder.seedSpirits("src/TextFiles/Spirits.txt"));

        // Shuffle to give variety in the spawn order
        Collections.shuffle(monsterTemplates);
    }

    @Override
    public String getName() {
        return "Legends of Valor";
    }
}
