package Entity;

import Levels.Level;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class QuestNPC extends NPC{
    boolean changed = false;


    public QuestNPC(float x, float y, int width, int height, BufferedImage[][] image, int[] movementPattern, Level level, Player player, String name, BufferedImage npcImage, ArrayList<String[]> dialog) {
        super(x, y, width, height, image, movementPattern, level, player, name, npcImage, dialog);
    }

    @Override
    public void update(){
        this.playerCord = player.getPlayerLocationTiles();
        animationHandle();
        if (!dialogShow){
            movementHandle();
            movement();
        }

        if (!changed && level.getListSampah().isEmpty()){
            dialog.clear();
            dialog.add(new String[]{"Terima kasih sudah membersihkan tempat ini sekarang udara menjadi segar tanpa \n polusi, sekarang aku bisa bermain dengan teman-temanku", this.name});
            changed=true;
        }
    }
    public void draw(Graphics g, int xOfflevelset, int yOfflevelSet){
        if (dialogShow){
            g.drawImage(image[diam][animationIndex],(int)hitBox.x-xOfflevelset,(int)hitBox.y-yOfflevelSet-20,(int) (16* Game.SCALES),(int) (32* Game.SCALES),null);
        }else {
            g.drawImage(image[movementPattern[movementIndex]][animationIndex],(int)hitBox.x-xOfflevelset,(int)hitBox.y-yOfflevelSet-20,(int) (16* Game.SCALES),(int) (32* Game.SCALES),null);
        }
//        g.drawRect((int)hitBox.x-xOfflevelset,(int)hitBox.y-yOfflevelSet, (int) (16* Game.SCALES),(int) (16* Game.SCALES));
    }

}
