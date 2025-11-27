package Entities;

public class Dragon extends Monster{

    public Dragon(String name, int health, int attack, double damage_reduction, double agility, int level) {
        super(name, health, attack, agility, damage_reduction, level);
    }

    protected Dragon (Dragon dragon){
        super(dragon);
    }

    @Override
    public Dragon copy(){
        return new Dragon(this);
    }

    @Override
    protected double hpGrowthPerLevel() {
        return 0.14;
    }

    @Override
    protected double atkGrowthPerLevel() {
        return 0.12;
    }

    @Override
    public String toString() {
        return String.format(
                "%s (Lvl %d) | HP:%d | ATK:%d DR:%.2f AGI:%.2f",
                super.getName(), super.getLevelObj().getCurrentLevel(),
                super.getStats().getHealth(),
                super.getStats().getAttack(), super.getStats().getDamage_reduction(), super.getStats().getAgility()
        );
    }
}
