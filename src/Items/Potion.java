package Items;

import Enums.PotionType;
import Interfaces.Consumable;
import Utility.Level;

import java.sql.SQLOutput;

public class Potion extends Item implements Consumable {

    private int effect_amount;
    private final PotionType type;
    private boolean consumed;

    public Potion(String name, int price, int level, PotionType type) {
        super(name, price, level);
        this.effect_amount = 0;
        this.type = type;
        this.consumed = false;
    }

    public int getEffectAmount() {
        return effect_amount;
    }

    public PotionType getType() {
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
            System.out.println("Spell used durability - 100 points");
            super.setDurability(super.getDurability() - 100); // 100 for now for one time use
        }
        else {
            System.out.println("Potion used all durability and cannot be used");
        }

    }



}
