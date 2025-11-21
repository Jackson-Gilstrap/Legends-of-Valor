package Builders;

import Enums.PotionType;
import Items.Potion;

public class PotionBuilder {

    private String name;
    private PotionType type;
    private int price, level, effect;

    public PotionBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PotionBuilder setType(PotionType type) {
        this.type = type;
        return this;
    }

    public PotionBuilder setPrice(int price) {
        this.price = price;
        return this;
    }
    public PotionBuilder setLevel(int level) {
        this.level = level;
        return this;
    }
    public PotionBuilder setEffect(int effect) {
        this.effect = effect;
        return this;
    }
    public Potion build() { return new Potion(name, price, level, type);}

}
