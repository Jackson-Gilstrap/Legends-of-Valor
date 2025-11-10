package Entities;

import Interfaces.Levelable;

public abstract class Hero extends Entity implements Levelable {
    private int experience_points;


    protected Hero(String name){
        super(name);
        this.experience_points = 0;
//
    }

    // used to check if hero can level up
    @Override
    public int getExperiencePoints() {
        return this.experience_points;
    }
    // used for after a battle
    @Override
    public void addExperiencePoints(int experience_points) {
        this.experience_points += experience_points;
        System.out.println(name + " earned " + experience_points + " points");
    }
    // used after a battle and experience point have hit the threshold from can level up method
    @Override
    public void levelUp() {
        if(canLevelUp(this.experience_points)) {
            super.getLevelObj().increaseLevel();
            System.out.println(this.name + " leveled up to " + level.getCurrentLevel());
        }

    }

    @Override
    public boolean canLevelUp(int experience_points) {
        return this.experience_points >= level.getXPForNextLevel();
    }

    @Override
    public int getLevel(){
        return level.getCurrentLevel();
    }


}
