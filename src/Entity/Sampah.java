package Entity;

import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sampah extends Entitty{
    Boolean show = true;
    BufferedImage[] tileset;
    public Sampah(float x, float y, int width, int height, BufferedImage[] tileset) {
        super(x*Game.TILES_SIZE, y*Game.TILES_SIZE, width, height);
        this.tileset = tileset;
    }

    public void draw(Graphics g,int  xOfflevelset,int yOfflevelSet){
        if (show){
            g.drawImage(tileset[254],(int)hitBox.x- xOfflevelset,(int)hitBox.y -yOfflevelSet,Game.TILES_SIZE,Game.TILES_SIZE,null);
        }
    }
    public int[] getCord() {
        return new int[]{(int)hitBox.y/ Game.TILES_SIZE,(int)hitBox.x/Game.TILES_SIZE};
    }


}
