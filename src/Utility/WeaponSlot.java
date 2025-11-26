package Utility;


import Items.Weapon;

public class WeaponSlot extends ItemSlot<Weapon> {
    private boolean disabled = false;

    public WeaponSlot() {
        super();
    }

    public void disableSlot() {
        this.disabled = true;
        this.item = null;
    }

    public void enableSlot() {
        this.disabled = false;
    }

    public boolean isDisabled() {
        return disabled;
    }

    @Override
    public void equipToSlot(Weapon weapon) {
        if(getItem() == null){
            this.item = weapon;
        }

    }

    @Override
    public Weapon unequipFromSlot() {
        // set the slot to null
        Weapon weapon = getItem();
        this.item = null;
        return weapon;
        //return the item
    }

    @Override
    public double getBuffFromSlot(Weapon weapon) {
        if(getItem() != null){
            return weapon.getAttack();
        }
        return 0;
    }
}
