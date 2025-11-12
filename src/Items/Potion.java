package Items;

import Interfaces.Consumable;
import Utility.Level;

public abstract class Potion extends Item implements Consumable {

    private int statBuff;
    private final String type;
    private boolean consumed;

    public Potion(String name, int price, Level level, String type) {
        super(name, level, price);
        this.statBuff = 0;
        this.type = type;
        this.consumed = false;
    }

    public int  getStatBuff() {
        return statBuff;
    }

    public String getType() {
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




    public abstract int setStatbuff();
}
