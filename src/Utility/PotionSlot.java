package Utility;

import Items.Potion;

public class PotionSlot extends ItemSlot<Potion>{
    public PotionSlot() {
        super();
    }
    @Override
    public void equipToSlot(Potion Potion) {
        if(getItem() != null){
            this.item = Potion;
            System.out.println(item.getName() + " equipped into the Potion slot.");
        }else {
            System.out.println("The Potion slot is full please unequip current item + " + item.getName() + "first");
        }

    }

    @Override
    public Potion unequipFromSlot() {
        // set the slot to null
        Potion Potion = getItem();
        this.item = null;
        System.out.println(Potion.getName() + " unequipped from the Potion slot.");
        return Potion;
        //return the item
    }

    @Override
    public int getBuffFromSlot(Potion Potion) {
        if(getItem() != null){
            return Potion.getStatBuff();
        }
        return 0;
    }
}
