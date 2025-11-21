package Builders;


import Items.Armors.Helmet;

public class HelmetBuilder {

    private String type, name;
    private int price, level;
    private double damage_reduction;

    public HelmetBuilder setType(String type) {
        this.type = type;
        return this;

    }
    public HelmetBuilder setName(String name) {
        this.name = name;
        return this;

    }

    public HelmetBuilder setDamageReduction(double damage_reduction) {
        this.damage_reduction = damage_reduction;
        return this;
    }

    public HelmetBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public HelmetBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public Helmet build() {
        return new Helmet(type,name, price, level, damage_reduction);
    }
}
