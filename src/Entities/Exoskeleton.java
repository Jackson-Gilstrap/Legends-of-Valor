package Entities;

public class Exoskeleton extends Monster{

    public Exoskeleton(String name, int health, int attack, double damage_reduction, double agility, int level) {
        super(name, health, attack, agility, damage_reduction, level);
    }


    protected Exoskeleton (Exoskeleton exoskeleton){
        super(exoskeleton);
    }

    @Override
    public Monster copy(){
        return new Exoskeleton(this);
    }

    @Override
    protected double drGrowthPerLevel() {
        return 0.018;
    }

    @Override
    protected double hpGrowthPerLevel() {
        return 0.10;
    }

    @Override
    protected double atkGrowthPerLevel() {
        return 0.06;
    }

    @Override
    protected double maxDamageReductionCap() {
        return 0.70;
    }

    @Override
    public String toString() {
        return String.format(
                "%s (Lvl %d) | HP:%d | DAM:%d DEF:%f DOG:%.2f",
                super.getName(), super.getLevelObj().getCurrentLevel(),
                super.getStats().getHealth(),
                super.getStats().getAttack(), super.getStats().getDamage_reduction(), super.getStats().getAgility()
        );
    }
}
