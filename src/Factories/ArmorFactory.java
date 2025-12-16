/**
 * Factory that creates armor pieces based on provided identifiers.
 */
package Factories;

import Builders.ChestplateBuilder;
import Builders.HelmetBuilder;
import Builders.LeggingsBuilder;
import Items.Armor;
import Utility.Stats;

public class ArmorFactory {

    public Armor createArmor(String type, String name, Stats stats, int price, int level) {
        switch (type) {
            case "Helmet":
                return new HelmetBuilder()
                        .setName(name)
                        .setPrice(price)
                        .setLevel(level)
                        .setDamageReduction(stats.getDamage_reduction())
                        .build();
            case "Chestplate":
                return new ChestplateBuilder()
                        .setName(name)
                        .setPrice(price)
                        .setLevel(level)
                        .setDamageReduction(stats.getDamage_reduction())
                        .build();
            case "Leggings":
                return new LeggingsBuilder()
                        .setName(name)
                        .setPrice(price)
                        .setLevel(level)
                        .setDamageReduction(stats.getDamage_reduction())
                        .build();
            default:
                return null;
        }
    }
}
