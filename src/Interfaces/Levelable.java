package Interfaces;

import java.util.logging.Level;

public interface Levelable {

    /*
    interface for entities that can be leveled up
    think about checking current experience to level each level has an exp threshold
     */
    int getLevel();
    int getExperiencePoints();
    void addExperiencePoints(int experience_points);
    boolean canLevelUp(int experience_points);
    void levelUp();
}
