package World;

import Entities.Hero;
import Game.GameUI;
import Items.Item;
import Player.Party;
import Utility.Inventory;


public class Market{
    private Inventory market_inventory;

    public Market(Inventory market_inventory) {
        this.market_inventory = market_inventory;

    }

    // when the hero wants to sell an item to the market
    private int buyItem(Item item) {
        market_inventory.addItem(item);
        return item.getPrice() / 2;
    }

    // when the hero wants to buy an item from the market
    private int sellItem(Item item) {
        market_inventory.removeItem(item);
        return item.getPrice();
    }

    public void enterMarket(GameUI ui, Party player_party) {
        System.out.println("Welcome to the Market");
        boolean exit = false;
        while (!exit) {

            System.out.println("Pick a hero to enter the market");
            for (int i  = 0 ; i < player_party.getPartySize(); i++) {
                System.out.println("Hero " + (i+1) + ": " + player_party.getHeroFromParty(i).getName());
            }

            int hero_choice = ui.askInt();
            if(hero_choice <= 0 || hero_choice > player_party.getPartySize()) {
                System.out.println("Invalid choice");
                continue;
            }

            Hero hero = player_party.getHeroFromParty(hero_choice - 1);

            System.out.println("Choose and action");
            showMarketMenu();

            int market_choice = ui.askInt();

            if(market_choice > 4 || market_choice < 1){
                System.out.println("Invalid choice");
                continue;
            }

            switch(market_choice) {
                case 1: // buy
                    if (market_inventory.getInventorySize() < 1) {
                        System.out.println("There are no more items to buy from this market");
                        exit = true;
                        break;
                    }
                    System.out.println("What item would you like to buy?");
                    market_inventory.viewInventory();
                    int item_choice = ui.askInt();
                    if(item_choice >= market_inventory.getInventorySize()) {
                        System.out.println("Invalid item choice");
                        continue;
                    }
                    Item buy_item = market_inventory.getItem(item_choice);
                    int sell_price = sellItem(market_inventory.getItem(item_choice));
                    hero.getInventory().addItem(buy_item);
                    hero.getWallet().makeTransaction(sell_price);
                    System.out.println("Hero " + hero.getName() + " has bought " + buy_item.getName());
                    break;

                case 2: //sell
                    if(hero.getInventory().getInventorySize() < 1){
                        System.out.println(hero.getName() + " has no items to sell choose another hero");
                        continue;
                    }
                    System.out.println("What item would you like to sell?");
                    hero.getInventory().viewInventory();;
                    int item_choice2 = ui.askInt();
                    if(item_choice2 >= hero.getInventory().getInventorySize()) {
                        System.out.println("Invalid item choice");
                        continue;
                    }
                    Item sell_item = hero.getInventory().getItem(item_choice2);
                    int buy_price = buyItem(sell_item);
                    hero.getInventory().removeItem(sell_item);
                    hero.getWallet().addGold(buy_price);
                    System.out.println("Hero " + hero.getName() + " has sold " + sell_item.getName());
                    break;
                case 3: // pick new hero
                    System.out.println("Returning back to hero selection");
                    continue;
                case 4: //exit
                    System.out.println("The market will refresh once you leave...\nLeaving market...");

                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        }

    }

    public void refreshItems() {
        for(int i = 0; i < market_inventory.getInventorySize(); i++ ) {
            Item item = market_inventory.getItem(i);
            market_inventory.removeItem(item);
        }
        // load the market_inventory
    }

    private void showMarketMenu() {
        System.out.println("Choose an action.\n1. Buy | 2. Sell | 3. Pick different hero | 4. Exit the market");
    }


}
