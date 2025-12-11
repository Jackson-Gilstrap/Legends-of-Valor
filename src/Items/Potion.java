package Items;

import Enums.PotionType;
import Interfaces.Consumable;
import Utility.Level;

import java.sql.SQLOutput;

import Entities.Hero;

public class Potion extends Item implements Consumable {

    private double effect_amount;
    private final PotionType type;
    private boolean consumed;

    public Potion(String name, int price, int level, PotionType type) {
        super(name, price, level);
        this.effect_amount = 0;
        this.type = type;
        this.consumed = false;
    }

    public double getEffectAmount() {
        return effect_amount;
    }

    public PotionType getType() {
        return type;
    }

    public void setEffectAmount(int hero_level, PotionType type) {
        int base_level = super.getLevel().getCurrentLevel();
        double growth = 1 + 0.15 * (hero_level - base_level);

        if (growth < 1) growth = 1; // never weaker than base, ever

        switch (type) {
            case HP:
            case MP:
            case Attack:
            case Dexterity:
                // FLAT stats
                this.effect_amount = (int)(this.effect_amount * growth);
                break;

            case Agility:
                // %-based stats — keep as double
                this.effect_amount =  this.effect_amount * growth;
                break;
        }
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
        if (this.isConsumed()) {
            System.out.println("Potion used all durability and cannot be used");
            return;
        }
        System.out.println("Spell used durability - 100 points");
        super.setDurability(super.getDurability() - 100); // 100 for now for one time use

    }

    @Override
    public boolean getEquipped(Hero h) {
        return h.getJacket().equipPotion(this);
    }



}
