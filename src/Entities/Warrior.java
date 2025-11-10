package Entities;

import java.util.logging.Level;

public class Warrior extends Hero{
    private int health, mana, strength, dexterity, agility, gold, experience;

    public Warrior (String name, int health, int mana, int strength, int dexterity, int agility, int gold, int experience_points) {
        super(name);
        this.health = health;
        this.mana = mana;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.gold = gold;
    }

    // these two methods used when hero is in the merket
    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    // used to check if hero can level up
    @Override
    public int getExperiencePoints() {
        return this.experience;
    }
     // used for after a battle
    @Override
    public void addExperiencePoints(int experience_points) {
        this.experience += experience_points;
        System.out.println(name + " earned " + experience + " points");
    }
    // used after a battle and experience point have hit the threshold from can level up method
    @Override
    public void levelUp() {
        if(canLevelUp(this.experience)) {
        super.getLevelObj().increaseLevel();
            System.out.println(this.name + " leveled up to " + level.getCurrentLevel());
        }

    }

    @Override
    public boolean canLevelUp(int experience_points) {
        return this.experience >= level.getXPForNextLevel();
    }

    @Override
    public String toString() {
        return String.format(
                "%s (Lvl %d) | HP:%d MP:%d | STR:%d AGI:%d DEX:%d | XP:%d Gold:%d",
                name, level.getCurrentLevel(), health, mana, strength, agility, dexterity, getExperiencePoints() , gold
        );
    }


}
