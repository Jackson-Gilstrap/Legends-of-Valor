package Game;


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

    public abstract String getName();
    public boolean isQuit(){
        return quit;
    }
}



