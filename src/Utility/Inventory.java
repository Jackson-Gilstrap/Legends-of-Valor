package Utility;

import Items.Item;

import java.util.ArrayList;
/*
The purpose of the inventory is going to be the middle man between the hero and the market
Items that the hero equips will leave the inventory and be added to the available equipment slots
Items that are being unequipped will be added back to the inventory of the hero
Items being sold to the market will be removed from the inventory
Items being bought will go to the end of the inventory

Methods are add and remove and view
Each hero will have an inventory on creation

 */
public class Inventory {

    private final ArrayList<Item> inventory;// Will be of type Item but String placeholder

    public Inventory() {
        inventory = new ArrayList<>();
    }

    public void addItem(Item item){
        inventory.add(item);
    }

    public void removeItem(Item item){
        inventory.remove(item);
    }

    public void viewInventory() {
        System.out.println("Inventory: ");
        for( int i =0; i <inventory.size(); i++){
            System.out.println("Slot[" + i + "] - Item: " + inventory.get(i).getName());
        }
    }

    public Item getItem(int index){
        return inventory.get(index);
    }
}
