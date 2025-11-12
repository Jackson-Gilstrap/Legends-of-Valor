package Utility;

import javax.swing.*;

public class Jacket {
    /*
    The idea behind this item is that the hero owns this and then can equip items from the inventory to the jacket
    giving the hero a boost in base stats depending on the items equipped
    Should have certain slots
    2 slots for weapons (representing hands)
    3 slots for armor (head,chest,legs)
    1 for spell
    1 for potion
    Can check if item can be inserted into by checking typeof item == typeof slot
    Can check for two handed or one handed by a disabled flag on weapon slots
     */

    private int baseHealth, baseMana, baseStrength, baseDexterity, baseAgility;


    public Jacket() {
        //slots for items
        ArmorSlot helmet = new ArmorSlot();
        ArmorSlot chestplate = new ArmorSlot();
        ArmorSlot legging = new ArmorSlot();
        WeaponSlot main = new WeaponSlot();
        WeaponSlot offhand = new WeaponSlot();
        SpellSlot spellSlot = new SpellSlot();
        PotionSlot potionSlot = new PotionSlot();

        this.baseHealth = helmet.getBuffFromSlot(helmet.getItem()) + chestplate.getBuffFromSlot(chestplate.getItem()) + legging.getBuffFromSlot(legging.getItem());
        this.baseMana = 0;
        this.baseStrength = 0;
        this.baseDexterity = 0;
        this.baseAgility = 0;

    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getBaseMana() {
        return baseMana;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public int getBaseDexterity() {
        return baseDexterity;
    }

    public int getBaseAgility() {
        return baseAgility;
    }
}
