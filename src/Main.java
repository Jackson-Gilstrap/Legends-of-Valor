import Parties.Party;
import WorldSets.Maps.Arena;
import WorldSets.Maps.World;

public class Main {

    public static void main(String[] args) {
//        GameController game = new GameController(new InputHandler(), new TileMap(8,8,"Forest", new Party()));
//        game.startGame();

        //testing for valor game below
        Arena test_Arena = new Arena(8,8);
        System.out.println(test_Arena.render());

        World test_world = new World(8,8, new Party(0,0));
        System.out.println(test_world.render());



    }
}
