package Entities;

import Interfaces.Levelable;

public abstract class Hero extends Entity implements Levelable {
    private int experience_points;


    protected Hero(String name){
        super(name);
        this.experience_points = 0;
//
    }

    @Override
    public int getLevel(){
        return level.getCurrentLevel();
    }


}
