package Entities;

public class MonsterBuilder {
    private String name;
    private int health ,damage, defence;
    private float dodge_chance;

   public MonsterBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public MonsterBuilder setHealth(int health) {
       this.health = health;
       return this;
    }
    public MonsterBuilder setDamage(int damage) {
       this.damage = damage;
       return this;
    }
    public MonsterBuilder setDefence(int defence) {
       this.defence = defence;
       return this;
    }
    public MonsterBuilder setDodge_chance(float dodge_chance) {
       this.dodge_chance = dodge_chance;
       return this;
    }

    public Dragon buildDragon() {return new Dragon(name, health, damage, defence, dodge_chance);}
    public Spirit buildSpirit() {return new Spirit(name, health, damage, defence, dodge_chance);}
    public Exoskeleton buildExoskeleton() {return new Exoskeleton(name, health, damage, defence, dodge_chance);}

}
