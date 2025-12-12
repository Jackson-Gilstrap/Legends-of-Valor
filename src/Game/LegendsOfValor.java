package Game;

import java.util.ArrayList;
import java.util.List;

import Controllers.HeroSelectionController;
import Controllers.LVInputController;
import Controllers.LVMovementController;
import Entities.Hero;
import Entities.Monster;
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
import WorldSets.Maps.Arena;

public class LegendsOfValor extends GameController {
    // heroes to load from the text files
    private final List<Hero> warriors = new ArrayList<>();
    private final List<Hero> paladins = new ArrayList<>();
    private final List<Hero> sorcerers = new ArrayList<>();

    // controller for choosing heroes
    private final HeroSelectionController heroSelectionController;
    private final Party party;  // the hero party

    // the mapset
    private Arena arena;
    private List<Monster> monsters; // the monsters on the map
    private LVMovementController mController;   // control the movement on the map
    // the input
    private final LVInputController iController;
    
    public LegendsOfValor(){
        GameUI ui = new GameUI();
        party = new Party();
        this.heroSelectionController = new HeroSelectionController(ui, party, warriors, paladins, sorcerers);   // selection won't start now

        // build the map
        this.arena = new Arena(8, 8, party, new MonsterParty(monsters));
        // the movement on the map

        // the input
        this.iController = new LVInputController(ui, null, paladins);
    }


    @Override
    public void startGame() {
        System.out.println("Starting Legends of Valor...");
        System.out.println("Loading the data...");
        loadGameData();
        introduceGame();

        // let the user choose the hero party
        partySelection();

        // start the loop
        gameLoop();
    }

    @Override
    public void gameLoop() {
        while(!isOver()){
            // show the map
            System.out.println(arena.render());
        }
    }

    @Override
    public boolean isOver(){
        // TODO: the game is over when either party reach the opponent's Nexus
        return true;
    }

    @Override
    public String getName() {
        return "Legends of Valor";
    }

    @Override
    protected void introduceGame() {
        // clear the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println(Color.CYAN + "===============================================");
        System.out.println(Color.PURPLE + "           ✦✦  LEGENDS OF VALOR  ✦✦");
        System.out.println(Color.CYAN + "===============================================\n" + Color.RESET);

        System.out.println(Color.YELLOW + "Welcome to " + Color.RED + "Legends of Valor" + Color.YELLOW + "!");
        System.out.println("A thrilling MOBA-style strategy game where heroes and monsters clash for glory.\n");

        System.out.println(Color.GREEN + "▸ Story:");
        System.out.println(Color.RESET +
                "In the realm of Valor, chaos reigns between two mighty forces — the valiant Heroes\n" +
                "and the relentless Monsters. Each side guards their sacred Nexus, the source of their power.\n" +
                "Your duty as the Commander of the Heroes is to lead your team of three champions into battle,\n" +
                "break through enemy lines, and destroy the Monsters’ Nexus before they reach yours!\n");

        System.out.println(Color.BLUE + "▸ Gameplay:");
        System.out.println(Color.RESET +
                "• Control a team of " + Color.CYAN + "three unique heroes" + Color.RESET + ".\n" +
                "• Fight your way through lanes guarded by monsters.\n" +
                "• Earn " + Color.YELLOW + "gold and experience" + Color.RESET + " by defeating enemies.\n" +
                "• Buy items, grow stronger, and push toward the enemy Nexus.\n" +
                "• " + Color.RED + "Victory" + Color.RESET + " — if any hero reaches the Monsters’ Nexus.\n" +
                "• " + Color.RED + "Defeat" + Color.RESET + " — if any monster reaches yours.\n");

        System.out.println(Color.PURPLE + "▸ Shared Universe:");
        System.out.println(Color.RESET +
                "This world shares its roots with " + Color.GREEN + "Monsters and Heroes" + Color.RESET + ".\n" +
                "All items, monsters, and damage systems remain the same — but teamwork is now the key.\n");

        System.out.println(Color.CYAN + "Prepare your heroes. Defend your Nexus. Claim the Valor!\n" + Color.RESET);
        System.out.println(Color.YELLOW + "Press ENTER to continue..." + Color.RESET);

        try {
            System.in.read();
        } catch (Exception ignored) {}
    }

    /**
     * This function load heroes' data from the text files.
     */
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

    /**
     * The function start the selection of the party.
     * After it is executed, the party should have at least 1 hero.
     */
    private void partySelection() {
        heroSelectionController.startSelectionMenu();
    }
    
}
