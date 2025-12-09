package Items;


import Interfaces.Equipable;
import Utility.Level;

public class Armor extends Item implements Equipable {
    private String type;
    private final double damage_reduction;
    private boolean equipped;

    public Armor(String type,String name, int level, int price, double damage_reduction ) {
        super(name, price, level);
        this.type= type;
        this.damage_reduction = damage_reduction;
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
    public String getType() {
        return type;
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

    @Override
    public String toString() {
        return String.format(
                "Armor{type='%s', name='%s', level=%d, price=%d, reduction=%.2f, equipped=%s}",
                type,
                getName(),
                getLevel().getCurrentLevel(),
                getPrice(),
                damage_reduction,
                equipped
        );
    }

}
