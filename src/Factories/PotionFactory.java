package Factories;

import Builders.PotionBuilder;
import Enums.PotionType;
import Items.Potion;

public class PotionFactory {

    public Potion createPotion(String name, int price, int level, PotionType type, int effect) {
        return new PotionBuilder()
                .setName(name)
                .setPrice(price)
                .setLevel(level)
                .setType(type)
                .setEffect(effect)
                .build();
    }
}
