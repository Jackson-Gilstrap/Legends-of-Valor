package Commands;

import Game.GameController;

public class Quit implements Command{
    private GameController game;
    public Quit(GameController g){
        game = g;
    }

    @Override
    public boolean execute() {
        game.quit();
        return true;
    }

    @Override
    public String name() {
        return "QUIT";
    }
    
}
