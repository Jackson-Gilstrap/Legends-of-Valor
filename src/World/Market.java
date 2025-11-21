package World;

import Entities.Hero;
import Game.GameUI;
import Items.Item;
import Player.Party;
import Utility.Inventory;
import java.util.List;


public class Market{
    public enum MarketType {Weapon, Armor, Spell, Potion}
    private Inventory inventory;

    public Market(Inventory inventory) {
        this.inventory = inventory;

    }
    // when the hero wants to sell an item to the market
    private int buyItem(Item item) {
        inventory.addItem(item);
        return item.getPrice() / 2;
    }

    // when the hero wants to buy an item from the market
    private int sellItem(Item item) {
        inventory.removeItem(item);
        return item.getPrice();
    }

    public void enterMarket(GameUI ui, Party player_party) {
        //ask the user to choose hero
        showMarketMenu();
        boolean exit = false;
        while (!exit) {
        int market_choice = ui.askInt();
        System.out.println("Pick a hero to enter the market");
        for (int i  = 0 ; i < player_party.getPartySize(); i++) {
            System.out.println("Hero " + (i+1) + ": " + player_party.getHeroFromParty(i).getName());
        }
        int hero_choice = ui.askInt();
        Hero hero = player_party.getHeroFromParty(hero_choice - 1);
        switch(market_choice) {
            case 1: // buy
                if (inventory.getInventorySize() < 1) {
                    System.out.println("There are no more items to buy from this market");
                    exit = true;
                    break;
                }
                System.out.println("What item would you like to buy?");
                inventory.viewInventory();
                int item_choice = ui.askInt();
                if(item_choice >= inventory.getInventorySize()) {
                    System.out.println("Invalid item choice");
                    continue;
                }
                Item buy_item = inventory.getItem(item_choice);
                int sell_price = sellItem(inventory.getItem(item_choice));
                hero.getInventory().addItem(buy_item);
                hero.setGold(hero.getGold() - sell_price);
                break;

            case 2: //sell
                if(hero.getInventory().getInventorySize() < 1){
                    System.out.println(hero.getName() + " has no items to sell choose another hero");
                    continue;
                }
                System.out.println("What item would you like to sell?");
                System.out.println(hero.getInventory());
                int item_choice2 = ui.askInt();
                if(item_choice2 >= hero.getInventory().getInventorySize()) {
                    System.out.println("Invalid item choice");
                    continue;
                }
                Item sell_item = hero.getInventory().getItem(item_choice2);
                int buy_price = buyItem(sell_item);
                hero.getInventory().removeItem(sell_item);
                hero.setGold(hero.getGold() + buy_price);
                break;
            case 0: //exit
                System.out.println("The market will refresh once you leave...\nLeaving market...");
                exit = true;
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }

        }

    }

    public void refreshItems() {
        for(int i =0; i < inventory.getInventorySize(); i++ ) {
            Item item = inventory.getItem(i);
            inventory.removeItem(item);
        }
        // load the inventory
    }

    private void showMarketMenu() {
        System.out.println("Welcome to the Market");
        System.out.println("What would you like to do?");
        System.out.println("1. Buy");
        System.out.println("2. Sell");
        System.out.println("3. Exit");
    }


}
