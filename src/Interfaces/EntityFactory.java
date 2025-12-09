package Interfaces;

import Entities.Entity;
import Utility.Stats;

public interface EntityFactory {
    Entity createEntity(String name, Stats stats);

    Entity createEntity(String name, Stats stats, int level);
}
