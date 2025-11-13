package Entities;

import Utility.Stats;

/*
Monster have a level, however they don't gain experience points.
Their level is going to be determined by the average party level
NEXT GOAL FOCUS ON THE STAT BREAKDOWN
 */
public abstract class Monster extends Entity {
    private String name;
    private Stats stats;


    public Monster(String name, int health, int attack, double agility, double damage_reduction) {
        super(name);
        stats = new Stats.StatsBuilder()
                .health(health)
                .attack(attack)
                .agility(agility)
                .damage_reduction(damage_reduction)
                .buildStats();

    }

    public Stats getStats() {
        return stats;
    }




}
