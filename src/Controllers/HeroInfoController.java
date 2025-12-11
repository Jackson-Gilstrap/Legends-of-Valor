package Controllers;

import Entities.Hero;
import Game.GameUI;
import Items.*;

public class HeroInfoController {
    private GameUI ui;

    public HeroInfoController(GameUI ui) {
        this.ui = ui;
    }


    public void showHeroDetails(Hero hero) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- HERO DETAILS ---");
            System.out.println(hero.toString());
            System.out.println("1. View Inventory");
            System.out.println("2. View Equipped Items");
            System.out.println("3. View Hero Stats");
            System.out.println("4. Unequip an item");
            System.out.println("5. Equip an item");
            System.out.println("6. Exit");

            int choice = ui.askInt();

            switch (choice) {

                case 1:
                    hero.getInventory().viewInventory();
                    break;

                case 2:
                    hero.getJacket().viewJacket();
                    break;

                case 3:
                    hero.viewStats();
                    break;

                case 4:
                    handleUnequip(hero);
                    break;

                case 5:
                    handleEquip(hero);
                    break;

                case 6:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

    }

    private void handleUnequip(Hero hero) {

        hero.getJacket().viewJacket();
        System.out.println("Which slot to unequip?");

        String slot = ui.askOneWord("Options: main | offhand | helmet | chestplate | leggings | spell | potion");

        Item removed = null;

        switch (slot) {
            case "main":
                removed = hero.getJacket().getMain().unequipFromSlot();
                break;
            case "offhand":
                removed = hero.getJacket().getOffhand().unequipFromSlot();
                break;
            case "helmet":
                removed = hero.getJacket().getHelmet().unequipFromSlot();
                break;
            case "chestplate":
                removed = hero.getJacket().getChestplate().unequipFromSlot();
                break;
            case "leggings":
                removed = hero.getJacket().getLeggings().unequipFromSlot();
                break;
            case "spell":
                removed = hero.getJacket().getSpells().unequipFromSlot();
                break;
            case "potion":
                removed = hero.getJacket().getPotions().unequipFromSlot();
                break;
            default:
                System.out.println("Invalid slot.");
                return;
        }

        if (removed == null) {
            System.out.println("Nothing was unequipped.");
            return;
        }

        hero.getInventory().addItem(removed);
        System.out.println("Unequipped: " + removed.getName());
    }


    private void handleEquip(Hero hero) {

        hero.getInventory().viewInventory();
        if (hero.getInventory().getInventorySize() == 0) {
            System.out.println("No items to equip.");
            return;
        }

        System.out.print("Choose item index: ");
        int index = ui.askInt() - 1;

        if (index < 0 || index >= hero.getInventory().getInventorySize()) {
            System.out.println("Invalid choice.");
            return;
        }

        Item item = hero.getInventory().getItem(index);

        if (hero.getLevelObj().getCurrentLevel() < item.getLevel().getCurrentLevel()) {
            System.out.println("Hero level too low to equip this item.");
            return;
        }

        boolean success = false;

        success = item.getEquipped(hero);

        if (success) {
            hero.getInventory().removeItem(item);
            System.out.println("Equipped: " + item.getName());
        } else {
            System.out.println("Failed to equip item.");
        }
    }
}
