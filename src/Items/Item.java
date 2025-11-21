package Items;

import Utility.Level;

public abstract class Item {
    private int durability;
    private final String name;
    private final Level level;
    private final int price;

    // Regular Item from shop
    public Item(String name, int price, int level ) {
        this.durability = 100;
        this.name = name;
        this.level = new Level(level);
        this.price = price;
    }

    public int getDurability() {
        return durability;
    }

    public Level getLevel() {
        return level;
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


}
