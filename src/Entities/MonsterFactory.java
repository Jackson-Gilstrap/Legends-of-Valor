package Entities;

public interface MonsterFactory extends EntityFactory{
    @Override
    Monster createEntity(String[] args);
}
