package Levels;

import Entity.NPC;
import Entity.QuestNPC;
import Entity.Sampah;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class Level {
    private int[][] levelData;
    private int[] spawnCord;

    private ArrayList<Integer[]> collisionData = new ArrayList<>();
    private ArrayList<LevelData> levelDataList = new ArrayList<>(Arrays.asList());

    private ArrayList<MapChanger> listChanger = new ArrayList<>();
    private ArrayList<int[]> gameEnd = new ArrayList<>();

    //BufferedImage[] levelSprite;
    private ArrayList<BufferedImage[]> levelSprites = new ArrayList<>();
    //private BufferedImage[] levelSpriteBase; //tgr
    //private BufferedImage[] levelSpriteInterior; //tgr

    private ArrayList<NPC> listNPC = new ArrayList<>();
    private ArrayList<Sampah> listSampah = new ArrayList<>();
    private QuestNPC questNPC;
    public Level( int[] spawnCord , BufferedImage[] levelSprite){
//        printDatainINT();
        this.spawnCord = spawnCord;
        this.levelSprites.add(levelSprite);
    }

    public Level(int[] spawnCord){
        this.spawnCord = spawnCord;
    }

    public int getSpriteIndex(int[][] levelData,int x, int y){
        return levelData[x][y];
    }

    public void addLevelData(int[][] levelData, int index){
        //{levelData, indexSprite}
        levelDataList.add(new LevelData(levelData,index ));
    }
    public void printDatainINT(){
        //Debugging
        for (int i = 0; i < levelData.length; i++) {
            String data = "";
            System.out.println(Arrays.toString(levelData[i]));
        }
    }

    public void addLevelCollision(Integer[] collisionData){
        this.collisionData.add(collisionData);
    }

    public ArrayList<Integer[]> getCollisionData() {
        return collisionData;
    }


//    public void printLevelData(){
//        for (int[][] levelData:levelDataList) {
//            for (int i = 0; i < levelData.length; i++) {
//                String data = "";
//                System.out.println(Arrays.toString(levelData[i]));
//            }
//        }
//    }

    public ArrayList<LevelData> getLevelDataList() {
        return levelDataList;
    }

    public int[] getSpawnCord() {
        return spawnCord;
    }

    public int[][] getLevelData(){
        return levelData;
    }

    public int[][] getCurrentLevelData() {
        return levelData;
    }

    public void addLevelSprite(BufferedImage[] levelSPrite){
        this.levelSprites.add(levelSPrite);
    }

    public BufferedImage[] getlevelSprite(int index){
        return  this.levelSprites.get(index);
    }


    public void addMapChanger(int[] location, int Map){
        MapChanger mapChanger = new MapChanger(location, Map);
        this.listChanger.add(mapChanger);
    }

    public ArrayList<MapChanger> getListChanger() {
        return listChanger;
    }

    public void  changeSpawnCord(int[] spawnCord){
        this.spawnCord = spawnCord;
    }

    public void addNPC(NPC npc){
        listNPC.add(npc);
    }
    public ArrayList<NPC> getListNPC(){
        return listNPC;
    }
    public void addSampah(Sampah sampah){
        listSampah.add(sampah);
    }
    public ArrayList<Sampah> getListSampah(){
        return listSampah;
    }
    public void setQuestNPC(QuestNPC questNPC){
        this.questNPC = questNPC;
    }
    public void addGameEnd(int[] location){
        gameEnd.add(location);
    }
    public ArrayList<int[]> getGameEnd(){
        return gameEnd;
    }
}
