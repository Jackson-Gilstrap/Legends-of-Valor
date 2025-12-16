package Utility;
// Stats.Java
// represents an object that all entities have allows for modular makeups where not all objects need to have every single stat
public class Stats {
    private int health, mana, attack, dexterity, max_health, max_mana;
    private double damage_reduction, agility;


    public Stats(StatsBuilder stats) {
        this.health = stats.health;
        this.mana = stats.mana;
        this.attack = stats.attack;
        this.dexterity = stats.dexterity;
        this.agility = stats.agility;
        this.damage_reduction= stats.damage_reduction;

        this.max_health = stats.max_health == 0 ? stats.health : stats.max_health;
        this.max_mana = stats.max_mana   == 0 ? stats.mana   : stats.max_mana;
    }

    public Stats copy() {
        return new StatsBuilder()
                .health(this.health)
                .mana(this.mana)
                .attack(this.attack)
                .dexterity(this.dexterity)
                .agility(this.agility)
                .damage_reduction(this.damage_reduction)
                .max_health(this.max_health)
                .max_mana(this.max_mana)
                .buildStats();
    }

    public int getHealth() {
        return health;
    }
    public int getMana() {
        return mana;
    }
    public int getAttack() {
        return attack;
    }
    public int getDexterity() {
        return dexterity;
    }
    public double getDamage_reduction() {
        return damage_reduction;
    }
    public double getAgility() {
        return agility;
    }
    public int getMax_health() {return max_health;}
    public int getMax_mana() {return max_mana;}

    public void setHealth(int health) {this.health = Math.min(health, max_health);}
    public void setMana(int mana) {this.mana = Math.min(mana, max_mana);}
    public void setAttack(int attack) {this.attack = attack;}
    public void setDexterity(int dexterity) {this.dexterity = dexterity;}
    public void setDamage_reduction(double damage_reduction) {this.damage_reduction = damage_reduction;}
    public void setAgility(double agility) {this.agility = agility;}
    public void setMax_health(int max_health) {this.max_health = max_health;}
    public void setMax_mana(int max_mana) {this.max_mana = max_mana;}

    public void increaseMaxHealth(int delta, boolean healToFull) {
        this.max_health += delta;
        if (healToFull) {
            this.health = this.max_health;
        }
    }

    public void increaseMaxMana(int delta, boolean fillToFull) {
        this.max_mana += delta;
        if (fillToFull) {
            this.mana = this.max_mana;
        }
    }

    @Override
    public String toString() {
        return "Health: " + health + "/" + max_health + "\n" +
                "Mana: "   + mana   + "/" + max_mana   + "\n" +
                "Attack: " + attack + "\n" +
                "Dexterity: " + dexterity + "\n" +
                "Agility: " + agility + "\n" +
                "Damage Reduction: " + damage_reduction + "\n";
    }

    public static class StatsBuilder {

        private int health, mana, attack, dexterity, max_health, max_mana;
        private double damage_reduction, agility;


        public StatsBuilder health(int health) {
            this.health = health;
            return this;
        }

        public StatsBuilder mana(int mana) {
            this.mana = mana;
            return this;
        }

        public StatsBuilder attack(int attack) {
            this.attack = attack;
            return this;
        }

        public StatsBuilder dexterity(int dexterity) {
            this.dexterity = dexterity;
            return this;
        }

        public StatsBuilder damage_reduction(double damage_reduction) {
            this.damage_reduction = damage_reduction;
            return this;
        }

        public StatsBuilder agility(double agility) {
            this.agility = agility;
            return this;
        }

        public StatsBuilder max_health(int max_health) {
            this.max_health = max_health;
            return this;
        }

        public StatsBuilder max_mana(int max_mana) {
            this.max_mana = max_mana;
            return this;
        }

        public Stats buildStats() {
            return new Stats(this);
        }
    }

}

