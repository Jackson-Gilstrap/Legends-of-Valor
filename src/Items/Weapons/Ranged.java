package Items.Weapons;

import Items.Weapon;
import Utility.Level;

public class Ranged extends Weapon {

    public Ranged(String name, int price, int damage, int number_of_hands, int level) {
        super(name,price,damage,level,number_of_hands);
    }
}
