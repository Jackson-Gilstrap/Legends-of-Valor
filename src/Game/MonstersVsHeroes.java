package Game;

import Adapters.PartyPositionAdapter;
import Controllers.HeroSelectionController;
import Controllers.MVHInputController;
import Controllers.MVHMovementController;
import Entities.Hero;
import Factories.*;
import Parties.Party;
import Seeders.EntitySeeder;
import WorldSets.Maps.World;

import java.util.ArrayList;
import java.util.List;

public class MonstersVsHeroes extends GameController {

    private final GameUI ui;
    private final World world;
    private final MVHMovementController movementController;
    private final MVHInputController inputController;
    private final HeroSelectionController heroSelectionController;
    private final EntitySeeder entitySeeder;
    private List<Hero> warriors = new ArrayList<>();
    private List<Hero> paladins = new ArrayList<>();
    private List<Hero> sorcerers = new ArrayList<>();

    public MonstersVsHeroes() {
        this.ui = new GameUI();
        Party party = new Party();
        this.world = new World(8,8, party);

        this.movementController =  new MVHMovementController(world, new PartyPositionAdapter(world), ui);
        this.inputController = new MVHInputController(ui, this, world, movementController);
        this.entitySeeder = new EntitySeeder(
                new WarriorFactory(),
                new PaladinFactory(),
                new SorcererFactory(),
                new DragonFactory(),
                new ExoskeletonFactory(),
                new SpiritFactory(),
                new WeaponFactory(),
                new ArmorFactory()
        );
        this.heroSelectionController = new HeroSelectionController(ui, world, warriors, paladins, sorcerers);
    }

    @Override
    public void startGame() {
        System.out.println("Starting Monsters Vs Heroes...");
        introduceGame();
        loadGameData();
        partySelection(); //todo for now

        gameLoop();
    }


    private void introduceGame() {
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

    private void gameLoop() {
        boolean gameOver = false;

        while (!gameOver) {
            System.out.println(world.render());
            String command = inputController.getInput();
            gameOver = inputController.handleCommand(command);
        }

        System.out.println("Game over");
    }

    public void loadPartySelection() {
        System.out.println(("Hero selection menu placeholder..."));
        System.out.println(("(Inject your real hero selection logic here)"));
    }

    // Called by MVHInputController for "I"
    public void getHeroInfo() {
        System.out.println(("Hero info menu placeholder..."));
        System.out.println(("(Hook in your showHeroDetails logic here)"));
    }

    // loading game
    public void loadGameData() {
        List<Hero> warrior_data = entitySeeder.seedWarriors("src/TextFiles/warriors.txt");
        warriors.addAll(warrior_data);

        List<Hero> paladin_data = entitySeeder.seedPaladins("src/TextFiles/Paladins.txt");
        paladins.addAll(paladin_data);
        List<Hero> sorcerer_data = entitySeeder.seedSorcerers("src/TextFiles/Sorcerers.txt");
        sorcerers.addAll(sorcerer_data);

    }

    public void partySelection() {
        heroSelectionController.startSelectionMenu();
    }




}
