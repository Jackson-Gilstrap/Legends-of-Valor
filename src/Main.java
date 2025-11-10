import Entities.Hero;
import Entities.WarriorFactory;
import Player.Party;
import World.*;

public class Main {

    public static void main(String[] args) {
        // Will run test code here as I build up the game before abstracting into classes


        Party player_party = new Party(0,0);

        WarriorFactory factory = new WarriorFactory();
        Hero hero = factory.createEntity(new String[] {"Warrior1", "200",  "100", "5", "10", "40", "100", "50"} );
        player_party.addHeroToParty(hero);
        player_party.getPartyInfo();


        player_party.getPartyInfo();
        player_party.getHeroFromParty(1).addExperiencePoints(10);
        if(player_party.getHeroFromParty(1).canLevelUp(player_party.getHeroFromParty(1).getExperiencePoints())) {
        player_party.getHeroFromParty(1).levelUp();

        }
    }
}
