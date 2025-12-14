package Utility;
import java.util.Random;

public class RollDie {
    public static boolean rollDie(int sides) {
        Random random = new Random();
        int die1 = random.nextInt(sides);
        int die2 = random.nextInt(sides);
        return die1 == die2;
    }
}
