package Utility.Slots;

import Items.Spell;

public class SpellSlot extends ItemSlot<Spell> {

    public SpellSlot() {
        super();
    }

    @Override
    public void equipToSlot(Spell spell) {
        if(getItem() == null){
            this.item = spell;
        }
    }

    @Override
    public Spell unequipFromSlot() {
        // set the slot to null
        Spell spell = getItem();
        this.item = null;
        return spell;
        //return the item
    }

    @Override
    public double getBuffFromSlot(Spell spell) {
        if(getItem() != null){
            return spell.getDamage();
        }
        return 0;
    }
}
