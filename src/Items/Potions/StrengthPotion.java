package Items.Potions;

import Items.Potion;
import Utility.Level;

public class StrengthPotion extends Potion {

    public StrengthPotion(String name, int price, Level level, String type) {
        super(name,price,level,type);
    }

    @Override
    public int setStatbuff() {
        String type = getType();
        if(type.equals("STR")){
            return getStatBuff() + 50;
        }
        return getStatBuff();
    }
}
