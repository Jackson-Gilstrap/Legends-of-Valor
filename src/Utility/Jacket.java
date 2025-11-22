package Utility;

import Items.*;
import Items.Armors.ChestPiece;
import Items.Armors.Helmet;
import Items.Armors.Leggings;


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
    private Stats buffStats;

    private ArmorSlot helmet;
    private ArmorSlot chestplate;
    private ArmorSlot leggings;

    private WeaponSlot main;
    private WeaponSlot offhand;

    private SpellSlot spells;
    private PotionSlot potions;


    public Jacket() {
        this.helmet = new ArmorSlot();
        this.chestplate = new ArmorSlot();
        this.leggings = new ArmorSlot();
        this.main = new WeaponSlot();
        this.offhand = new WeaponSlot();
        this.spells = new SpellSlot();
        this.potions = new PotionSlot();

        this.buffStats = new Stats.StatsBuilder()
                .attack(0)
                .dexterity(0)
                .damage_reduction(0)
                .buildStats();
    }


    public boolean equipWeapon(Weapon weapon) {

        boolean isTwoHanded = weapon.getNumber_of_hands() == 2;

        if(isTwoHanded){

            if(isOccupied(main) || isOccupied(offhand)){
                System.out.println("Cannot equip " + weapon.getName() + ". Both hands must be free for a two-handed weapon.");
                return false;
            }
            main.equipToSlot(weapon);
            offhand.disableSlot();
            System.out.println("Equipped " + weapon.getName() + " as a two-handed weapon.");
            return true;
        }

        if (isEmpty(main)) {
            main.equipToSlot(weapon);
            System.out.println("Equipped " + weapon.getName() + " to main hand.");
            return true;
        }

        if (main.getItem().getNumber_of_hands() == 2) {
            System.out.println("Cannot equip " + weapon.getName() +
                    " because a two-handed weapon is equipped.");
            return false;
        }

        if (isEmpty(offhand)) {
            offhand.equipToSlot(weapon);
            System.out.println("Equipped " + weapon.getName() + " to offhand.");
            return true;
        }

        System.out.println("Weapon slot is full");
        return false;
    }
    public boolean equipArmor(Armor armor) {
        if(armor instanceof Helmet){
            if (isEmpty(helmet)) {
                helmet.equipToSlot(armor);
                return true;
            }
            else {
                System.out.println("Helmet slot is full: " + helmet.getItem().getName());
                return false;
            }
        }
        else if(armor instanceof ChestPiece){
            if (isEmpty(chestplate)) {
                chestplate.equipToSlot(armor);
                return true;
            }
            else {
                System.out.println("Chestplate slot is full: " + chestplate.getItem().getName());
                return false;
            }

        }
        else if(armor instanceof Leggings){
            if (isEmpty(leggings)) {
                leggings.equipToSlot(armor);
                return true;
            }
            else {
                System.out.println("Leggings slot is full: " + leggings.getItem().getName());
                return false;

            }
        }

        System.out.println("Armor slots are full");
        return false;


    }
    public boolean equipSpell (Spell spell) {
        if(isEmpty(spells)) {
            spells.equipToSlot(spell);
            return true;
        }
        System.out.println("Spell slot is full: " + spells.getItem().getName());
        return false;
    }
    public boolean equipPotion(Potion potion) {
        if(isEmpty(potions)) {
            potions.equipToSlot(potion);
            return true;
        }
        System.out.println("Potion slot is full: " + potions.getItem().getName());
        return false;
    }
    public Item unequip(ItemSlot<?> slot) {
        if(isOccupied(slot)) {
            System.out.println("Unequipped weapon: " + slot.getItem().getName());
            return slot.unequipFromSlot();
        }
        return null;
    }


    public Stats getBuffStats() {
        return buffStats;
    }

    public Stats updateBuffStats() {
       this.buffStats = new Stats.StatsBuilder()
                .attack(addAttack())
                .dexterity(addDexterity())
                .damage_reduction(addDR())
                .buildStats();

       return this.buffStats;
    }




    private double addDR () {
        double drBuff = 0;
        if(isOccupied(helmet)) {
            drBuff += helmet.getBuffFromSlot(helmet.getItem());
        }
        if(isOccupied(chestplate)) {
            drBuff += chestplate.getBuffFromSlot(chestplate.getItem());
        }
        if(isOccupied(leggings)) {
            drBuff += leggings.getBuffFromSlot(leggings.getItem());
        }

        return drBuff;
    }

    private int addAttack() {
        int attackBuff =0;
        if(isOccupied(main)) {
            attackBuff += main.getBuffFromSlot(main.getItem());
        }
        if(isOccupied(offhand)) {
            attackBuff += offhand.getBuffFromSlot(offhand.getItem());
        }
        return attackBuff;
    }

    private int addDexterity() {
        int dexterityBuff = 0;
        if(isOccupied(spells)) {
            dexterityBuff = spells.getBuffFromSlot(spells.getItem());
        }
        return dexterityBuff;
    }

    public boolean isOccupied(ItemSlot<?> slot) {
        return slot.getItem() != null;
    }

    public boolean isEmpty(ItemSlot<?> slot) {
        return slot.getItem() == null;
    }

    public void viewJacket() {

        System.out.println("=== EQUIPPED ITEMS ===");

        printSlot("Main Hand",   main,      "Attack");
        printSlot("Off Hand",    offhand,   "Attack");
        printSlot("Helmet",      helmet,    "Damage Reduction");
        printSlot("Chestplate",  chestplate,"Damage Reduction");
        printSlot("Leggings",    leggings,  "Damage Reduction");
        printSlot("Spell",       spells,    "Dexterity");
        printSlot("Potion",      potions,   null); // potions don’t buff

        System.out.println("======================");
    }

    private <T extends Item> void printSlot(String name, ItemSlot<T> slot, String buffName) {
        String item = itemName(slot);
        String buff = "";

        if (slot.getItem() != null && buffName != null) {
            double rawBuff = slot.getBuffFromSlot(slot.getItem());

            // If buff is an integer, print without decimals
            if (rawBuff == Math.floor(rawBuff)) {
                buff = String.format(" | +%d %s", (int)rawBuff, buffName);
            } else {
                buff = String.format(" | +%.2f %s", rawBuff, buffName);
            }
        }

        System.out.printf("┃ %-11s : %-20s%s┃\n", name, item, buff);
    }


    private String itemName(ItemSlot<?> slot) {
        return isOccupied(slot) ? slot.getItem().getName() : "Empty";
    }



    public ArmorSlot getHelmet() {
        return helmet;
    }

    public ArmorSlot getChestplate() {
        return chestplate;
    }

    public ArmorSlot getLeggings() {
        return leggings;
    }

    public WeaponSlot getMain() {
        return main;
    }

    public WeaponSlot getOffhand() {
        return offhand;
    }

    public SpellSlot getSpells() {
        return spells;
    }

    public PotionSlot getPotions() {
        return potions;
    }
}
