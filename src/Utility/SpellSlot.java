package Utility;

import Items.Spell;

public class SpellSlot extends ItemSlot<Spell> {

    public SpellSlot() {
        super();
    }

    @Override
    public void equipToSlot(Spell spell) {
        if(getItem() != null){
            this.item = spell;
            System.out.println(item.getName() + " equipped into the spell slot.");
        }else {
            System.out.println("The Spell slot is full please unequip current item + " + item.getName() + "first");
        }

    }

    @Override
    public Spell unequipFromSlot() {
        // set the slot to null
        Spell spell = getItem();
        this.item = null;
        System.out.println(spell.getName() + " unequipped from the spell slot.");
        return spell;
        //return the item
    }

    @Override
    public int getBuffFromSlot(Spell spell) {
        if(getItem() != null){
            return spell.getDamage();
        }
        return 0;
    }
}
