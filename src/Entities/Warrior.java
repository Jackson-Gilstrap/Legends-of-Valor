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


    @Override
    public String toString() {
        return String.format(
                "%s (Lvl %d) | HP:%d MP:%d | STR:%d AGI:%d DEX:%d | XP:%d Gold:%d",
                name, level.getCurrentLevel(), health, mana, strength, agility, dexterity, getExperiencePoints() , gold
        );
    }


}
