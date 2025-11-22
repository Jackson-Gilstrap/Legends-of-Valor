package Builders;

import Entities.Dragon;
import Entities.Exoskeleton;
import Entities.Spirit;

public class MonsterBuilder {
    private String name;
    private int health, attack, level;
    private double agility, damage_reduction;

   public MonsterBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public MonsterBuilder setHealth(int health) {
       this.health = health;
       return this;
    }
    public MonsterBuilder setAttack(int attack) {
       this.attack = attack;
       return this;
    }

    public MonsterBuilder setAgility(double agility) {
       this.agility = agility;
       return this;
    }

    public MonsterBuilder setDamageReduction(double damage_reduction) {
       this.damage_reduction = damage_reduction;
       return this;
    }

    public MonsterBuilder setLevel(int level) {
       this.level = level;
       return this;
    }



    public Dragon buildDragon() {return new Dragon(name, health, attack, agility, damage_reduction, level);}
    public Spirit buildSpirit() {return new Spirit(name, health, attack, agility, damage_reduction, level);}
    public Exoskeleton buildExoskeleton() {return new Exoskeleton(name, health, attack, agility, damage_reduction, level);}

}
