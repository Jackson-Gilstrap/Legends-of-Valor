package Items.Potions;

import Items.Potion;
import Utility.Level;

public class ManaPotion extends Potion {
    public ManaPotion (String name, int price, int level, String type ){
        super(name, price, level, type);
    }

    @Override
    public int setStatbuff() {
        String type = getType();
        if(type.equals("MP")){
            return getStatBuff() + 100;
        }
        return getStatBuff();
    }
}
