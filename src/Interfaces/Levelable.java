package Interfaces;

public interface Levelable {

    void gainExperiencePoints(int experience_points);
    boolean canLevelUp(int experience_points);
    void levelUp();
}
