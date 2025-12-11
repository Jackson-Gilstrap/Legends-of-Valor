package Game;


import java.util.Random;

public abstract class GameController {


    public abstract void startGame();
    public abstract void gameLoop();

    protected boolean rollDie(int sides) {
        Random random = new Random();
        int die1 = random.nextInt(sides);
        int die2 = random.nextInt(sides);
        return die1 == die2;
    }

    public abstract String getName();

}



