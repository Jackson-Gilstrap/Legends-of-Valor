package Market;

import Entities.Hero;
import Game.GameUI;
import Items.Item;
import Parties.Party;
import Utility.Inventory;

// Market.java
// represents a Market provides basic Buy and sell methods
public class Market{
    private final Inventory market_inventory;

    public Market(Inventory market_inventory) {
        this.market_inventory = market_inventory;

    }
    public Inventory getMarketInventory() {
        return market_inventory;
    }

    public boolean canBuy(Hero hero, Item item){
        return hero.getWallet().canMakePurchase(item.getPrice());
    }

    public void buy(Hero hero, Item item){
        market_inventory.removeItem(item);
        hero.getWallet().makeTransaction(item.getPrice());
        hero.getInventory().addItem(item);
    }

    public void sell(Hero hero, Item item){
        hero.getInventory().removeItem(item);
        market_inventory.addItem(item);
        hero.getWallet().addGold(item.getPrice() / 2);
    }
}


