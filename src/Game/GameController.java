package Game;

import Entities.Hero;
import Factories.*;
import Items.*;
import Seeders.EntitySeeder;
import WorldSets.*;
import WorldSets.Maps.World;
import WorldSets.Spaces.MarketSpace;




import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class GameController {

    public abstract void startGame();
    public abstract void gameLoop();





    private void showHeroDetails(Hero hero) {
        System.out.println(hero.toString());
        boolean exit = false;
        while (!exit) {
            System.out.println("What would you like to do?\n1. View Inventory\n2. View Equipped Items\n3. View Hero Stats\n4. Unequip an item\n5. Equip an item\n6. Exit");
            int choice = ui.askInt();
            switch (choice) {
                case 1: // view inventory
                    hero.getInventory().viewInventory();
                    break;
                case 2: // view jacket
                    hero.getJacket().viewJacket();
                    break;
                case 3:
                    hero.viewStats();
                    break;
                case 4: // unequip
                    hero.getJacket().viewJacket();
                    System.out.println("What Item would you like to unequip");
                    String slot_choice = ui.askOneWord("Choose a slot: Main / Offhand / Helmet / Chestplate / Legging / Spell / Potion ").toLowerCase();
                    switch (slot_choice) {
                        case "main":
                            if(hero.getJacket().isOccupied(hero.getJacket().getMain())) {
                                Item item = hero.getJacket().getMain().unequipFromSlot();
                                hero.getInventory().addItem(item);
                                System.out.println("Unequipping " + item.getName() + "from " + slot_choice);
                                break;
                            }
                            System.out.println("slot is unoccupied");
                            break;
                        case "offhand":
                            if(hero.getJacket().isOccupied(hero.getJacket().getOffhand())) {
                                Item item = hero.getJacket().getOffhand().unequipFromSlot();
                                hero.getInventory().addItem(item);
                                System.out.println("Unequipping " + item.getName() + "from " + slot_choice);
                                break;
                            }
                            System.out.println("slot is unoccupied");
                            break;
                        case "helmet":
                            if(hero.getJacket().isOccupied(hero.getJacket().getHelmet())) {
                                Item item = hero.getJacket().getHelmet().unequipFromSlot();
                                hero.getInventory().addItem(item);
                                System.out.println("Unequipping " + item.getName() + "from " + slot_choice);
                                break;
                            }
                            System.out.println("slot is unoccupied");
                            break;
                        case "chestplate":
                            if(hero.getJacket().isOccupied(hero.getJacket().getChestplate())) {
                                Item item = hero.getJacket().getChestplate().unequipFromSlot();
                                hero.getInventory().addItem(item);
                                System.out.println("Unequipping " + item.getName() + "from " + slot_choice);
                                break;
                            }
                            System.out.println("slot is unoccupied");
                            break;
                        case "legging":
                            if(hero.getJacket().isOccupied(hero.getJacket().getLeggings())) {
                                Item item = hero.getJacket().getLeggings().unequipFromSlot();
                                hero.getInventory().addItem(item);
                                System.out.println("Unequipping " + item.getName() + "from " + slot_choice);
                                break;
                            }
                            System.out.println("slot is unoccupied");
                            break;
                        case "spell":
                            if(hero.getJacket().isOccupied(hero.getJacket().getSpells())) {
                                Item item = hero.getJacket().getSpells().unequipFromSlot();
                                hero.getInventory().addItem(item);
                                System.out.println("Unequipping " + item.getName() + "from " + slot_choice);
                                break;

                            }
                            System.out.println("slot is unoccupied");
                            break;
                        case "potion":
                            if(hero.getJacket().isOccupied(hero.getJacket().getPotions())) {
                                Item item = hero.getJacket().getPotions().unequipFromSlot();
                                hero.getInventory().addItem(item);
                                System.out.println("Unequipping " + item.getName() + "from " + slot_choice);
                                break;
                            }
                            System.out.println("slot is unoccupied");
                            break;
                        default:
                            System.out.println("Not a valid item slot");
                            break;
                    }
                    break;
                case 5: // equip
                    hero.getInventory().viewInventory();
                    if(hero.getInventory().getInventorySize() == 0) {
                        System.out.println("No items in your inventory to equip");
                        break;
                    }
                    System.out.println("Choose an item from your inventory");
                    int inventory_choice = ui.askInt();

                    if(inventory_choice < 0 || inventory_choice >= hero.getInventory().getInventorySize()) {
                        System.out.println("Invalid choice");
                        break;
                    }
                    Item item = hero.getInventory().getItem(inventory_choice);
                    if(item instanceof Weapon) {
                        if(hero.getLevelObj().getCurrentLevel() < item.getLevel().getCurrentLevel()){
                            System.out.println("Item is not equippable yet please level up");
                            break;
                        }
                        if(hero.getJacket().equipWeapon((Weapon) item)) {
                            hero.getInventory().removeItem(item);
                            System.out.println("Equipping "+ item.getName());
                        }  else {
                            System.out.println("Failed to equip " + item.getName());
                        }
                        break;

                    }else if (item instanceof Armor) {
                        if(hero.getLevelObj().getCurrentLevel() < item.getLevel().getCurrentLevel()){
                            System.out.println("Item is not equippable yet please level up");
                            break;
                        }
                        if(hero.getJacket().equipArmor((Armor) item)) {
                            hero.getInventory().removeItem(item);
                            System.out.println("Equipping "+ item.getName());
                        } else {
                            System.out.println("Failed to equip " + item.getName());
                        }
                        break;

                    }else if (item instanceof Spell) {
                        if(hero.getLevelObj().getCurrentLevel() < item.getLevel().getCurrentLevel()){
                            System.out.println("Item is not equippable yet please level up");
                            break;
                        }
                        if(hero.getJacket().equipSpell((Spell) item)) {
                            hero.getInventory().removeItem(item);
                            System.out.println("Equipping "+ item.getName());
                        } else {
                            System.out.println("Failed to equip " + item.getName());
                        }
                        break;

                    } else if (item instanceof Potion) {
                        if(hero.getLevelObj().getCurrentLevel() < item.getLevel().getCurrentLevel()) {
                            System.out.println("Item is not equippable yet please level up");
                            break;
                        }
                        if(hero.getJacket().equipPotion((Potion) item)) {
                            hero.getInventory().removeItem(item);
                            System.out.println("Equipping "+ item.getName());
                        } else {
                            System.out.println("Failed to equip " + item.getName());
                        }
                        break;

                    }else {
                        System.out.println("Not a valid item to equip");
                        continue;
                    }
                case 6:
                   exit = true;
                   break;
               default:
                   System.out.println("Invalid choice");
                   break;
            }
        }

    }










public void getHeroInfo(){
    String choice = ui.askOneWord("Would you like to view your heros...\nYes or No.");
                switch (choice) {
        case "Yes":
        case "yes":
            map.getPlayerParty().getPartyInfo();

            System.out.println("PICK A HERO\n");
            int hero_choice = ui.askInt();
            if(hero_choice > map.getPlayerParty().getPartySize() || hero_choice <= 0) {
                System.out.println("Invalid choice.");
                break;
            }
            showHeroDetails(map.getPlayerParty().getHeroFromParty(hero_choice - 1));
            break;
        case "No":
        case "no":
            break;
        default:
            System.out.println("Not a valid choice.");
            break;

    }

}

    private boolean rollDie(int sides) {
        Random random = new Random();
        int die1 = random.nextInt(sides);
        int die2 = random.nextInt(sides);
        return die1 == die2;
    }


}
