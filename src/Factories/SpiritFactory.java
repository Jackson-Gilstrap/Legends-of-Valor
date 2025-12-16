/**
 * Factory for generating Spirit monsters with predefined stats.
 */
package Factories;

import Entities.Entity;
import Entities.Monster;
import Builders.MonsterBuilder;
import Interfaces.MonsterFactory;
import Utility.Stats;

public class SpiritFactory implements MonsterFactory {

    @Override
    public Entity createEntity(String name, Stats stats) {
        return null;
    }

    @Override
    public Monster createEntity(String name, Stats stats, int level) {
        return new MonsterBuilder()
                .setName(name)
                .setHealth(stats.getHealth())
                .setAttack(stats.getAttack())
                .setAgility(stats.getAgility())
                .setDamageReduction(stats.getDamage_reduction())
                .setLevel(level)
                .buildSpirit();
    }
}
