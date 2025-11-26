package Utility;

import Items.Potion;

public class PotionSlot extends ItemSlot<Potion>{
    public PotionSlot() {
        super();
    }
    @Override
    public void equipToSlot(Potion Potion) {
        if(getItem() == null){
            this.item = Potion;
        }

    }

    @Override
    public Potion unequipFromSlot() {
        // set the slot to null
        Potion Potion = getItem();
        this.item = null;
        return Potion;
        //return the item
    }

    @Override
    public double getBuffFromSlot(Potion Potion) {
        if(getItem() != null){
            return Potion.getEffectAmount();
        }
        return 0.00;
    }
}
