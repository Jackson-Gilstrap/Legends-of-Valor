package Items;

import Entities.Hero;
import Enums.ItemType;
import Utility.Level;

public abstract class Item {
    private int durability;
    private final String name;
    private final Level level;
    private final int price;
    protected final ItemType itemType;

    // Regular Item from shop
    public Item(String name, int price, int level, ItemType itemType ) {
        this.durability = 100;
        this.name = name;
        this.level = new Level(level);
        this.price = price;
        this.itemType = itemType;

    }

    public int getDurability() {
        return durability;
    }

    public Level getLevel() {
        return level;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public int getPrice() {
        return price;
    }

    public void setDurability(int durability) {
        if (durability > 100) {
            this.durability = 100;
        }
        this.durability = durability;
    }

    public String getName() {
        return name;
    }

    /**
     * The item will call the hero to equip itself.
     * @param h
     * @return
     */
    public abstract boolean getEquipped(Hero h);

}
