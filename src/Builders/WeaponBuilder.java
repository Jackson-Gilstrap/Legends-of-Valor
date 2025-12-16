/**
 * Builder for producing Weapon items with attack, level, and hand requirements.
 */
package Builders;

import Items.Weapon;

public class WeaponBuilder {

    private String name;
    private int attack, price, level, hands;

    public WeaponBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public WeaponBuilder setAttack(int attack) {
        this.attack = attack;
        return this;
    }

    public WeaponBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public WeaponBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public WeaponBuilder setHands(int hands) {
        this.hands = hands;
        return this;
    }


    public Weapon buildWeapon() {return new Weapon(name, price, attack ,level, hands);}

}
