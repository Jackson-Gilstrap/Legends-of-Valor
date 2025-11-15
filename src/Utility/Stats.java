package Utility;

/*
    This class is responsible for handling and storing objects stats
    taking care of all the actions that can happen to a single stat

    Stat breakdown:
    Health -  total health of the entity
    Mana - total mana of the entity
    Atk -  flat damage value the entity has(increased by strength and decreased by defense)
    Dex - flat value that increases spell damage
    Agility - percentage that increases dodge chance
    Damage reduction - percentage that reduces incoming attack damage
 */




public class Stats {
    private int health, mana, attack, dexterity;
    private double damage_reduction, agility;

    // empty constructor
    public Stats(StatsBuilder stats) {
        this.health = stats.health;
        this.mana = stats.mana;
        this.attack = stats.attack;
        this.dexterity = stats.dexterity;
        this.agility = stats.agility;
        this.damage_reduction= stats.damage_reduction;
    }

    public static class StatsBuilder {

        private int health;
        private int mana;
        private int attack;
        private int dexterity;
        private double damage_reduction;
        private double agility;


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

        public Stats buildStats() {
            return new Stats(this);
        }
    }

    public Stats addStats(Stats otherStats) {
        return new StatsBuilder()
                .health(this.health + otherStats.health)
                .mana(this.mana +otherStats.health)
                .attack(this.attack + otherStats.attack)
                .dexterity(this.dexterity + otherStats.dexterity)
                .agility(this.agility + otherStats.agility)
                .damage_reduction(this.damage_reduction + otherStats.damage_reduction)
                .buildStats();
    }
    public Stats subtractStats(Stats otherStats) {
        return new StatsBuilder()
                .health(this.health - otherStats.health)
                .mana(this.mana -otherStats.health)
                .attack(this.attack - otherStats.attack)
                .dexterity(this.dexterity - otherStats.dexterity)
                .agility(this.agility - otherStats.agility)
                .damage_reduction(this.damage_reduction - otherStats.damage_reduction)
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Health: " + health + "\n");
        stringBuilder.append("Mana: " + mana + "\n");
        stringBuilder.append("Attack: "+ attack + "\n");
        stringBuilder.append("Dexterity: " + dexterity + "\n");
        stringBuilder.append("Agility: " + agility + "\n");
        stringBuilder.append("Damage_reduction: " + damage_reduction + "\n");
        return stringBuilder.toString();
    }

}

