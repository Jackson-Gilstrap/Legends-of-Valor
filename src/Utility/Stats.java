package Utility;

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

    public Stats addStats(Stats otherStats) {
        return new StatsBuilder()
                .health(this.health + otherStats.health)
                .mana(this.mana +otherStats.mana)
                .attack(this.attack + otherStats.attack)
                .dexterity(this.dexterity + otherStats.dexterity)
                .agility(this.agility + otherStats.agility)
                .damage_reduction(this.damage_reduction + otherStats.damage_reduction)
                .max_health(this.max_health + otherStats.max_health)
                .max_mana(this.max_mana + otherStats.max_mana)
                .buildStats();
    }
    public Stats subtractStats(Stats otherStats) {
        return new StatsBuilder()
                .health(this.health - otherStats.health)
                .mana(this.mana -otherStats.mana)
                .attack(this.attack - otherStats.attack)
                .dexterity(this.dexterity - otherStats.dexterity)
                .agility(this.agility - otherStats.agility)
                .damage_reduction(this.damage_reduction - otherStats.damage_reduction)
                .max_health(this.max_health - otherStats.max_health)
                .max_mana(this.max_mana - otherStats.max_mana)
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

    public void setHealth(int health) {this.health = Math.min(health, max_health);}
    public void setMana(int mana) {this.mana = Math.min(mana, max_mana);}
    public void setAttack(int attack) {this.attack = attack;}
    public void setDexterity(int dexterity) {this.dexterity = dexterity;}
    public void setDamage_reduction(double damage_reduction) {this.damage_reduction = damage_reduction;}
    public void setAgility(double agility) {this.agility = agility;}

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

