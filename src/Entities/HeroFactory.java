package Entities;

public interface HeroFactory extends EntityFactory{
    @Override
    Hero createEntity(String[] args);
}
