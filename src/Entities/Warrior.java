package Entities;

/*
Need to set up the favored attributes at a later time for all hero classes once I can read from file
 */
public class Warrior extends Hero{

    public Warrior (String name, int health, int mana, int attack, int dexterity, double agility, double damage_reduction, int gold) {
        super(name, health, mana, attack, dexterity, agility, damage_reduction, gold);
    }

    @Override
    public String toString() {
        return String.format(
                "%s (Lvl %d) | HP:%d MP:%d | ATK:%d AGI:%.2f DEX:%d DR:%.2f | XP:%d Gold:%d",
                name, level.getCurrentLevel(),
                super.getStats().getHealth(), super.getStats().getMana(),
                super.getStats().getAttack(), super.getStats().getAgility(), super.getStats().getDexterity(), super.getStats().getDamage_reduction(),
                getExperiencePoints() , super.getGold()
        );
    }


}
