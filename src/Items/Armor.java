package Items;


import Interfaces.Equipable;
import Utility.Level;

public class Armor extends Item implements Equipable {
    private final double damage_reduction;
//    private final int agility;
    private boolean equipped;

    public Armor(String name, int level, int price, double damage_reduction ) {
        super(name, level, price);
        this.damage_reduction = Armor.this.damage_reduction;
//        this.agility =  agility;
        this.equipped = false;
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
    public double getDamage_reduction() {
        return damage_reduction;
    }

    @Override
    public boolean isEquiped() {
        return equipped;
    }

    @Override
    public void equip() {
        equipped = true;
    }

    @Override
    public void unequip() {
        equipped = false;
    }
}
