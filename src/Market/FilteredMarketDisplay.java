package Market;

import Entities.Hero;
import Enums.ItemType;
import Game.GameUI;
import Items.Item;
import Utility.Inventory;
// FilteredMarketDisplay.java
// provides different display method for LoV markets
public class FilteredMarketDisplay implements MarketDisplayStrategy {

    @Override
    public boolean display(Market market, Hero hero, GameUI ui) {
        while (true) {
            System.out.println("Gold: " + hero.getWallet().getCurrent_gold());
            System.out.println("1. Buy | 2. Sell | 3. Exit");
            int choice = ui.askInt();
            switch (choice) {
                case 1:
                    handleBuy(market, hero, ui);
                    break;
                case 2:
                    handleSell(market, hero, ui);
                    break;
                case 3:
                    return false;
                default:
                    System.out.println("Invalid choice");
            }
        }

    }

    private Inventory makeSelection(Inventory market_inventory, GameUI ui) {
        System.out.println("Select a category of item to view");
        System.out.println("1. Weapons | 2. Armors | 3. Spells | 4. Potions");
        while(true) {
            int itemTypeChoice = ui.askInt();
            switch (itemTypeChoice) {
                case 1: // Weapons
                    return filterInventory(market_inventory, ItemType.WEAPON);
                case 2: // Armors
                    return filterInventory(market_inventory, ItemType.ARMOR);
                case 3: // Spells
                    return filterInventory(market_inventory, ItemType.SPELL);
                case 4: //Potions
                    return  filterInventory(market_inventory, ItemType.SPELL);
                default:
                    System.out.println("Invalid choice");
            };
        }
    }

    private Inventory filterInventory(Inventory market_inventory, ItemType itemType) {
        Inventory filtered = new Inventory();
        for (int i = 0; i < market_inventory.getInventorySize(); i++) {
            Item item = market_inventory.getItem(i);
            if (itemType.equals(item.getItemType()))  {
                filtered.addItem(item);
            }
        }

        return filtered;


    }

    private void handleBuy(Market market, Hero hero, GameUI ui) {
        Inventory market_inventory = market.getMarketInventory();
        Inventory filtered = makeSelection(market_inventory, ui);
        for (int i = 0; i < filtered.getInventorySize(); i++) {
            Item item = market_inventory.getItem(i);
            System.out.println("[" + i + "] " + item.getName() + ": cost " + item.getPrice() + " gold " + "| Required equip level : " + item.getLevel().getCurrentLevel() );
        }

        while(true) {
            int choice = ui.askInt();
            if(choice < 0  || choice > market_inventory.getInventorySize() - 1) {
                System.out.println("Invalid choice");
                continue;
            }
            Item item = market_inventory.getItem(choice);
            if(market.canBuy(hero, item)) {
                market.buy(hero, item);
                System.out.println("Purchased " + item.getName() + " for " + item.getPrice() + " gold");
                break;
            } else {
                System.out.println(hero.getName() + " cannot afford " + item.getName() + " for " + item.getPrice() + " gold");
                System.out.println(hero.getName() + " only has " + hero.getWallet().getCurrent_gold() + " gold");
                return;
            }
        }
    }
    private void handleSell(Market market, Hero hero, GameUI ui) {
        Inventory hero_inventory = hero.getInventory();
        hero_inventory.viewInventory();
        if(hero_inventory.getInventorySize() == 0) {
            System.out.println("Nothing to sell");
            return;
        }
        while(true) {
            int choice = ui.askInt();
            if(choice < 0 || choice > hero_inventory.getInventorySize() - 1) {
                System.out.println("Invalid choice");
                continue;
            }
            Item item = hero_inventory.getItem(choice);
            market.sell(hero, item);
            break;
        }
    }


}
