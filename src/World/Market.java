package World;

import Items.Item;
import Player.Party;
import Utility.Inventory;

public class Market <T extends Item>{
    public enum MarketType {Weapon, Armor, Spell, Potion}

    private MarketType marketType;
    private Inventory inventory;

    public Market(MarketType marketType, Inventory inventory) {
        this.marketType = marketType;
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

    public void enterMarket(Party player_party) {
        //ask the user to choose hero
        //show the inventory of the market
        inventory.viewInventory();
        //give options to buy and sell
    }

    public void exitMarket() {
        // prompt the user that once they leave the market it will refresh,
        // they can't get back any items they sold
        // call refreshItems
    }

    public void loadItems() {
        // the market on spawn will see the correct type of item into its inventory
        // some for each that goes through a list of items and filters based on Market type
    }

    public void refreshItems() {

        //once the pary leaves the items will refresh remove all items from inventory
        // load new items might call the load items method here
    }


}
