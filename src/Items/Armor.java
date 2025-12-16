package Items;


import Entities.Hero;
import Enums.ItemType;

import Utility.Level;
//Armor.java
// represents a piece of armor in the game
public class Armor extends Item{
    private final String type;
    private final double damage_reduction;


    public Armor(String type,String name, int level, int price, double damage_reduction ) {
        super(name, price, level, ItemType.ARMOR);
        this.type= type;
        this.damage_reduction = damage_reduction;

    }

    public String getName() {
        return super.getName();
    }
    public int getPrice() {
        return super.getPrice();
    }
    public Level getLevel() {
        return super.getLevel();
    }
    public String getType() {
        return type;
    }
    public double getDamage_reduction() {
        return damage_reduction;
    }



    @Override
    public String toString() {
        return String.format(
                "Armor{type='%s', name='%s', level=%d, price=%d, reduction=%.2f}",
                type,
                getName(),
                getLevel().getCurrentLevel(),
                getPrice(),
                damage_reduction

        );
    }

    @Override
    public boolean getEquipped(Hero h) {
        return h.getJacket().equipArmor(this);
    }

}
