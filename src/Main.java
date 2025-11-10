import Entities.*;
import Player.Party;
import World.*;

public class Main {

    public static void main(String[] args) {
        // Will run test code here as I build up the game before abstracting into classes


        Party player_party = new Party(0,0);

        WarriorFactory warrior_factory = new WarriorFactory();
        Hero testWarrior = warrior_factory.createEntity(new String[] {"Clyde Jenkins", "200",  "100", "5", "10", "40", "100", "50"} );
        player_party.addHeroToParty(testWarrior);
        player_party.getPartyInfo();

        PaladinFactory paladin_factory = new PaladinFactory();
        Hero testPaladin = paladin_factory.createEntity(new String[] {"Simon Bakerson", "200",  "100", "5", "10", "40", "100", "50"} );
        player_party.addHeroToParty(testPaladin);
        player_party.getPartyInfo();

        SorcererFactory sorcerer_factory = new SorcererFactory();
        Hero testSorcerer = sorcerer_factory.createEntity(new String[] {"Gandalf The Great", "200",  "100", "5", "10", "40", "100", "50"} );
        player_party.addHeroToParty(testSorcerer);

        // this method prints the party for future reference
        player_party.getPartyInfo();






        // this block is valid level up logic to can be used in the end of the battle
        player_party.getHeroFromParty(1).addExperiencePoints(50);
        if(player_party.getHeroFromParty(1).canLevelUp(player_party.getHeroFromParty(1).getExperiencePoints())) {
        player_party.getHeroFromParty(1).levelUp();

        }

        // need to work on scaling stats on a level up
        player_party.getPartyInfo();
    }
}
