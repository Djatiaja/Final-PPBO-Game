package Entity;

import Levels.Level;
import Main.Game;
import utils.Helpers;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class NPC extends Entitty{
    int animationTick = 0; int animationSpeed = 40;
    int animationIndex = 0;
    public static int kanan=0,atas=1,kiri=2,bawah=3, diam=4;
    int fps= 0;

    int[] movementPattern;

    int movementIndex = 0;
    int movementSpeed= 184;
    int movementTick = 0;
    int velocity = 1;
    BufferedImage[][] image;
    Level level;
    Player player;
    ArrayList<String[]> dialog;
    Boolean dialogShow = false;
    int[] playerCord;
    String name;
    BufferedImage npcImage;
    public NPC(float x, float y, int width, int height, BufferedImage[][] image, int[] movementPattern, Level level, Player player, String name, BufferedImage npcImage, ArrayList<String[]> dialog) {
        super(x, y, width, height);
        this.image = image;
        this.movementPattern = movementPattern;
        this.level = level;
        this.player = player;
        this.playerCord = player.getPlayerLocationTiles();
        this.dialog = dialog;
        this.name = name;
        this.npcImage= npcImage;
    }

    public void draw(Graphics g, int xOfflevelset, int yOfflevelSet){
        if (dialogShow){
            g.drawImage(image[diam][animationIndex],(int)hitBox.x-xOfflevelset,(int)hitBox.y-yOfflevelSet-20,(int) (16* Game.SCALES),(int) (32* Game.SCALES),null);
        }else {
        g.drawImage(image[movementPattern[movementIndex]][animationIndex],(int)hitBox.x-xOfflevelset,(int)hitBox.y-yOfflevelSet-20,(int) (16* Game.SCALES),(int) (32* Game.SCALES),null);
        }
//        g.drawRect((int)hitBox.x-xOfflevelset,(int)hitBox.y-yOfflevelSet, (int) (16* Game.SCALES),(int) (16* Game.SCALES));
    }

    public void update(){
        this.playerCord = player.getPlayerLocationTiles();
        animationHandle();
        if (!dialogShow){
            movementHandle();
            movement();
        }

    }

    public void animationHandle(){
        if (animationTick>= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if (animationIndex>=image[0].length){
                animationIndex = 0;
            }
        }
        animationTick++;
    }

    public void movementHandle(){
        if (movementTick>=movementSpeed){
            movementTick = 0;
            movementIndex++;
            animationIndex = 0;
            animationTick = 0;
            if (movementIndex>=movementPattern.length){
                movementIndex = 0;
            }
        }
        movementTick++;
    }

    public void movement(){
        fps++;
        if (fps<10){
            return;
        }



        if (movementPattern[movementIndex]==kiri){
            if (Helpers.canMoveHere(hitBox.x-velocity,hitBox.y,hitBox.width,hitBox.height,level)){
                hitBox.x -= velocity;

            }
            if (playerCord[1]== (int) (hitBox.x -velocity)/Game.TILES_SIZE && playerCord[0]== (int) (hitBox.y)/Game.TILES_SIZE){
                hitBox.x += velocity;
            }

        }
        if (movementPattern[movementIndex]==kanan){
            if (Helpers.canMoveHere(hitBox.x+velocity+ 32,hitBox.y,hitBox.width,hitBox.height,level)){
                hitBox.x += velocity;

            }
            if (playerCord[1]== (int) (hitBox.x -velocity+ 32)/Game.TILES_SIZE && playerCord[0]== (int) (hitBox.y)/Game.TILES_SIZE){
                hitBox.x -= velocity;
            }
        }
        if (movementPattern[movementIndex]==atas){
            if (Helpers.canMoveHere(hitBox.x,hitBox.y-velocity,hitBox.width,hitBox.height,level)){
                hitBox.y -= velocity;

            }
            if (playerCord[0]== (int) (hitBox.y +velocity)/Game.TILES_SIZE && playerCord[1]== (int) (hitBox.x)/Game.TILES_SIZE){
                hitBox.x += velocity;
            }
        }
        if (movementPattern[movementIndex]==bawah){
            if (!Helpers.canMoveHere(hitBox.x,hitBox.y+velocity+ 32,hitBox.width,hitBox.height,level)){
                hitBox.y += velocity;
            }
            if (playerCord[0]== (int) (hitBox.y -velocity+32)/Game.TILES_SIZE && playerCord[1]== (int) (hitBox.x)/Game.TILES_SIZE){
                hitBox.x -= velocity;
            }
        }
        if (movementPattern[movementIndex]==diam){
            hitBox.x += 0;
            hitBox.y += 0;
        }
        fps=0;
    }


    public int[] getCord() {
        return new int[]{(int)hitBox.y/Game.TILES_SIZE,(int)hitBox.x/Game.TILES_SIZE};
    }
    public ArrayList<String[]> getDialog() {
        return dialog;
    }
    public void setDialogShow(Boolean dialogShow) {
        this.dialogShow = dialogShow;

    }
    public BufferedImage getNpcImage() {
        return npcImage;
    }
    public String getName() {
        return name;
    }
}
