package Items;

import Entities.Hero;
import Enums.ItemType;
import Utility.Level;
// Weapon.java
// represent a weapon in the game
public class Weapon extends Item {
    private final int attack;
    private final int number_of_hands;

    public Weapon(String name, int price, int attack, int level, int number_of_hands) {
        super(name, price, level, ItemType.WEAPON);
        this.attack = attack;
        this.number_of_hands = number_of_hands;

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
    public String toString() {
        return String.format(
                "Weapon{name='%s', price=%d, attack=%d, level=%d, hands=%d}",
                getName(),
                getPrice(),
                attack,
                getLevel().getCurrentLevel(),
                number_of_hands

        );
    }

    @Override
    public boolean getEquipped(Hero h) {
        return h.getJacket().equipWeapon(this);
    }

}
