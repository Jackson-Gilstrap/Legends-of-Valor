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



}
