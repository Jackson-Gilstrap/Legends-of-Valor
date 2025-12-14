package Game;

import Controllers.HeroSelectionController;
import Controllers.LVInputHandler;
import Controllers.LVMovementController;
import Entities.Hero;
import Entities.Monster;
import Entities.MonsterPool;
import Enums.Direction;
import Factories.ArmorFactory;
import Factories.DragonFactory;
import Factories.ExoskeletonFactory;
import Factories.PaladinFactory;
import Factories.SorcererFactory;
import Factories.SpiritFactory;
import Factories.WarriorFactory;
import Factories.WeaponFactory;
import Parties.MonsterParty;
import Parties.Party;
import Seeders.EntitySeeder;
import Utility.Color;
import Utility.Stats;
import WorldSets.Maps.Arena;
import WorldSets.Maps.Lane;

import java.util.ArrayList;
import java.util.List;

import Commands.*;

/**
 * Legends of Valor main game loop.
 * This is a lightweight implementation that focuses on the core lane rules,
 * movement, teleport, and recall. Combat and shopping can be layered on later.
 */
public class LegendsOfValor extends GameController {

    private static final int ARENA_ROWS = 8;
    private static final int ARENA_COLS = 8;

    private final GameUI ui;
    private final Arena arena;

    private final List<Hero> warriors;
    private final List<Hero> paladins;
    private final List<Hero> sorcerers;

    // generate monsters
    private final MonsterPool monsterPool;
    private MonsterParty monsters;

    // heroes
    private Party party;

    private int rounds = 0;
    private int spawnFrequency = 4;

    // handler
    private LVMovementController actionController;
    private LVInputHandler handler;

    public LegendsOfValor(){
        ui = new GameUI();
        arena = new Arena(ARENA_ROWS, ARENA_COLS);
        warriors = new ArrayList<>();
        paladins = new ArrayList<>();
        sorcerers = new ArrayList<>();
        monsterPool = new MonsterPool();
        monsterPool.generateMonsters();
        monsters = new MonsterParty();
        party = new Party();
        actionController = new LVMovementController(ui, arena);
        handler = new LVInputHandler(ui);
        registerCmds();
    }
    
    private void registerCmds(){
        handler.register("W", new Move(actionController, Direction.UP))
        .register("S", new Move(actionController, Direction.DOWN))
        .register("A", new Move(actionController, Direction.LEFT))
        .register("D", new Move(actionController, Direction.RIGHT))
        .register("TP", new Teleport(actionController))
        .register("R", new Recall(actionController))
        .register("C", new ClearObstacle(actionController))
        .register("ATK", new Attack(actionController))
        .register("Q", new Quit(this))
        .register("M", new EnterMarket(actionController));
    }

