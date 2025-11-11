package Factories;

import Entities.Monster;
import Builders.MonsterBuilder;
import Interfaces.MonsterFactory;

public class SpiritFactory implements MonsterFactory {

    @Override
    public Monster createEntity(String[] args) {
        return new MonsterBuilder()
                .setName(args[0])
                .setHealth(Integer.parseInt(args[1]))
                .setDamage(Integer.parseInt(args[2]))
                .setDefence(Integer.parseInt(args[3]))
                .setDodge_chance(Float.parseFloat(args[4]) * 100)
                .buildSpirit();
    }
}
