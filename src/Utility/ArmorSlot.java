package Utility;

import Items.Armor;

public class ArmorSlot extends ItemSlot<Armor>{

    public ArmorSlot() {
        super();
    }

    @Override
    public void equipToSlot(Armor armor) {
        if(getItem() != null){
            this.item = armor;
            System.out.println(item.getName() + " equipped into the armor slot.");
        }else {
            System.out.println("The Armor slot is full please unequip current item + " + item.getName() + "first");
        }

    }

    @Override
    public Armor unequipFromSlot() {
        // set the slot to null
        Armor armor = getItem();
        this.item = null;
        System.out.println(armor.getName() + " unequipped from the armor slot.");
        return armor;
        //return the item
    }

    @Override
    public int getBuffFromSlot(Armor armor) {
        if(getItem() != null){
        return armor.getDefence();
        }
        return 0;
    }
}
