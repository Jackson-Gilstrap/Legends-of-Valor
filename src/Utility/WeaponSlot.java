package Utility;


import Items.Weapon;

public class WeaponSlot extends ItemSlot<Weapon> {

    public WeaponSlot() {
        super();
    }

    @Override
    public void equipToSlot(Weapon weapon) {
        if(getItem() != null){
            this.item = weapon;
            System.out.println(item.getName() + " equipped into the weapon slot.");
        }else {
            System.out.println("The Weapon slot is full please unequip current item + " + item.getName() + "first");
        }

    }

    @Override
    public Weapon unequipFromSlot() {
        // set the slot to null
        Weapon weapon = getItem();
        this.item = null;
        System.out.println(weapon.getName() + " unequipped from the weapon slot.");
        return weapon;
        //return the item
    }

    @Override
    public int getBuffFromSlot(Weapon weapon) {
        if(getItem() != null){

            return weapon.getDamage();
        }
        return 0;
    }
}
