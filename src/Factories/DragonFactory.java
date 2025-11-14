package Factories;

import Entities.Monster;
import Builders.MonsterBuilder;
import Interfaces.MonsterFactory;
import Utility.Stats;

public class DragonFactory implements MonsterFactory {

    @Override
    public Monster createEntity(String name, Stats stats) {
        return new MonsterBuilder()
                .setName(name)
                .setHealth(stats.getHealth())
                .setAttack(stats.getAttack())
                .setAgility(stats.getAgility())
                .setDamageReduction(stats.getDamage_reduction())
                .buildDragon();
    }
}
