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

    /**
     * Performs an attack on the specified entity.
     *
     * @param e the target entity being attacked
     * @return the amount of damage dealt to the target
     */
    public abstract int attack(Entity e);

    /**
     * Handles receiving an attack and applies the given damage to this entity.
     *
     * @param damage the amount of damage inflicted
     * @return the remaining health (or other relevant value) of this entity after taking damage
     */
    public abstract int takeAttack(int damage);


}
