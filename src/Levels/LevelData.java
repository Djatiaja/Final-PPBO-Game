package Levels;

public class LevelData {
    private int[][] levelData;
    private int spriteIndex;


    public LevelData(int[][] levelData, int spriteIndex) {
        this.levelData = levelData;
        this.spriteIndex = spriteIndex;
    }

    public int[][] getLevelData() {
        return levelData;
    }

    public int getSpriteIndex() {
        return spriteIndex;
    }

    public void setLevelData(int[][] levelData) {
        this.levelData = levelData;
    }

    public void setSpriteIndex(int spriteIndex) {
        this.spriteIndex = spriteIndex;
    }
}
