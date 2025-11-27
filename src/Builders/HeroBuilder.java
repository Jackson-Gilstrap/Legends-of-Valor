package Builders;

import Entities.Paladin;
import Entities.Sorcerer;
import Entities.Warrior;
import Utility.Stats;

public class HeroBuilder {

    private String name;
    private int health , mana, attack, dexterity, gold;
    private double agility, damage_reduction;

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

    public HeroBuilder setAttack(int attack) {
        this.attack = attack;
        return this;
    }
    public HeroBuilder setDexterity(int dexterity) {
        this.dexterity = dexterity;
        return this;
    }
    public HeroBuilder setAgility(double agility) {
        this.agility = agility;
        return this;
    }

    public HeroBuilder setDamageReduction(double damage_reduction) {
        this.damage_reduction = damage_reduction;
        return this;
    }
    public HeroBuilder setGold(int gold) {
        this.gold = gold;
        return this;
    }




    public Warrior buildWarrior() {return new Warrior(name, health, mana, attack, dexterity, agility, damage_reduction);}
    public Sorcerer buildSorcerer() {return new Sorcerer(name, health, mana, attack, dexterity, agility, damage_reduction);}
    public Paladin buildPaladin() {return new Paladin(name, health, mana, attack, dexterity, agility, damage_reduction);}



}
