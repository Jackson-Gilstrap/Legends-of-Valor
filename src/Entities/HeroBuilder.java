package Entities;

public class HeroBuilder {

    private String name;
    private int health , mana, strength, dexterity, agility, gold, experience;

    public HeroBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public HeroBuilder setHealth(int health) {
        this.health = health;
        return this;
    }
    public HeroBuilder setMana(int mana) {
        this.mana = mana;
        return this;
    }

    public HeroBuilder setStrength(int strength) {
        this.strength = strength;
        return this;
    }
    public HeroBuilder setDexterity(int dexterity) {
        this.dexterity = dexterity;
        return this;
    }
    public HeroBuilder setAgility(int agility) {
        this.agility = agility;
        return this;
    }
    public HeroBuilder setGold(int gold) {
        this.gold = gold;
        return this;
    }



    public Warrior buildWarrior() {return new Warrior(name, health, mana, strength, dexterity, agility, gold);}
    public Sorcerer buildSorcerer() {return new Sorcerer(name, health, mana, strength, dexterity, agility, gold);}
    public Paladin buildPaladin() {return new Paladin(name, health, mana, strength, dexterity, agility, gold);}



}
