package Entities;

/*
Need to set up the favored attributes at a later time for all hero classes once I can read from file
 */
public class Warrior extends Hero{

    public Warrior (String name, int health, int mana, int strength, int dexterity, int agility, int gold) {
        super(name, health, mana, strength, dexterity, agility, gold);
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
