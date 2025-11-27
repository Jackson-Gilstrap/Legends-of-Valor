package Utility;

public class Level {

    private int currentLevel;

    public Level() {
        this.currentLevel = 1;
    }

    public Level(int level) {
        this.currentLevel = level;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void increaseLevel() {
        currentLevel++;
    }
    public void setCurrentLevel(int level) {
        this.currentLevel = level;
    }


}
