/**
 * Spirit monster type defined by agile, evasive traits.
 */
package Entities;

public class Spirit extends Monster{

    public Spirit(String name, int health, int attack, double damage_reduction, double agility, int level) {
        super(name, health, attack, agility, damage_reduction, level);
    }

    protected Spirit (Spirit spirit){
        super(spirit);
    }

    @Override
    public Spirit copy(){
        return new Spirit(this);
    }


    @Override
    protected double agiGrowthPerLevel() {
        return 0.035;
    }

    @Override
    protected double hpGrowthPerLevel() {
        return 0.08;
    }

    @Override
    protected double atkGrowthPerLevel() {
        return 0.07;
    }

    @Override
    protected double drGrowthPerLevel() {
        return 0.006;
    }

    @Override
    protected double maxAgilityCap() {
        return 0.85;
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
