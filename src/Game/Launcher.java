package Game;

import java.util.ArrayList;
import java.util.List;

/**
 * The Launcher class is responsible for a initial flow of the game center.
 * The Launcher lets users choose from games.
 */
public class Launcher {
    private final List<GameController> games = new ArrayList<GameController>();
    private final GameUI ui = new GameUI();
    private final AsciiArt asciiArt = new AsciiArt();

    /**
     * Register a game that is not already registered.
     */
    public Launcher register(GameController game){
        if (game != null && !games.contains(game)) {
            games.add(game);
        }
        return this;
    }

    /**
     * Some UI design that immerse users.
     */
    public void start(){
        asciiArt.printGreeting();
        boolean keepPlaying = true;
        while (keepPlaying) {
            int choice = askForGameIndex();
            GameController selected = games.get(choice);
            System.out.println("Launching: " + selected.getName());
            selected.startGame();

            asciiArt.printGameOver();
            System.out.print("Play another game? (y/n): ");
            String again = ui.askOneWord("").trim().toLowerCase();
            keepPlaying = again.startsWith("y");
        }
        System.out.println("Thanks for visiting the Legends Center!");
    }

    /**
     * Ask the player for the index of the game they want to play.
     */
    private int askForGameIndex(){
        if (games.isEmpty()) {
            throw new IllegalStateException("No games registered with the launcher.");
        }

        System.out.println("Choose a game to play:");
        for (int i = 0; i < games.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, games.get(i).getName());
        }

        int choice = -1;
        while (choice < 0 || choice >= games.size()) {
            System.out.print("Enter choice: ");
            int input = ui.askInt() - 1;
            if (input >= 0 && input < games.size()) {
                choice = input;
            } else {
                System.out.println("Invalid selection. Try again.");
            }
        }

        return choice;
    }
}
