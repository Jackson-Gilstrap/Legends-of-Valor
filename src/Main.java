import Game.GameController;
import Game.InputHandler;
import Parties.Party;
import World.TileMap;


public class Main {

    public static void main(String[] args) {
        GameController game = new GameController(new InputHandler(), new TileMap(8,8,"Forest", new Party()));
        game.startGame();
    }
}
