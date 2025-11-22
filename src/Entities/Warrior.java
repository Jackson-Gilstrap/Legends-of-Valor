package Entities;

import Utility.Stats;
import Utility.Wallet;

/*
Need to set up the favored attributes at a later time for all hero classes once I can read from file
 */
public class Warrior extends Hero{

    public Warrior (String name, int health, int mana, int attack, int dexterity, double agility, double damage_reduction, int gold) {
        super(name, health, mana, attack, dexterity, agility, damage_reduction);
    }

    @Override
    public String toString() {
        Stats s = super.getStats();
        Wallet w = super.getWallet();
        return String.format(
                "%s (Lvl %d) | HP:%d MP:%d | ATK:%d DEX:%d | AGI:%.2f DR:%.2f | XP:%d/%d | Gold:%d",
                name,
                level.getCurrentLevel(),

                s.getHealth(),
                s.getMana(),
                s.getAttack(),
                s.getDexterity(),
                s.getAgility(),
                s.getDamage_reduction(),

                super.getExperiencePoints(),
                super.getExpToNextLevel(),

                w.getCurrent_gold()
        );
    }


}
