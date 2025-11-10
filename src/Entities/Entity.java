package Entities;

public abstract class Entity {
    protected String name;
    protected int level;

    protected Entity(String name) {
        this.name = name;
        this.level = 1;
    }

    protected String getName(){
        return name;
    }

    protected int getLevel(){
        return level;
    }

    protected void setLevel(int level){
        this.level = level;
    }
}
