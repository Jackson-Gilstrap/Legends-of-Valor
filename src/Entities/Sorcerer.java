package Entities;

public class Sorcerer extends Hero{

    public Sorcerer (String name, int health, int mana, int attack, int dexterity, double agility, double damage_reduction, int gold) {
        super(name, health, mana, attack, dexterity, agility, damage_reduction);
    }

    @Override
    public String toString() {
        return String.format(
                "%s (Lvl %d) | HP:%d MP:%d | ATK:%d DEX:%d | AGI:%.2f DR:%.2f | XP:%d Gold:%d",
                name, level.getCurrentLevel(),
                super.getStats().getHealth(), super.getStats().getMana(),
                super.getStats().getAttack(), super.getStats().getDexterity(), super.getStats().getAgility(), super.getStats().getDamage_reduction(),
                getExperiencePoints() , super.getGold()
        );
    }
}
