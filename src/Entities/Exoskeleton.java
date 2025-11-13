package Entities;

public class Exoskeleton extends Monster{

    public Exoskeleton(String name, int health, int attack, double damage_reduction, double agility) {
        super(name, health, attack, agility, damage_reduction);
    }

    @Override
    public String toString() {
        return String.format(
                "%s (Lvl %d) | HP:%d | DAM:%d DEF:%f DOG:%.2f",
                super.getName(), level.getCurrentLevel(),
                super.getStats().getHealth(),
                super.getStats().getAttack(), super.getStats().getDamage_reduction(), super.getStats().getAgility()
        );
    }
}
