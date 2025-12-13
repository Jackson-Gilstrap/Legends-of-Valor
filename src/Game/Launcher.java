package Game;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

import Utility.Color;

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
     * @return index chosen by the player
     */
    private int askForGameIndex() {
        System.out.println(Color.colorize("\nPlease choose a game to play:", Color.CYAN));
        System.out.println(Color.colorize("--------------------------------", Color.YELLOW));

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // print games
        for (int i = 0; i < games.size(); i++) {
            System.out.println("[" + (i+1) + "] " + games.get(i).getName());
        }

        System.out.println(Color.colorize("--------------------------------",Color.YELLOW));
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.print(Color.colorize("Enter the number of your choice > ",Color.LIGHTGREEN));

        int choice = ui.askInt();

        // valid the choice
        while (choice < 1 || choice > games.size()) {
            System.out.println(Color.colorize("Invalid choice.",Color.RED)+
                            " Please enter a number between 1 and " + (games.size()) + ": ");
            choice = ui.askInt();
        }

        System.out.println(Color.colorize("\nLoading " + games.get(choice-1).getName() + "...",Color.CYAN));
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return choice - 1; // return the real index
    }


}
