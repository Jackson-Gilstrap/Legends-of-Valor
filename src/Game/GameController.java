package Game;


import java.util.Random;

public abstract class GameController {
    private boolean quit = false;

    public abstract void startGame();
    protected abstract void gameLoop();
    protected abstract void introduceGame();
    protected abstract void loadGameData();
    protected abstract boolean isOver();
    public void quit(){
        quit = true;
    }
    protected boolean rollDie(int sides) {
        Random random = new Random();
        int die1 = random.nextInt(sides);
        int die2 = random.nextInt(sides);
        return die1 == die2;
    }

    public abstract String getName();
    public boolean isQuit(){
        return quit;
    }
}



