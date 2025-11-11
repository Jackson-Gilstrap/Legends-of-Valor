package Entities;
/*
Monster have a level, however they don't gain experience points.
Their level is going to be determined by the average party level
 */
public abstract class Monster extends Entity {
    private String name;
    private int health, damage, defence;
    private float dodge_chance;

    public Monster(String name,int health, int damage, int defence, float dodge_chance) {
        super(name);
        this.health = health;
        this.damage = damage;
        this.defence = defence;
        this.dodge_chance = dodge_chance;

    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
       this.defence = defence;
    }

    public float getDodge_chance() {
        return dodge_chance;
    }
    public void setDodge_chance(float dodge_chance) {
        this.dodge_chance = dodge_chance;
    }




}
