package Player;

import Entities.Hero;
import Interfaces.Positionable;

import java.util.ArrayList;

public class Party implements Positionable {

    private final ArrayList<Hero> player_party;
    private int xPosition;
    private int yPosition;

    public Party(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        player_party = new ArrayList<Hero>();


    }
    @Override
    public int getXPosition( ){
        return xPosition;
    }
    @Override
    public int getYPosition( ){
        return yPosition;
    }

    public void addHeroToParty(Hero hero){
        player_party.add(hero);

    }

    public Hero getHeroFromParty(int slot){
        return player_party.get(slot -1);
    }


    public void getPartyInfo() {
        System.out.println("===== PARTY INFORMATION =====");

        int index = 1;
        for (Hero character : player_party) {
            System.out.printf("Party Slot %d:%n", index);
            System.out.println(character); // relies on Hero/Warrior’s toString()
            System.out.println("-----------------------------");
            index++;
        }

        System.out.println("===== END OF PARTY =====");
    }

}
