package Game;

import java.sql.SQLOutput;
import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner =  new Scanner(System.in);

//    public int[] getMovementInput() {
//
//    }
    public String getInput() {
        printValidCommands();
        while (true) {
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "W":
                case "S":
                case "A":
                case "D":
                case "Q":
                case "I":
                case "T":
                case "F":
                    return choice;
                default:
                    printValidCommands();
                    System.out.println("Invalid choice - Input again");

            }
        }


    }

    private void printValidCommands() {
        System.out.println("Valid Commands");
        System.out.println("Movement     - W (Up), A (Left), S (Down), D (Right)");
        System.out.println("Interaction  - F (Interact), I (Info), T (Travel), M (Map)");
        System.out.println("Game State   - Q (Quit)");
    }
}
