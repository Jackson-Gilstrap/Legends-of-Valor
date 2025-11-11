package Interfaces;

import Entities.Monster;

public interface MonsterFactory extends EntityFactory {
    @Override
    Monster createEntity(String[] args);
}
