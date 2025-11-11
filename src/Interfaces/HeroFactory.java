package Interfaces;

import Entities.Hero;

public interface HeroFactory extends EntityFactory {
    @Override
    Hero createEntity(String[] args);
}
