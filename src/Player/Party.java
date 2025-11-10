package Player;

import World.Positionable;

import java.util.ArrayList;

public class Party implements Positionable {

    private final ArrayList<String> player_party;
    private int xPosition;
    private int yPosition;

    public Party(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        player_party = new ArrayList<>();

        player_party.add("Paladin");
        player_party.add("Knight");
        player_party.add("Mage");
    }
    @Override
    public int getXPosition( ){
        return xPosition;
    }
    @Override
    public int getYPosition( ){
        return yPosition;
    }


    public void getPartyInfo () {
        int i = 0;
        for(String character: player_party) {
            System.out.println("Party slot " + i + ": " + character);
            i++;
        }
    }
}
