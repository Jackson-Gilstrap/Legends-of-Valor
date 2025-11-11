package Interfaces;

import Entities.Entity;

public interface EntityFactory {
    Entity createEntity(String[] args);
}
