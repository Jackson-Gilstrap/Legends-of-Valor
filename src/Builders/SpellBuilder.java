/**
 * Builder for assembling Spell items with level, cost, and type settings.
 */
package Builders;

import Enums.SpellType;
import Items.Spell;

public class SpellBuilder {

    private String name;
    private int price, level, dexterity, mana_cost;
    private SpellType type;

    public SpellBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SpellBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public SpellBuilder setLevel(int level) {
        this.level = level;
        return this;
    }
    public SpellBuilder setDexterity(int dexterity) {
        this.dexterity = dexterity;
        return this;
    }
    public SpellBuilder setMana_cost(int mana_cost) {
        this.mana_cost = mana_cost;
        return this;
    }

    public SpellBuilder setType(SpellType type) {
        this.type = type;
        return this;
    }


    public Spell build() {return new Spell(name, price, level, dexterity, mana_cost, type);}
}
