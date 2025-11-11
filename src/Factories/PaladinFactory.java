package Factories;

import Entities.Hero;
import Builders.HeroBuilder;
import Interfaces.HeroFactory;

public class PaladinFactory implements HeroFactory {

    @Override
    public Hero createEntity(String[] args) {
        return new HeroBuilder().setName(args[0])
                .setHealth(Integer.parseInt(args[1]))
                .setMana(Integer.parseInt(args[2]))
                .setStrength(Integer.parseInt(args[3]))
                .setDexterity(Integer.parseInt(args[4]))
                .setAgility(Integer.parseInt(args[5]))
                .setGold(Integer.parseInt(args[6]))
                .buildWarrior();
    }
}
