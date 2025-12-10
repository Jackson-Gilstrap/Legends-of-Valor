package Entities;

import Interfaces.Levelable;
import Utility.Inventory;
import Utility.Jacket;
import Utility.Stats;
import Utility.Wallet;

public abstract class Hero extends Entity implements Levelable {
    private int experience_points, to_next_level;
    private final Inventory inventory;
    private final Stats stats;
    private final Jacket jacket;
    private final Wallet wallet;
    private final char symbol;


    protected Hero(String name, int health, int mana, int attack, int dexterity, double agility, double damage_reduction){
        super(name);
        this.experience_points = 0;
        this.to_next_level = 10 * super.getLevelObj().getCurrentLevel();

        this.inventory = new Inventory();
        this.jacket = new Jacket();
        this.wallet = new Wallet();

        this.stats = new Stats.StatsBuilder()
                .health(health)
                .mana(mana)
                .attack(attack)
                .dexterity(dexterity)
                .agility(agility)
                .damage_reduction(damage_reduction)
                .buildStats();

        this.symbol = 'H';


    }

    protected abstract void applyLevelUpGrowth(Stats stats);

    public Inventory getInventory() {
        return inventory;
    }
    public Wallet getWallet() {return wallet;}
    public Stats getStats() {
        return stats;
    }
    public Jacket getJacket() {
        return jacket;
    }
    public int getExperiencePoints() {return experience_points;}
    public int getExpToNextLevel() {return to_next_level;}
    private void setExpToNextLevel() {
        this.to_next_level = 10 * super.getLevelObj().getCurrentLevel();
    }
    public char getSymbol() {return symbol;}
    public void viewStats(){
        System.out.println(stats.toString());
        jacket.viewJacket();
    }

    public boolean hasSpellEquipped () {
        return jacket.isOccupied(jacket.getSpells());
    }

    public boolean hasPotionEquipped () {
        return jacket.isOccupied(jacket.getPotions());
    }

    // used for after a battle
    @Override
    public void gainExperiencePoints(int experience_points) {
        System.out.println(name + " earned " + experience_points + " points");
        this.experience_points += experience_points;

        while (canLevelUp(this.experience_points)) {
            levelUp();
        }
    }

    @Override
    public void levelUp() {
        int old_level = super.getLevelObj().getCurrentLevel();

        // consume XP for this level
        this.experience_points -= to_next_level;

        super.getLevelObj().increaseLevel();
        int new_level = super.getLevelObj().getCurrentLevel();

        setExpToNextLevel(); // recompute for the *next* level

        System.out.println(name + " has leveled up from " + old_level + " to " + new_level);
        System.out.println();
        System.out.println("Stats before level up\n" + stats.toString());
        System.out.println();

        applyLevelUpGrowth(stats);

        System.out.println("Stats after level up\n" + stats.toString());
    }

    @Override
    public boolean canLevelUp(int experience_points) {
        return this.experience_points >= to_next_level;
    }


}
