package Game;

import Controllers.HeroSelectionController;
import Controllers.InputHandler;
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
import Market.FilteredMarketDisplay;
import Market.MarketController;
import Market.MarketInteractionController;
import Parties.MonsterParty;
import Parties.Party;
import Seeders.EntitySeeder;
import Utility.Color;
import Utility.Stats;
import WorldSets.Maps.Arena;
import WorldSets.Maps.Lane;
import Views.CliArenaView;

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
    private InputHandler handler;
    private MarketController marketController;
    private MarketInteractionController mIController;

    //context
    private LOVGameContext lovContext;
    private final CliArenaView arenaView = new CliArenaView();

    public LegendsOfValor(){
        ui = new GameUI();
        
        // entity set
        warriors = new ArrayList<>();
        paladins = new ArrayList<>();
        sorcerers = new ArrayList<>();
        monsterPool = new MonsterPool();
        monsters = new MonsterParty();
        party = new Party();
        arena = new Arena(ARENA_ROWS, ARENA_COLS, party, monsters);

        // controllers
        actionController = new LVMovementController(ui, arena);
        lovContext = new LOVGameContext();
        handler = new InputHandler(ui);
        marketController = new MarketController(ui);
        mIController = new MarketInteractionController(marketController);
        marketController.setMarketDisplayStrategy(new FilteredMarketDisplay());

        // cmds
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
        .register("M", new EnterMarket(actionController, mIController, lovContext))
        .register("I", new Info(actionController));
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

            arenaView.render(arena, rounds);

            List<Hero> aliveHeros = party.getAliveHeroes();
            for(Hero h: aliveHeros){
                lovContext.setActiveHero(h);
                heroTurn(h);
                if(isOver() || isQuit()) return;
            }
            actionController.takeTurns();

            for (int i = 0; i < monsters.size(); i++) {
                Monster m  = monsters.get(i);
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
    protected boolean isOver() {
        if (arena.isHeroNexusInvaded()) {
            System.out.println(Color.RED +
                "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                "┃            GAME OVER :(.             ┃\n" +
                "┃   Monsters have invaded your Nexus!  ┃\n" +
                "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n" +
                Color.RESET);
            return true;
        }

        if (arena.isMonsterNexusInvaded()) {
            System.out.println(Color.GREEN +
                "\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                "┃            YOU WIN! :)               ┃\n" +
                "┃   Heroes conquered the Monster Nexus!┃\n" +
                "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n" +
                Color.RESET);
            return true;
        }

        return false;
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
                    arena.spawnHero(chosen, ARENA_ROWS-1, arena.laneToColumn(l));
                    l.addHero(chosen);
                }
                else{
                    System.out.println("Please choose your hero for each lane before the game starts.");
                }
            }
        }

        // start the game
        party.getPartyInfo();
        String confirmPlay = "";
        while (!confirmPlay.equals("yes")) {
            confirmPlay = ui.askOneWord("Start the game? (yes/no): ").toLowerCase().trim();
            if (confirmPlay.equals("no")) {
                System.exit(0);
            }

        }
            System.out.println("Starting game!");


    }

    private void spawnMonsters() {
        List<Lane> allLanes = arena.getAllLanes();
        for (Lane l: allLanes) {
            int row = 0;
            int col = arena.laneToColumn(l);
            if (arena.hasMonsterAt(row, col)) {
                continue;
            }

            Monster monster = monsterPool.getRandomMonster();
            int avgLevel = party.getPartyLevel();
            monster.rescaleStatsForLevel(avgLevel); // rescale the monsters
            monsters.add(monster);
            arena.spawnMonster(monster, row, col);
            System.out.printf("A new %s spawns in %s.%n", monster.getName(), l.getName());

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
        GameUI.sleep(500); // 

        // if there is a hero next
        Hero adjacentHero = arena.findAdjacentHero(m);
        if (adjacentHero != null) {
            System.out.printf("%s engages %s near (%d,%d)%n",
                    m.getName(),
                    adjacentHero.getName(),
                    m.getRow(),
                    m.getCol());
            m.attack(adjacentHero);
            GameUI.sleep(800);

            return;
        }

        // move down
        System.out.printf("%s moves down...%n", m.getName());
        GameUI.sleep(600);

        handler.handleCommand("S");
        System.out.println(arena.render());

        GameUI.sleep(400);

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
