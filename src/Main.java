import Entities.*;
import Factories.*;
import Items.Spell;
import Items.Weapons.Melee;
import Player.Party;
import Utility.Level;
import World.TileMap;


public class Main {

    public static void main(String[] args) {
        // Will run test code here as I build up the game before abstracting into classes


        Party player_party = new Party(0,0);
//
        WarriorFactory warrior_factory = new WarriorFactory();
        Hero testWarrior = warrior_factory.createEntity(new String[] {"Clyde Jenkins", "200",  "100", "5", "10", "40", "100", "50"} );
        player_party.addHeroToParty(testWarrior);
        player_party.getPartyInfo();
//
//        PaladinFactory paladin_factory = new PaladinFactory();
//        Hero testPaladin = paladin_factory.createEntity(new String[] {"Simon Bakerson", "200",  "100", "5", "10", "40", "100", "50"} );
//        player_party.addHeroToParty(testPaladin);
//        player_party.getPartyInfo();
//
//        SorcererFactory sorcerer_factory = new SorcererFactory();
//        Hero testSorcerer = sorcerer_factory.createEntity(new String[] {"Gandalf The Great", "200",  "100", "5", "10", "40", "100", "50"} );
//        player_party.addHeroToParty(testSorcerer);
//
//        // This method prints the party for future reference
//        player_party.getPartyInfo();






//        // this block is valid level up logic to can be used in the end of the battle
//        player_party.getHeroFromParty(1).addExperiencePoints(50);
//        if(player_party.getHeroFromParty(1).canLevelUp(player_party.getHeroFromParty(1).getExperiencePoints())) {
//        player_party.getHeroFromParty(1).levelUp();
//
//        }
//
//        // need to work on scaling stats on a level up
//        player_party.getPartyInfo();


//        System.out.println("Test Monsters\n============================ ");
//        //test Monster creation
//        DragonFactory dragon_factory = new DragonFactory();
//        Monster test_dragon = dragon_factory.createEntity(new String[] {"Blue Eyes White Dragon", "300" , "150", "100", "0.20"});
//        System.out.println(test_dragon.toString());
//
//        SpiritFactory spirit_factory = new SpiritFactory();
//        Monster test_spirit = spirit_factory.createEntity(new String[] {"Gengar", "300" , "150", "100", "0.30"});
//        System.out.println(test_spirit.toString());
//
//        ExoskeletonFactory exoskeleton_factory = new ExoskeletonFactory();
//        Monster test_exoskeleton = exoskeleton_factory.createEntity(new String[] {"Wither Skeleton", "300" , "150", "100", "0.10"});
//        System.out.println(test_exoskeleton.toString());


        TileMap world_map = new TileMap(5,5,"Home World");
        System.out.println((world_map.render()));
        System.out.println(world_map.getPartyPosition());

        Spell testSpell = new Spell("Fire spell", 10, new Level(2), 50, 200, Spell.Type.FIRE);

        System.out.println(testSpell.isConsumed());
        testSpell.consume();
        System.out.println(testSpell.getDurability());
        testSpell.consume();
        testSpell.consume();
        testSpell.consume();
        System.out.println(testSpell.isConsumed());
        testSpell.consume();
        System.out.println(testSpell.getType());
        System.out.println(testSpell.getType().debuffMultiplier(testSpell.getLevel()));
        Melee sword = new Melee("One handed Sword", 50, 100, 1,new Level(2));
        testWarrior.getInventory().addItem(testSpell);
        testWarrior.getInventory().addItem(sword);
        testWarrior.getInventory().viewInventory();

    }
}
