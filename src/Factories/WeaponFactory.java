/**
 * Factory that creates weapon items from catalog identifiers.
 */
package Factories;

import Builders.WeaponBuilder;
import Items.Weapon;
import Utility.Stats;

public class WeaponFactory {

    public Weapon createWeapon(String name, Stats stats, int number_of_hands, int price, int level) {
        return new WeaponBuilder()
                .setName(name)
                .setPrice(price).setLevel(level)
                .setAttack(stats.getAttack())
                .setHands(number_of_hands)
                .buildWeapon();
    }
}
