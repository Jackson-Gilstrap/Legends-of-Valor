package Items.Potions;

import Items.Potion;
import Utility.Level;

public class StrengthPotion extends Potion {

    public StrengthPotion(String name, int price, int level, String type) {
        super(name,price,level,type);
    }
// MODFIY THIS TYPE.EQUALS IS NO LONGER THE SAME
    @Override
    public int setStatbuff() {
        String type = getType();
        if(type.equals("STR")){
            return getStatBuff() + 50;
        }
        return getStatBuff();
    }
}
