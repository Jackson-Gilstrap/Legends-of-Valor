package Utility;

import Items.Item;

public abstract class ItemSlot<T extends Item> {
    protected T item;

    abstract public void equipToSlot(T item);
    abstract public T unequipFromSlot();
    abstract public int getBuffFromSlot(T item);

    public ItemSlot () {
        this.item = null;
    }

    public T getItem() {
        return item;
    }

}
