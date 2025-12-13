package Game;

import Controllers.LVinputController;
import Controllers.LVMovementController;
import Entities.Hero;
import Entities.Monster;
import Entities.MonsterPool;
import Factories.ArmorFactory;
import Factories.DragonFactory;
import Factories.ExoskeletonFactory;
import Factories.PaladinFactory;
import Factories.SorcererFactory;
import Factories.SpiritFactory;
import Factories.WarriorFactory;
import Factories.WeaponFactory;
import Seeders.EntitySeeder;
import Utility.Color;
import Utility.Stats;
import WorldSets.Maps.Arena;
import WorldSets.Maps.UnitToken;

import java.util.ArrayList;
import java.util.List;

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

    // generate monsters
    private final MonsterPool monsterPool = new MonsterPool();

    private final List<UnitToken> heroTokens = new ArrayList<>();
    private final List<UnitToken> monsterTokens = new ArrayList<>();

    private int rounds = 1;
    private int spawnFrequency = 4;

    
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
            System.out.printf("---- Round %d ----%n", rounds);

            gameOver = heroesTurn();
            if (gameOver) {
                break;
            }

            endOfRoundRecovery();
            respawnFallenHeroes();
            spawnNewMonstersIfNeeded();
            rounds++;
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
        if (rounds == 0 || rounds % spawnFrequency != 0) {
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


    private boolean isMonsterOccupied(int row, int col) {
        for (UnitToken token : monsterTokens) {
            if (token.getRow() == row && token.getCol() == col) {
                return true;
            }
        }
        return false;
    }

    private Monster createMonsterForLane() {
        Monster monster = monsterPool.getRandomMonster();
        // set the level of the monster
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
        // clear the screen
        GameUI.clearScreen();

        System.out.println(Color.colorize("===============================================", Color.CYAN));
        System.out.println(Color.colorize("           ✦✦  LEGENDS OF VALOR  ✦✦", Color.PURPLE));
        System.out.println(Color.colorize("===============================================\n", Color.CYAN));

        System.out.println(Color.colorize("Welcome to ", Color.YELLOW) +
                        Color.colorize("Legends of Valor", Color.RED) +
                        Color.colorize("!", Color.YELLOW));
        System.out.println("A thrilling MOBA-style strategy game where heroes and monsters clash for glory.\n");

        System.out.println(Color.colorize("▸ Story:", Color.GREEN));
        System.out.println(
            "In the realm of Valor, chaos reigns between two mighty forces — the valiant Heroes\n" +
            "and the relentless Monsters. Each side guards their sacred Nexus, the source of their power.\n" +
            "Your duty as the Commander of the Heroes is to lead your team of three champions into battle,\n" +
            "break through enemy lines, and destroy the Monsters’ Nexus before they reach yours!\n"
        );

        System.out.println(Color.colorize("▸ Gameplay:", Color.BLUE));
        System.out.println(
            "• Control a team of " + Color.colorize("three unique heroes", Color.CYAN) + ".\n" +
            "• Fight your way through lanes guarded by monsters.\n" +
            "• Earn " + Color.colorize("gold and experience", Color.YELLOW) + " by defeating enemies.\n" +
            "• Buy items, grow stronger, and push toward the enemy Nexus.\n" +
            "• " + Color.colorize("Victory", Color.RED) + " — if any hero reaches the Monsters’ Nexus.\n" +
            "• " + Color.colorize("Defeat", Color.RED) + " — if any monster reaches yours.\n"
        );

        System.out.println(Color.colorize("▸ Shared Universe:", Color.PURPLE));
        System.out.println(
            "This world shares its roots with " + Color.colorize("Monsters and Heroes", Color.GREEN) + ".\n" +
            "All items, monsters, and damage systems remain the same — but teamwork is now the key.\n"
        );

        System.out.println(Color.colorize("Prepare your heroes. Defend your Nexus. Claim the Valor!\n", Color.CYAN));
        System.out.println(Color.colorize("Press ENTER to continue...", Color.YELLOW));

        try {
            System.in.read();
        } catch (Exception ignored) {}
    }


    /**
     * This function load heroes' data from the text files.
     */
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
    }

    @Override
    public String getName() {
        return "Legends of Valor";
    }
}
