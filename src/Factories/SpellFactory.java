/**
 * Factory for assembling spells based on provided identifiers.
 */
package Factories;

import Builders.SpellBuilder;
import Enums.SpellType;
import Items.Spell;

public class SpellFactory {

    public Spell createSpell(String name, int price, int level, int dexterity, int mana_cost, SpellType type) {
        return new SpellBuilder()
                .setName(name)
                .setPrice(price)
                .setLevel(level)
                .setDexterity(dexterity)
                .setMana_cost(mana_cost)
                .setType(type)
                .build();
    }
}
