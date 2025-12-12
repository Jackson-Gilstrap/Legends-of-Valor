package Game;


import java.util.Random;

public abstract class GameController {


    public abstract void startGame();
    protected abstract void gameLoop();
    protected abstract void introduceGame();
    protected abstract void loadGameData();

    protected boolean rollDie(int sides) {
        Random random = new Random();
        int die1 = random.nextInt(sides);
        int die2 = random.nextInt(sides);
        return die1 == die2;
    }

    public abstract String getName();

    /**
     * Each game should have a standard to judge whether the game is over.
     * @return
     */
    public abstract boolean isOver();
}



