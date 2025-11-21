package Builders;

import Items.Armors.Leggings;

public class LeggingsBuilder {

    private String type, name;
    private int price, level;
    private double damage_reduction;

    public LeggingsBuilder setType(String type) {
        this.type = type;
        return this;

    }
    public LeggingsBuilder setName(String name) {
        this.name = name;
        return this;

    }

    public LeggingsBuilder setDamageReduction(double damage_reduction) {
        this.damage_reduction = damage_reduction;
        return this;
    }

    public LeggingsBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public LeggingsBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public Leggings build() {
        return new Leggings(type, name, price, level, damage_reduction);
    }
}
