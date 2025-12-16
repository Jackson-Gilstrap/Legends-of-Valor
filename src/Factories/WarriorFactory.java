/**
 * Factory for constructing Warrior heroes with predefined stats.
 */
package Factories;

import Entities.Entity;
import Entities.Hero;
import Builders.HeroBuilder;
import Interfaces.HeroFactory;
import Utility.Stats;

public class WarriorFactory implements HeroFactory {

    @Override
    public Hero createEntity(String name, Stats stats) {
        return new HeroBuilder().setName(name)
                .setHealth(stats.getHealth())
                .setMana(stats.getMana())
                .setAttack(stats.getAttack())
                .setDexterity(stats.getDexterity())
                .setAgility(stats.getAgility())
                .setDamageReduction(stats.getDamage_reduction())
                .buildWarrior();
    }

    @Override
    public Entity createEntity(String name, Stats stats, int level) {
        return null;
    }
}
