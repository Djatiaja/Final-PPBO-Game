package utils;

import Levels.Level;
import Levels.LevelData;
import Main.Game;

import java.util.ArrayList;
import java.util.Arrays;

public class Helpers {
    public static boolean devMode=false;
    public static void setDevMode(Boolean bool){
        devMode=bool;
    }
    public static boolean canMoveHere(float x, float y, float width, float height, Level level){
        if (!isSolid(x,y,level)){
            if (!isSolid(x+width,y+height, level)){
                if (!isSolid(x,y+height,level)){
                    if (!isSolid(x+width,y,level)){
                        return true;
                    };
                };
            };
        };
        return false;
    }

    public static boolean isSolid(float x, float y, Level level){
        int maxHeightGame =  (level.getLevelDataList().get(0).getLevelData().length * Game.TILES_SIZE);
        int maxWidthGame =  (level.getLevelDataList().get(0).getLevelData()[0].length * Game.TILES_SIZE);
        if (x<=0 || x>= maxWidthGame){
            return true;
        }
        if (y <=0 || y>= maxHeightGame){
            return true;
        }
        float xIndex = x/Game.TILES_SIZE;
        float yIndex = y/Game.TILES_SIZE;
            for (LevelData levelData1: level.getLevelDataList()) {
                int value = levelData1.getLevelData()[(int) yIndex][(int) xIndex];
                if (level.getCollisionData().toArray().length>0){
                    Integer[] notSolidSprite = level.getCollisionData().get(levelData1.getSpriteIndex());
                    if (!(Arrays.asList(notSolidSprite).contains(value)||value<0)){
                        return  !devMode;
                }
            }
        }

        return false;
    }
}
