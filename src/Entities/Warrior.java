package Entities;

public class Warrior extends Hero{
    private int gold;

    public Warrior (String name, int health, int mana, int strength, int dexterity, int agility, int gold, int experience_points) {
        super(name);
        this.health = health;
        this.mana = mana;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.experience_points = experience_points;
        this.gold = gold;
    }

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
                name, level, health, mana, strength, agility, dexterity, experience_points, gold
        );
    }


}
