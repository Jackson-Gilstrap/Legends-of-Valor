package Game;

import Utility.Color;

/**
 * Central place to print reusable ASCII art banners.
 */
public class AsciiArt {

    public void printGreeting() {
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

    public void printGameOver() {
        String art =
                "  _____                         ____                 \n" +
                " / ____|                       / __ \\                \n" +
                "| |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __ \n" +
                "| | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__|\n" +
                "| |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |   \n" +
                " \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|   \n";
        System.out.println(art);
    }
}
