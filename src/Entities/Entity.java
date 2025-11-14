package Entities;

import Utility.Level;

public abstract class Entity {
    protected String name;
    protected Level level;

    public Entity(String name) {
        this.name = name;
        this.level = new Level();
    }

    public String getName(){
        return name;
    }

    public Level getLevelObj(){
        return level;
    }


}
