package Entities;

public class Paladin extends Hero{

    public Paladin(String name, int health, int mana, int strength, int agility, int dexterity, int gold) {
        super(name, health, mana, strength, agility, dexterity, gold);
    }

    @Override
    public String toString() {
        return String.format(
                "%s (Lvl %d) | HP:%d MP:%d | STR:%d AGI:%d DEX:%d | XP:%d Gold:%d",
                name, level.getCurrentLevel(),
                super.getHealth(), super.getMana(),
                super.getStrength(), super.getAgility(), super.getDexterity(),
                getExperiencePoints() , super.getGold()
        );
    }

}
