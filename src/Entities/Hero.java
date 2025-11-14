package Entities;

import Interfaces.Levelable;
import Utility.Inventory;
import Utility.Jacket;
import Utility.Stats;

public abstract class Hero extends Entity implements Levelable {
    private int gold, experience_points;
    private final Inventory inventory;
    private final Stats stats;
    private final Jacket jacket;
    /*
    Think about maybe encapsulating stats into its own class so that stat modifications
    Behavior can be pull out of the hero and into the stat manager class for example
    each hero and monster and item has a Stats that represent some amount of stats
     */
    protected Hero(String name, int health, int mana, int attack, int dexterity, double agility, double damage_reduction, int gold){
        super(name);
        this.stats = new Stats.StatsBuilder()
                .health(health)
                .mana(mana)
                .attack(attack)
                .dexterity(dexterity)
                .agility(agility)
                .damage_reduction(damage_reduction)
                .buildStats();
        this.inventory = new Inventory();
        this.jacket = new Jacket();
        this.experience_points = 0;
        this.gold = gold;

    }



    public Inventory getInventory() {
        return inventory;
    }

    public Stats getStats() {
        return stats;
    }

    public Jacket getJacket() {
        return jacket;
    }

    // these two methods used when hero is in the market
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }

    public void viewStats(){
        ;
        System.out.println(("=== Hero Stats ==="));
        System.out.println(("Health: " + getStats().getHealth()));
        System.out.println(("Mana: " + getStats().getMana()));
        System.out.println(("Attack: " +getStats().getAttack()));
        System.out.println(("Dexterity: " + getStats().getDexterity()));
        System.out.println(("Agility: " + getStats().getAgility()));
        System.out.println(("Damage_reduction: " + getStats().getDamage_reduction()));
        System.out.println("======================");

        jacket.viewJacket();



    }


    // used to check if hero can level up
    @Override
    public int getExperiencePoints() {
        return this.experience_points;
    }
    // used for after a battle
    @Override
    public void addExperiencePoints(int experience_points) {
        this.experience_points += experience_points;
        System.out.println(name + " earned " + experience_points + " points");
    }
    // used after a battle and experience point have hit the threshold from can level up method
    @Override
    public void levelUp() {
        if(canLevelUp(this.experience_points)) {
            super.getLevelObj().increaseLevel();
            System.out.println(this.name + " leveled up to " + level.getCurrentLevel());
        }

    }

    @Override
    public boolean canLevelUp(int experience_points) {
        return this.experience_points >= level.getXPForNextLevel();
    }

    @Override
    public int getLevel(){
        return level.getCurrentLevel();
    }


}
