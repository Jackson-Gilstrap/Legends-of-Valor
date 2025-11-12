package Entities;

import Interfaces.Levelable;
import Utility.Inventory;

public abstract class Hero extends Entity implements Levelable {
    private int health,mana,strength,agility,dexterity, gold, experience_points;
    private final Inventory inventory;

    protected Hero(String name, int health, int mana, int strength, int agility, int dexterity, int gold){
        super(name);
        this.health = health;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.gold = gold;
        this.experience_points = 0;
        this.inventory = new Inventory();
//
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    // these two methods used when hero is in the merket
    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Inventory getInventory() {
        return inventory;
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
