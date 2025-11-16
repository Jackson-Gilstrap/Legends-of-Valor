package Items;

import Interfaces.Equipable;
import Utility.Level;
/*
Need to think about base values of starting equipment
 */
public abstract class Weapon extends Item implements Equipable {
    private final int attack;
    private final int number_of_hands;
    private boolean equipped;

    public Weapon(String name, int price, int attack, int level, int number_of_hands) {
        super(name, level, price);
        this.attack = attack;
        this.number_of_hands = number_of_hands;
        this.equipped = false;

    }

    public Weapon(String name, int number_of_hands) {
        super(name);
        this.number_of_hands = number_of_hands;
        this.attack = 100; //some base number idk yet
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
    public int getAttack() {
        return this.attack;
    }

    public int getNumber_of_hands() {
        return this.number_of_hands;
    }

    @Override
    public boolean isEquiped() {
        return this.equipped;
    }
    @Override
    public void equip() {
        this.equipped = true;
    }
    @Override
    public void unequip() {
        this.equipped = false;
    }


}
