package Items;

import Entities.Hero;

/*
Spells are multi use items that can be equipped before battle by any hero
Base spells are fire, ice and lighting these all have some sort of debuff that takes in an attribute and decreases it
The calculation right now will just be a 10% reduction and for each level of the spell an additional 1% up to lvl 15 granting 25% reduction in a stat
Fire deals with Defense, Ice is for damage and lighting is dodge chance

 */

import Enums.ItemType;
import Enums.SpellType;
import Interfaces.Consumable;
import Utility.Level;

public class Spell extends Item implements Consumable {

    private final int damage;
    private final int mana_cost;
    private boolean consumed;
    private final SpellType type;




    public Spell(String name, int price, int level, int damage, int mana_cost, SpellType type) {
        super(name,price,level, ItemType.SPELL);
        this.damage = damage;
        this.mana_cost = mana_cost;
        this.consumed = false;
        this.type = type;


    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return mana_cost;
    }

    public SpellType getType() {
        return type;
    }



    @Override
    public boolean isConsumed() {
        if (super.getDurability() ==0) {
            this.consumed = true;
        }
        return consumed;
    }

    @Override
    public void consume() {
        if(super.getDurability() > 0) {
            System.out.println("Spell used durability - 25 points");
            super.setDurability(super.getDurability() - 25); // 25 for now change later to fixed value
        }
        else {
        System.out.println("Spell used all durability and cannot be used");
        }

    }

    @Override
    public boolean getEquipped(Hero h) {
        return h.getJacket().equipSpell(this);
    }



}
