import Player.Party;
import World.*;

public class Main {

    public static void main(String[] args) {
        // Will run test code here as I build up the game before abstracting into classes


        TileMap map = new TileMap(9,9, "Home world");
        System.out.println(map.render());

        Party player_party = new Party(0,0);
        System.out.println("Party position is: " + player_party.getXPosition() + ", " +  player_party.getYPosition());
        player_party.getPartyInfo();

    }
}