    @Override
    public void startGame() {
        introduceGame();
        loadGameData();
        selectDifficulty();
        partySelection();
        gameLoop();
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

    @Override
    protected void gameLoop() {

        while (!isOver() && !isQuit()) {
            // spawn the monsters if needed
            if(rounds % spawnFrequency == 0){
                spawnMonsters();
            }

            System.out.println(arena.render());
            System.out.printf("---- Round %d ----%n", rounds);

            List<Hero> aliveHeros = party.getAliveHeroes();
            for(Hero h: aliveHeros){
                heroTurn(h);
                if(isOver() || isQuit()) return;
            }
            actionController.takeTurns();

            for (int i = 0; i < monsters.getMonsterPartySize(); i++) {
                Monster m  = monsters.getMonsterFromParty(i);
                monsterTurn(m);
                if(isOver()) return;
            }

            actionController.takeTurns();

            endOfRoundRecovery();
            respawnFallenHeroes();
            rounds++;
        }

        System.out.println("Game over");
    }

    @Override
    protected boolean isOver(){
        return arena.isHeroNexusInvaded() || arena.isMonsterNexusInvaded();
    }

    @Override
    public String getName() {
        return "Legends of Valor";
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

    private void selectDifficulty() {
        System.out.println("Select difficulty: 1) Easy 2) Medium 3) Hard");
        int choice = ui.askInt();
        switch (choice) {
            case 2:
                spawnFrequency = 5;
                break;
            case 3:
                spawnFrequency = 3;
                break;
            default:
                spawnFrequency = 6;
                break;
        }
    }

    private void partySelection() {
        HeroSelectionController selector = new HeroSelectionController(ui, party, warriors, paladins, sorcerers);

        List<Lane> lanes = arena.getAllLanes();

        for (Lane l:lanes) {
            while(l.isEmpty()){
                // get the hero
                System.out.printf(Color.colorize("Choose a hero for %s.%n", Color.GREEN),l.getName());
                Hero chosen = selector.select();
                if(chosen != null){
                    party.add(chosen);
                    // add the hero position to the map
                    arena.addHero(chosen, ARENA_ROWS-1, arena.laneToColumn(l));
                    l.addHero(chosen);
                }
                else{
                    System.out.println("Please choose your hero for each lane before the game starts.");
                }
            }
        }

        // start the game
        party.getPartyInfo();
        String confirm = ui.askOneWord("Start the game? (yes/no): ");

        if (confirm.toLowerCase().startsWith("y")) {
            System.out.println("Starting game!");
            return;
        }
    }

    private void spawnMonsters() {
        List<Lane> allLanes = arena.getAllLanes();
        for (int i = 0; i < 3; i++) {
           Monster m = monsterPool.getRandomMonster();
           monsters.addMonster(m);
        }
        int count = 0;
        for (Lane l: allLanes) {
            int row = 0;
            int col = arena.laneToColumn(l);
            if (arena.hasMonsterAt(row, col)) {
                continue;
            }
            int avgLevel = party.getPartyLevel();


            Monster monster = monsters.getMonsterFromParty(count);
            monster.rescaleStatsForLevel(avgLevel); // rescale the monsters
            arena.addMonster(monster, row, col);
            System.out.printf("A new %s spawns in %s.%n", monster.getName(), l.getName());
            count++;

        }
    }

    private void heroTurn(Hero h) {
        if (h.getStats().getHealth() <= 0) {
            return;
        }

        actionController.setTarget(h);

        boolean turnDone = false;
        while (!turnDone) {
            System.out.printf("Hero turn: %s at (%d,%d)%n",
                    h.getName(), h.getRow(), h.getCol());
            handler.printValidCommands();
            String command = handler.getInput();
            turnDone = handler.handleCommand(command);
            System.out.println(arena.render());
        }

        if (arena.isMonsterNexusInvaded()) {
            System.out.println("Heroes reached the monster nexus. You win!");
        }

    }

    private void monsterTurn(Monster m) {
        actionController.setTarget(m);

        // thinking...
        ui.sleep(500); // 

        // if there is a hero next
        Hero adjacentHero = arena.findAdjacentHero(m);
        if (adjacentHero != null) {
            System.out.printf("%s engages %s near (%d,%d)%n",
                    m.getName(),
                    adjacentHero.getName(),
                    m.getRow(),
                    m.getCol());
            m.attack(adjacentHero);
            ui.sleep(800);

            return;
        }

        // move down
        System.out.printf("%s moves down...%n", m.getName());
        ui.sleep(600);

        handler.handleCommand("S");
        System.out.println(arena.render());

        ui.sleep(400);

        if (arena.isMonsterNexusInvaded()) {
            System.out.println("A monster reached the hero nexus. Monsters win!");
        }
    }

    private void endOfRoundRecovery() {
        for (Hero h: party) {
            Stats stats = h.getStats();
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
        for (Hero h: party.getDeadHeroes()) {
            Stats stats = h.getStats();
            stats.setHealth(stats.getMax_health());
            stats.setMana(stats.getMax_mana());
            arena.respawn(h);
            System.out.printf("%s has respawned at their nexus.%n", h);
        }
    }

}
