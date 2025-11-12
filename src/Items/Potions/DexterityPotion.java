package Items.Potions;

import Items.Potion;
import Utility.Level;

public class DexterityPotion extends Potion {

    public DexterityPotion(String name, int price, Level level, String type) {
        super(name, price, level, type);
    }

    @Override
    public int setStatbuff() {
        String type = getType();
        if(type.equals("DEX")){
            return getStatBuff() + 50;
        }
        return getStatBuff();
    }
}
