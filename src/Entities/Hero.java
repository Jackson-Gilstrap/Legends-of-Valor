package Entities;

public abstract class Hero extends Entity{
    protected int experience_points;
    protected int health;
    protected int mana;
    protected int strength;
    protected int agility;
    protected int dexterity;


    protected Hero(String name){
        super(name);
        this.experience_points = 0;
        this.health = 100; // the following values will be set in the specific hero type class init at 0
        this.mana = 0;
        this.strength = 0;
        this.agility = 0;
        this.dexterity = 0;
    }

    public int getExperiencePoints(){
        return this.experience_points;
    }

    public void setExperiencePoints(int experience_points){
        this.experience_points = experience_points;
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
}
