package Entities;

public class Spirit extends Monster{

    public Spirit(String name, int health, int damage, int defence, float dodge_chance) {
        super(name, health, damage, defence, dodge_chance);
    }

    @Override
    public String toString() {
        return String.format(
                "%s (Lvl %d) | HP:%d | DAM:%d DEF:%d DOG:%.2f",
                super.getName(), level.getCurrentLevel(),
                super.getHealth(),
                super.getDamage(), super.getDefence(), super.getDodge_chance()
        );
    }
}
