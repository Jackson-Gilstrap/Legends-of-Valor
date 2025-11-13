package Interfaces;

import Entities.Entity;
import Utility.Stats;

public interface EntityFactory {
    Entity createEntity(String name, Stats stats);
}
