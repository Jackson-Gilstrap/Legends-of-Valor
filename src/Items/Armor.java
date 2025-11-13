package Items;


import Interfaces.Equipable;
import Utility.Level;

public class Armor extends Item implements Equipable {
    private final int defence;
//    private final int agility;
    private boolean equipped;

    public Armor(String name, Level level, int price, int defence ) {
        super(name, level, price);
        this.defence = defence;
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
    public int getDefence() {
        return defence;
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
