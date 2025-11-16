package Items.Weapons;

import Items.Weapon;
import Utility.Level;

public class Melee extends Weapon {

    public Melee(String name, int price, int damage, int number_of_hands, int level) {
        super(name,price,damage,level,number_of_hands);
    }
}
