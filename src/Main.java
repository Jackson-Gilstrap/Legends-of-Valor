import World.Space;
import World.Spaces.PlainSpace;

public class Main {

    public static void main(String[] args) {
//        GameController game = new GameController(new InputHandler(), new TileMap(8,8,"Forest", new Party()));
//        game.startGame();

        //testing for valor game below
        Space test_Space = new PlainSpace("Plain", 0,0);

        System.out.println(test_Space);


    }
}
