package Game;

import Adapters.PartyPositionAdapter;
import Commands.*;
import Controllers.HeroSelectionController;
import Controllers.InputHandler;
import Controllers.MVHMovementController;
import Market.MarketController;
import Entities.Hero;
import Enums.Direction;
import Factories.*;
import Market.MarketInteractionController;
import Market.SimpleMarketDisplay;
import Parties.Party;
import Seeders.EntitySeeder;
import WorldSets.Maps.World;
import Views.CliWorldView;

import java.util.ArrayList;
import java.util.List;


public class MonstersVsHeroes extends GameController {

    private final GameUI ui;
    private final World world;
    private Party party;
    private final MVHMovementController actionController;
    private final InputHandler inputHandler;
    private final HeroSelectionController heroSelectionController;
    private final MarketController marketController;
    private final MarketInteractionController mIController;
    private final GameContext gameContext;
    private PartyPositionAdapter partyPositionAdapter;
    private final List<Hero> warriors = new ArrayList<>();
    private final List<Hero> paladins = new ArrayList<>();
    private final List<Hero> sorcerers = new ArrayList<>();
    private final CliWorldView view = new CliWorldView();
    // constants
    private final int MAX_HEROES = 3;
    public MonstersVsHeroes() {
        this.ui = new GameUI();
        this.party = new Party();
        this.world = new World(8,8, party);
        this.actionController =  new MVHMovementController(world, new PartyPositionAdapter(world), ui);
        this.inputHandler = new InputHandler(ui);
        this.gameContext = new MVHGameContext(party, new PartyPositionAdapter(world));
        this.heroSelectionController = new HeroSelectionController(ui, party, warriors, paladins, sorcerers);
        this.marketController = new MarketController(ui);
        marketController.setMarketDisplayStrategy(new SimpleMarketDisplay());
        mIController = new MarketInteractionController(marketController);



        registerCmds();

    }

    private void registerCmds(){
        inputHandler.register("W", new Move(actionController, Direction.UP))
        .register("S", new Move(actionController, Direction.DOWN))
        .register("A", new Move(actionController, Direction.LEFT))
        .register("D", new Move(actionController, Direction.RIGHT))
        .register("Q", new Quit(this))
        .register("F", new EnterMarket(actionController, mIController, gameContext))
        .register("I", new Info(actionController))
        .register("T", new FastTravel(actionController));
    }

    @Override
    public void startGame() {
        System.out.println("Starting Monsters Vs Heroes...");
        introduceGame();
        loadGameData();
        partySelection();
        gameLoop();
    }


    protected void gameLoop() {
        while (true && !isQuit()) {
            view.render(world);
            view.showCommands(inputHandler);
            String command = inputHandler.getInput();
            inputHandler.handleCommand(command);
        }

        System.out.println("Game over");
    }

    // loading game
    @Override
    protected void loadGameData() {

        EntitySeeder entitySeeder = new EntitySeeder(
                new WarriorFactory(),
                new PaladinFactory(),
                new SorcererFactory(),
                new DragonFactory(),
                new ExoskeletonFactory(),
                new SpiritFactory(),
                new WeaponFactory(),
                new ArmorFactory());

        warriors.addAll(entitySeeder.seedWarriors("src/TextFiles/warriors.txt"));
        paladins.addAll(entitySeeder.seedPaladins("src/TextFiles/Paladins.txt"));
        sorcerers.addAll(entitySeeder.seedSorcerers("src/TextFiles/Sorcerers.txt"));

    }
    @Override
    protected void introduceGame() {
        try {
            System.out.println("Welcome to the world of Monsters vs Heros");
            Thread.sleep(2000);
            System.out.println();
            System.out.println("The aim of the game is to take you party of heroes and battle monsters along your journey");
            Thread.sleep(2000);
            System.out.println("On your journey you will encounter various Dragons, ExoSkeletons, and Spirits");
            Thread.sleep(2000);
            System.out.println("The goal... Survive as long as possible");
            Thread.sleep(3000);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("Good luck...");
            System.out.println();

        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted " + e.getMessage());

            Thread.currentThread().interrupt();
        }

    }

    public void partySelection() {
        while(party.size()<MAX_HEROES){
            Hero h = heroSelectionController.select();
            if(h == null && party.size()<1){
                // if the party is empty but the player want to start
                System.out.println("You need at least one hero in your party.");
            } else if(h==null){
                // if the player did not choose a hero but hero is enough
                break; 
            } else{
                // if the player choose a hero
                party.add(h);
            }

        }
        // start the game
        party.getPartyInfo();
        String confirmPlay = "";
        while(!confirmPlay.equals("yes")) {
            confirmPlay = ui.askOneWord("Start the game? (yes/no): ").toLowerCase().trim();
            if (confirmPlay.equals("no")) {
                System.exit(0);
            }
        }
        System.out.println("Starting Monsters vs Heroes");
    }


    

    /**
     * Get the name of the game.
     */
    public String getName(){
        return "Monsters VS Heroes";
    }

    /**
     * As long as the heroes are not all defeated, the game won't end.
     */
    public boolean isOver(){
        return false;
    }


}
