package Utility.Slots;

import Items.Armor;

public class ArmorSlot extends ItemSlot<Armor>{

    public ArmorSlot() {
        super();
    }

    @Override
    public void equipToSlot(Armor armor) {
        if(getItem() == null){
            this.item = armor;
        }

    }

    @Override
    public Armor unequipFromSlot() {
        // set the slot to null
        Armor armor = getItem();
        this.item = null;
        return armor;
        //return the item
    }

    @Override
    public double getBuffFromSlot(Armor armor) {
        if(getItem() != null){
        return  armor.getDamage_reduction();
        }
        return 0;
    }
}
