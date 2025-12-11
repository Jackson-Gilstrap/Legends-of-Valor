package Game;

import java.util.List;
import Utility.Color;

/**
 * The Launcher class is responsible for a initial flow of the game center.
 * The Launcher lets users to choose from games.
 */
public class Launcher {
    private List<GameController> games;
    private final GameUI ui = new GameUI();
    private MonstersVsHeroes mvhGame;// handle the prompt

    /**
     * Register a game that is not already registered.
     * @param game
     * @return
     */
    public Launcher register(GameController game){
        if(!games.contains(game)) games.add(game);
        return this;
    }

    /**
     * Some UI design that immerse users.
     */
    public void start(){
        greeting();
        int choice = askForGameIndex();
        games.get(choice).startGame();
        mvhGame.startGame();

    }

    /**
     * A greeting of the Legends Center with art design and color print based on the terminal.
     */
    public static void greeting() {
        String logo =
                "██╗     ███████╗ ██████╗ ███████╗███╗   ██╗██████╗ ███████╗\n" +
                "██║     ██╔════╝██╔════╝ ██╔════╝████╗  ██║██╔══██╗██╔════╝\n" +
                "██║     █████╗  ██║  ███╗█████╗  ██╔██╗ ██║██║  ██║███████╗\n" +
                "██║     ██╔══╝  ██║   ██║██╔══╝  ██║╚██╗██║██║  ██║╚════██║\n" +
                "███████╗███████╗╚██████╔╝███████╗██║ ╚████║██████╔╝███████║\n" +
                "╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚═╝  ╚═══╝╚═════╝ ╚══════╝\n";

        System.out.println(Color.colorize(logo, Color.YELLOW));
        System.out.println(Color.colorize("Welcome to the Legends Center", Color.CYAN));
        System.out.println(Color.colorize("Where Heroes and Monsters share the same destiny...", Color.LIGHTGREEN));
        System.out.println();

        System.out.println(Color.colorize("Two worlds await your command:", Color.WHITE));
        System.out.println("  " + Color.colorize("[1] RPG Realm", Color.GREEN) + "  —  Embark on an epic adventure as a hero of light.");
        System.out.println("  " + Color.colorize("[2] MOBA Arena", Color.MAGENTA) + " —  Test your might against legends from across realms.");
        System.out.println();

        System.out.println(Color.colorize("Your progress, your heroes, one legend.", Color.LIGHTGREEN));
        System.out.println(Color.colorize("──────────────────────────────────────────────", Color.BLUE));
        System.out.println(Color.colorize("Version 1.0.0  |  © 2025 Legends Studio", Color.WHITE));
        System.out.println(Color.reset());
    }

    
    /**
     * Ask the player for the index of the game they want to play.
     * @return
     */
    private int askForGameIndex(){
        return ui.askInt();
    }
}
