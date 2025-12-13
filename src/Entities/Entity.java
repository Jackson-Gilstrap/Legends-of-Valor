package Entities;

import Interfaces.Positionable;
import Utility.Level;

public abstract class Entity implements Positionable{
    protected String name;
    protected Level level;
    protected int row, col;
    
    public Entity(String name) {
        this.name = name;
        this.level = new Level();
    }

    public Entity(String name, int level) {
        this.name = name;
        this.level = new Level(level);
    }

    public String getName(){
        return name;
    }

    public Level getLevelObj(){
        return level;
    }

    public void setCurrentLevel(int level){
        if(level < 0) return;
        this.level.setCurrentLevel(level);
    }

    @Override
    public int getRow(){ return row; }

    @Override
    public int getCol(){ return col; }

    @Override
    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }  

}
