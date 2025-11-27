package Interfaces;

import Entities.Monster;
import Utility.Stats;

public interface MonsterFactory extends EntityFactory {
    @Override
    Monster createEntity(String name, Stats stats, int level);
}
