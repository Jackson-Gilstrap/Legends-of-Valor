package Game;

import Adapters.PartyPositionAdapter;
import Controllers.HeroInfoController;
import Controllers.HeroSelectionController;
import Controllers.MVHInputController;
import Controllers.MVHMovementController;
import Entities.Hero;
import Factories.*;
import Parties.Party;
import Seeders.EntitySeeder;
import WorldSets.Maps.World;
import WorldSets.Spaces.PlainSpace;

import java.util.ArrayList;
import java.util.List;


public class MonstersVsHeroes extends GameController {

    private final GameUI ui;
    private final World world;
    private final MVHMovementController movementController;
    private final MVHInputController inputController;
    private final HeroSelectionController heroSelectionController;
    private final HeroInfoController heroInfoController;
    private final List<Hero> warriors = new ArrayList<>();
    private final List<Hero> paladins = new ArrayList<>();
    private final List<Hero> sorcerers = new ArrayList<>();

    public MonstersVsHeroes() {
        this.ui = new GameUI();
        Party party = new Party();
        this.world = new World(8,8, party);
        this.movementController =  new MVHMovementController(world, new PartyPositionAdapter(world), ui);
        this.inputController = new MVHInputController(ui, this, world, movementController);
        this.heroSelectionController = new HeroSelectionController(ui, world, warriors, paladins, sorcerers);
        this.heroInfoController = new HeroInfoController(ui);
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
        boolean gameOver = false;

        while (!gameOver) {
            System.out.println(world.render());
            inputController.printValidCommands();
            String command = inputController.getInput();
            gameOver = inputController.handleCommand(command);
        }

        System.out.println("Game over");
    }


    // Called by MVHInputController for "I"
    public void getHeroInfo() {
        world.getPlayerParty().getPartyInfo();

        System.out.print("Select a hero by number: ");
        int choice = ui.askInt() - 1;

        if (choice < 0 || choice >= world.getPlayerParty().getPartySize()) {
            System.out.println("Invalid hero selection.");
            return;
        }

        Hero hero = world.getPlayerParty().getHeroFromParty(choice);
        heroInfoController.showHeroDetails(hero);
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
        heroSelectionController.startSelectionMenu();
    }


    public void checkForBattle() {
        int row = world.getParty_row();
        int col = world.getParty_col();

        // Only battle on plain spaces
        if (!(world.getSpace(row, col) instanceof PlainSpace)) {
            return;
        }

        // Random battle trigger
        if (!rollDie(7)) {
            return;
        }

        // Initiate battle
        System.out.println("A wild group of monsters appears!");

        Battle battle = new Battle(world.getPlayerParty());
        boolean survived = battle.battle();

        if (!survived) {
            System.out.println("Your party has fallen...");
            System.exit(0);  // End entire program cleanly
        }

    }

    /**
     * Get the name of the game.
     */
    public String getName(){
        return "Monsters VS Heroes";
    }




}
