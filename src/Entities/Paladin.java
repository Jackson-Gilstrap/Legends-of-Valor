package Entities;

public class Paladin extends Hero{

    private int health,mana,strength,agility,dexterity, gold;

    public Paladin(String name, int health, int mana, int strength, int agility, int dexterity, int gold) {
        super(name);
        this.health = health;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
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
                name, level.getCurrentLevel(), health, mana, strength, agility, dexterity, getExperiencePoints() , gold
        );
    }

}
