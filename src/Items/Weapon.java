package Items;

import Entities.Hero;
import Interfaces.Equipable;
import Utility.Level;
/*
Need to think about base values of starting equipment
 */
public class Weapon extends Item implements Equipable {
    private final int attack;
    private final int number_of_hands;
    private boolean equipped;

    public Weapon(String name, int price, int attack, int level, int number_of_hands) {
        super(name, price, level);
        this.attack = attack;
        this.number_of_hands = number_of_hands;
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


    @Override
    public String toString() {
        return String.format(
                "Weapon{name='%s', price=%d, attack=%d, level=%d, hands=%d, equipped=%s}",
                getName(),
                getPrice(),
                attack,
                getLevel().getCurrentLevel(),
                number_of_hands,
                equipped
        );
    }

    @Override
    public boolean getEquipped(Hero h) {
        return h.getJacket().equipWeapon(this);
    }

}
