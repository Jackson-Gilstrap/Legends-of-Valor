package Interfaces;

public interface Consumable {
/*
isConsumed returns a boolean checking the durability of the consumable seeing if it has been used or not.
consume doesn't return anything but lowers the durability on the consumable by 100% (or 50% - two uses)
 */
    boolean isConsumed();
    void consume();
}
