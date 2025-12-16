/**
 * Builder for creating chest armor pieces with defensive values.
 */
package Builders;

import Items.Armors.ChestPiece;

public class ChestplateBuilder {

    private String type, name;
    private int price, level;
    private double damage_reduction;

    public ChestplateBuilder setType(String type) {
        this.type = type;
        return this;

    }
    public ChestplateBuilder setName(String name) {
        this.name = name;
        return this;

    }

    public ChestplateBuilder setDamageReduction(double damage_reduction) {
        this.damage_reduction = damage_reduction;
        return this;
    }

    public ChestplateBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    public ChestplateBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public ChestPiece build() {
        return new ChestPiece(type, name, level, price, damage_reduction);
    }
}
