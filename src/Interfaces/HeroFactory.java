package Interfaces;

import Entities.Hero;
import Utility.Stats;

public interface HeroFactory extends EntityFactory {
    @Override
    Hero createEntity(String name, Stats stats);
}
