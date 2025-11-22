package Entities;

public class Dragon extends Monster{

    public Dragon(String name, int health, int attack, double damage_reduction, double agility, int level) {
        super(name, health, attack, agility, damage_reduction, level);
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
