package Utility.Slots;

import Items.Item;
// itemSlot.java
// abstract contract for what a slot in a jacket can do
public abstract class ItemSlot<T extends Item> {
    protected T item;
    private boolean isLocked;

    abstract public void equipToSlot(T item);
    abstract public T unequipFromSlot();
    abstract public double getBuffFromSlot(T item);



    public ItemSlot () {
        this.item = null;
        this.isLocked = false;
    }

    public T getItem() {
        return item;
    }

    public void isLockedSwitch(boolean isLocked) {
        this.isLocked = isLocked;
    }

}
