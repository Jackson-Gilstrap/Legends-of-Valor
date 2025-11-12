package Items;

/*
Spells are multi use items that can be equipped before battle by any hero
Base spells are fire, ice and lighting these all have some sort of debuff that takes in an attribute and decreases it
The calculation right now will just be a 10% reduction and for each level of the spell an additional 1% up to lvl 15 granting 25% reduction in a stat
Fire deals with Defense, Ice is for damage and lighting is dodge chance

 */

import Interfaces.Consumable;
import Utility.Level;

public class Spell extends Item implements Consumable {

    private final int damage;
    private final int mana_cost;
    private boolean consumed;
    private final Type type;


    public enum Type {
        FIRE {
            @Override
            public String effect() {
                return "The Fire Spell reduces the defense of a monster it is used on";
            }
            @Override
            public double debuffMultiplier(Level level) {
                return 0.10 + (level.getCurrentLevel()- 1) * 0.01;
            }

        },
        ICE {
            @Override
            public String effect() {
                return "The Ice Spell reduces the damage of a monster it is used on";
            }
            @Override
            public double debuffMultiplier(Level level) {
                return 0.10 + (level.getCurrentLevel()- 1) * 0.01;
            }
        },
        LIGHTNING {
            @Override
            public String effect() {
                return "The Lightning Spell reduces the dodge chance of a monster it is used on";
            }
            @Override
            public double debuffMultiplier(Level level) {
                return 0.10 + (level.getCurrentLevel()- 1) * 0.01;
            }
        };

        // 🔸 This MUST be inside the enum body, after the semicolon.
        public abstract String effect();
        public abstract double debuffMultiplier(Level level);
    }

    public Spell(String name, int price, Level level, int damage, int mana_cost, Type type) {
        super(name,level,price);
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

    public Type getType() {
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



}
