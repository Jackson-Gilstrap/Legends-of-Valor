package Utility;

import Items.Item;

import java.util.ArrayList;

public class Inventory {

    private final ArrayList<Item> inventory;//

    public Inventory() {
        inventory = new ArrayList<>();
    }

    public void addItem(Item item){
        inventory.add(item);
    }
    public Item removeItem(Item item){
        inventory.remove(item);
        return item;
    }

    public int getInventorySize(){
        return inventory.size();
    }

    public void viewInventory() {
        System.out.println("=== INVENTORY ===");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.printf("Slot %-3d | %s\n", i, inventory.get(i).getName());
        }
        System.out.println("=================\n");
    }


    public Item getItem(int index){
        return inventory.get(index);
    }
}
