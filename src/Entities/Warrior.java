/**
 * Warrior hero class focused on melee combat attributes.
 */
package Entities;

import Utility.Stats;
import Utility.Wallet;

/*
Need to set up the favored attributes at a later time for all hero classes once I can read from file
 */
public class Warrior extends Hero{

    public Warrior (String name, int health, int mana, int attack, int dexterity, double agility, double damage_reduction) {
        super(name, health, mana, attack, dexterity, agility, damage_reduction);
    }

    @Override
    public void applyLevelUpGrowth(Stats stats) {
        // Define the multipliers
        double hp_growth = 0.07;
        double mana_growth = 0.03;
        double attack_growth = 0.07;
        double dexterity_growth = 0.03;
        double agility_growth = 0.03;
        double damage_reduction_growth = 0.01;


        int hp_delta  = (int) Math.max(1, Math.round(stats.getMax_health() * hp_growth));
        int mp_delta  = (int) Math.max(1, Math.round(stats.getMax_mana()   * mana_growth));
        int atk_delta = (int) Math.max(1, Math.round(stats.getAttack()     * attack_growth));
        int dex_delta = (int) Math.max(1, Math.round(stats.getDexterity()  * dexterity_growth));

        stats.increaseMaxHealth(hp_delta, true);
        stats.increaseMaxMana(mp_delta, true);

        stats.setAttack(stats.getAttack() + atk_delta);
        stats.setDexterity(stats.getDexterity() + dex_delta);

        stats.setAgility(Math.min(0.45, stats.getAgility() + agility_growth));
        stats.setDamage_reduction(Math.min(0.40, stats.getDamage_reduction() + damage_reduction_growth));


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
