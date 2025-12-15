package Market;

import Entities.Hero;
import Game.GameUI;
import Items.Item;
import Utility.Inventory;

public class SimpleMarketDisplay implements MarketDisplayStrategy {

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

    private void handleBuy(Market market, Hero hero, GameUI ui) {
        Inventory market_inventory = market.getMarketInventory();
        for (int i = 0; i < market_inventory.getInventorySize(); i++) {
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
            System.out.println("Sold " + item.getName() + " for " + (item.getPrice() / 2 ) + " gold");
            break;
        }
    }
}
