package Game;

import java.util.Scanner;

public class GameUI {

    private final Scanner in = new Scanner(System.in);

    public int askInt() {
        while(!in.hasNextInt()) {
            System.out.println("Please enter an integer");
            in.next();
        }
        return in.nextInt();
    }

    public String askOneWord(String prompt) {
        System.out.println(prompt);
        return in.next();

    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; ++i) System.out.println();
        }
    }




}
