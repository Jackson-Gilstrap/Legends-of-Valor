import Entities.*;
import Factories.*;
import Items.Spell;
import Items.Weapons.Melee;
import Player.Party;
import Utility.Level;
import Utility.Stats;
import World.TileMap;


public class Main {

    public static void main(String[] args) {
        // Will run test code here as I build up the game before abstracting into classes


        Party player_party = new Party(0,0);
//
        WarriorFactory warrior_factory = new WarriorFactory();
        Hero testWarrior = warrior_factory.createEntity("Clyde Jenkins" , new Stats.StatsBuilder()
                .health(200)
                .mana(100)
                .attack(50)
                .dexterity(30)
                .agility(0.15)
                .damage_reduction(0.0)
                .buildStats()); // fix the create entity
        player_party.addHeroToParty(testWarrior);
        player_party.getPartyInfo();

        DragonFactory dragon_factory = new DragonFactory();
        Monster dragon = dragon_factory.createEntity("Blue Eyes White Dragon", new Stats.StatsBuilder()
                .health(200)
                .attack(100)
                .agility(0.5)
                .damage_reduction(0.3)
                .buildStats());
        System.out.println(dragon.toString());

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
//        testWarrior.getInventory().addItem(testSpell);
//        testWarrior.getInventory().addItem(sword);
//        testWarrior.getInventory().viewInventory();

    }
}
