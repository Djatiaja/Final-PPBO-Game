package Levels;

public class MapChanger {
    int[] location = new int[2];
    int mapDestination;

    public MapChanger(int[] location, int mapDestination){
        this.location = location;
        this.mapDestination = mapDestination;
    }

    public int[] getLocation(){
        return location;
    }

    public int getMap() {
        return mapDestination;
    }
}
