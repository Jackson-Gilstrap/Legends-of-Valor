package Entities;

import Utility.Stats;

/*
Monster have a level, however they don't gain experience points.
Their level is going to be determined by the average party level
NEXT GOAL FOCUS ON THE STAT BREAKDOWN
 */
public abstract class Monster extends Entity {
    private Stats stats;
    private int gold_drop, experience_drop;


    public Monster(String name, int health, int attack, double agility, double damage_reduction, int level) {
        super(name, level);
        stats = new Stats.StatsBuilder()
                .health(health)
                .attack(attack)
                .agility(agility)
                .damage_reduction(damage_reduction)
                .buildStats();
        this.gold_drop = 20 * (int)(Math.pow(level, 2));
        this.experience_drop = 10 * (int) (Math.pow(level,1.5));

    }

    protected Monster(Monster monster) {
        super(monster.getName(), monster.getLevelObj().getCurrentLevel());
        this.stats = monster.stats.copy();
        this.gold_drop = monster.gold_drop;
        this.experience_drop = monster.experience_drop;
    }

    public Stats getStats() {
        return stats;
    }

    public int getGoldDrop() {
        return gold_drop;
    }
    public int getExperienceDrop() {return experience_drop;}
    public void setGoldDrop() {this.gold_drop = 20 * (int)(Math.pow(super.getLevelObj().getCurrentLevel(), 2));}
    public void setExperienceDrop() {this.experience_drop  = 20 * (int)(Math.pow(super.getLevelObj().getCurrentLevel(), 1.5));}


    protected double hpGrowthPerLevel()    { return 0.12; }   // +12% HP per level
    protected double atkGrowthPerLevel()   { return 0.08; }   // +8% ATK per level
    protected double agiGrowthPerLevel()   { return 0.015; }  // +0.015 AGI per level
    protected double drGrowthPerLevel()    { return 0.008; }  // +0.008 DR per level

    // caps so things don't get stupid
    protected double maxAgilityCap()       { return 0.75; }
    protected double maxDamageReductionCap(){ return 0.60; }

    public void rescaleStatsForLevel() {
        int level = getLevelObj().getCurrentLevel();
        int levelDelta = Math.max(0, level - 1);

        double hp_growth = hpGrowthPerLevel();
        double atk_growth = atkGrowthPerLevel();
        double agi_growth = agiGrowthPerLevel();
        double dr_growth  = drGrowthPerLevel();


        int scaledHealth = (int) Math.max(1, Math.round(stats.getHealth()) * (1 + hp_growth * levelDelta));

        int scaledAttack = (int) Math.max(1, Math.round(stats.getAttack()) * (1 + atk_growth * levelDelta));


        double scaledAgility = Math.min(maxAgilityCap(), stats.getAgility()) + agi_growth * levelDelta;

        double scaledDR = Math.min(maxDamageReductionCap(), stats.getDamage_reduction()) + dr_growth * levelDelta;

        stats.setMax_health(scaledHealth);
        stats.setHealth(scaledHealth);

        stats.setAttack(scaledAttack);
        stats.setAgility(scaledAgility);
        stats.setDamage_reduction(scaledDR);
    }
    public abstract Monster copy();
}
