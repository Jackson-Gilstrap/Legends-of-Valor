package Entities;

public class Level {

    private int currentLevel;
    private static final int MAX_LEVEL = 15;

    private static final int[] XP_THRESHOLDS = { 0, 20, 70, 170, 320, 520, 770, 1070, 1420, 1820, 2270, 2770, 3320, 3920, 4570, 5270 };

    public Level() {
        this.currentLevel = 1;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public boolean isMaxLevel() {
        return currentLevel == MAX_LEVEL;
    }

    public void increaseLevel() {
        if (!isMaxLevel()) {
            currentLevel++;
        }
    }

    public int getXPForNextLevel() {
        if (isMaxLevel()) return Integer.MAX_VALUE;
        return XP_THRESHOLDS[currentLevel]; // next level’s threshold
    }

}
