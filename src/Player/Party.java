package Player;

import Entities.Hero;
import Interfaces.Positionable;

import java.util.ArrayList;

public class Party {

    private final ArrayList<Hero> player_party;

    public Party() {
        player_party = new ArrayList<Hero>();
    }

    public void addHeroToParty(Hero hero){
        player_party.add(hero);

    }
    public void removeHeroFromParty(Hero hero){
        player_party.remove(hero);
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
