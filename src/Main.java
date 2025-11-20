import Game.GameController;
import Game.InputHandler;
import Player.Party;

import World.TileMap;


public class Main {

    public static void main(String[] args) {
        // Will run test code here as I build up the game before abstracting into classes
        GameController game = new GameController(new InputHandler(), new TileMap(8,8,"Forest", new Party()));
        game.startGame();


//        DragonFactory dragon_factory = new DragonFactory();
//        Monster dragon = dragon_factory.createEntity("Blue Eyes White Dragon", new Stats.StatsBuilder()
//                .health(200)
//                .attack(100)
//                .agility(0.5)
//                .damage_reduction(0.3)
//                .buildStats());
//        System.out.println(dragon.toString());



//        Spell testSpell = new Spell("Fire spell", 10, new Level(2), 50, 200, Spell.Type.FIRE);
//
//
//        Melee sword = new Melee("Sword", 50, 100, 1,new Level(2));
//        Melee heavy_sword = new Melee("Heavy Sword", 80, 150, 2,new Level(3));


        // Tested adding items to inventory
//        testWarrior.getInventory().addItem(testSpell);
//        testWarrior.getInventory().addItem(sword);
//        testWarrior.getInventory().addItem(heavy_sword);
//        testWarrior.getInventory().viewInventory();

        // Tested equipping item to slot, equipping items to full slot rejected, unequipping item from slot
//        testWarrior.getJacket().equipWeapon(sword);
//        testWarrior.getInventory().removeItem(sword);
//        testWarrior.getJacket().equipWeapon(heavy_sword);
//        testWarrior.getInventory().viewInventory();
//        testWarrior.getJacket().viewJacket();
//        Item item = testWarrior.getJacket().unequip(testWarrior.getJacket().getMain());
//        testWarrior.getInventory().addItem(item);
//        testWarrior.getJacket().equipWeapon(heavy_sword);
//        testWarrior.getInventory().removeItem(heavy_sword);
//        testWarrior.getJacket().viewJacket();
//        testWarrior.getJacket().equipSpell(testSpell);
//        testWarrior.getInventory().removeItem(testSpell);
//        testWarrior.getJacket().updateBuffStats();
//        testWarrior.getJacket().updateStats();
//        testWarrior.getJacket().viewJacket();
//
//        testWarrior.getInventory().viewInventory();
//        testWarrior.viewStats();
//        System.out.println("Hero Jacket Stats: \n" + testWarrior.getJacket().getBaseStats().toString());

//

    }
}
