package Game;

import java.util.ArrayList;
import java.util.List;
import Utility.Color;

/**
 * The Launcher class is responsible for a initial flow of the game center.
 * The Launcher lets users to choose from games.
 */
public class Launcher {
    private List<GameController> games = new ArrayList<>();
    private GameUI ui = new GameUI();   // handle the prompt

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
        int choice;
        do{
            System.out.println(GameInfo());
            System.out.print("> ");
            choice = ui.askInt();
        } while(
            choice < 1 || choice > games.size()
        );
        return choice-1;    // coordinate with the index
    }

    /**
     * Build and return a formatted introduction text for all available games.
     * Each game shows its index and a short description.
     * @return a multi-line string describing all games
     */
    private String GameInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Here you can choose from the following games:\n\n");

        int index = 1;
        for (GameController g : games) {
            info.append(index++)
                .append(". ")
                .append(g.getName())
                .append("\n");

            // if th game has some description
            // if (g.getDescription() != null && !g.getDescription().isEmpty()) {
            //     info.append("   → ").append(g.getDescription()).append("\n");
            // }

            info.append("\n");
        }

        info.append("Please enter the number of the game you want to play.\n");
        return info.toString();
    }

}
