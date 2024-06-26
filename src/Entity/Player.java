package Entity;
import Levels.Level;
import Main.Game;
import utils.LoadSave;
import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.PlayerConstants.*;
import static utils.Helpers.canMoveHere;

public class Player  extends Entitty{
    private BufferedImage[][] AnimationsIDLE;
    private BufferedImage[][] AnimationsRUNNING;
    private int velocity = 1;
    private int aniTick, aniIndex, aniSpeed=20;
    private int player_action=IDLE;
    private boolean kiri, kanan, atas, bawah;

    private int Kiri=2,Kanan=0,Atas=1,Bawah=3;
    private int lastPlayerDirection = 1;

    private int lastDirect=1;
    private int leftxOffset = 0;
    private boolean isPlayerMoving = false, isAttacking=false;
    private int xOffset= (int) (25* Game.SCALES);
    private int yOffset = (int) (22* Game.SCALES);
    private Integer[] notSaidSprite;
    private Level level;
    public Player(float x , float y, int width, int height, Level level) {
        super(x, y, width,height);
        this.level = level;
        loadAnimations();
        hitBox.width=14*Game.SCALES; //20
        hitBox.height=10*Game.SCALES;//28
    }

    public void setNotSaidSprite(Integer[] notSaidSprite) {
        // setting untuk mengidentifikasi barang yang tidak bisa dilewati
        this.notSaidSprite = notSaidSprite;
    }

    public void reset(){
        kiri = false;
        kanan = false;
        atas = false;
        bawah = false;
        isPlayerMoving=false;
    }
    public void render(Graphics g, int xOfflevelset, int yOfflevelSet){
//        drawHitbox(g, xOfflevelset, yOfflevelSet);

        if (isPlayerMoving){
            if (lastPlayerDirection==Kiri || lastPlayerDirection==Kanan) {
                g.drawImage(AnimationsRUNNING[lastPlayerDirection][aniIndex], (int) hitBox.x - xOfflevelset + ((leftxOffset - 80) * lastDirect), (int) hitBox.y - 50 - yOfflevelSet-20, 128 * lastDirect, 128, null);
            }else {
                g.drawImage(AnimationsRUNNING[lastPlayerDirection][aniIndex], (int)hitBox.x -xOfflevelset-50  ,(int) hitBox.y -50 -yOfflevelSet-20,128 ,128,null);

            }

        }else {
            if (aniIndex>=4){
                aniIndex=0;
            }
            if (lastPlayerDirection==Kiri || lastPlayerDirection==Kanan) {
                g.drawImage(AnimationsIDLE[lastPlayerDirection][aniIndex], (int) hitBox.x - xOfflevelset + ((leftxOffset - 80) * lastDirect), (int) hitBox.y - 50 - yOfflevelSet-20, 128 * lastDirect, 128, null);
            }else {
                g.drawImage(AnimationsIDLE[lastPlayerDirection][aniIndex], (int)hitBox.x -xOfflevelset-50  ,(int) hitBox.y -50 -yOfflevelSet-20,128 ,128,null);
            }
        }
    }

    public void update(){
        updatePlayerAction();
        updateAnimation();
        updatePos();
    }
    public void updatePos(){
        isPlayerMoving=false;
        if (!kiri&&!kanan&&!atas&&!bawah){
            return;
        }

        float xSpeed=0,ySpeed=0;

        if (kiri && !kanan){
            xSpeed-=velocity;
            this.lastPlayerDirection= Kiri;
            this.leftxOffset =30;
            this.lastDirect=Math.abs(lastDirect);
        } else if (!kiri && kanan) {
            xSpeed+=velocity;
            this.lastPlayerDirection= Kanan;
            this.lastDirect=Math.abs(lastDirect)*-1;
            this.leftxOffset =0;
        }

        if (atas && !bawah){
            ySpeed-=velocity;
            this.lastPlayerDirection= Atas;
        } else if (!atas && bawah) {
            ySpeed+=velocity;
            this.lastPlayerDirection= Bawah;
        }



        if (canMoveHere(hitBox.x +xSpeed,hitBox.y+ySpeed,  hitBox.width,hitBox.height, this.level )){
            for (NPC npc : level.getListNPC()) {
                int npcX = npc.getCord()[1];
                int npcY = npc.getCord()[0];
                int hitBoxTileX = (int) ((hitBox.x +xSpeed)/ Game.TILES_SIZE);
                int hitBoxTileY = (int) ((hitBox.y +ySpeed) / Game.TILES_SIZE);

                if (npcX == hitBoxTileX && npcY == hitBoxTileY) {
                    return;
                }
                if (npcX ==  (int) ((hitBox.x +xSpeed+npc.width)/ Game.TILES_SIZE) && npcY == hitBoxTileY){
                    return;
                }
                if (npcX ==  (int) ((hitBox.x +xSpeed)/ Game.TILES_SIZE) && npcY == (int) ((hitBox.y +ySpeed+npc.height) / Game.TILES_SIZE)){
                    return;
                }
                if (npcX ==  (int) ((hitBox.x +xSpeed+npc.width)/ Game.TILES_SIZE) && npcY == (int) ((hitBox.y +ySpeed+npc.height) / Game.TILES_SIZE)){
                    return;
                }

            }
            for (Sampah sampah : level.getListSampah()) {
                int npcX = sampah.getCord()[1];
                int npcY = sampah.getCord()[0];
                int hitBoxTileX = (int) ((hitBox.x +xSpeed)/ Game.TILES_SIZE);
                int hitBoxTileY = (int) ((hitBox.y +ySpeed) / Game.TILES_SIZE);


                if (npcX == hitBoxTileX && npcY == hitBoxTileY) {
                    return;
                }
                if (npcX ==  (int) ((hitBox.x +xSpeed+sampah.width)/ Game.TILES_SIZE) && npcY == hitBoxTileY){
                    return;
                }
                if (npcX ==  (int) ((hitBox.x +xSpeed)/ Game.TILES_SIZE) && npcY == (int) ((hitBox.y +ySpeed+sampah.height) / Game.TILES_SIZE)){
                    return;
                }
                if (npcX ==  (int) ((hitBox.x +xSpeed+sampah.width)/ Game.TILES_SIZE) && npcY == (int) ((hitBox.y +ySpeed+sampah.height) / Game.TILES_SIZE)){
                    return;
                }

            }

            hitBox.y+=ySpeed;
            hitBox.x+=xSpeed;
            isPlayerMoving=true;
        }
    }
    public void  loadAnimations() {
        BufferedImage playerSideIdle = LoadSave.getAtlasSprite(LoadSave.playerSideIdle);
        BufferedImage playerSideRun = LoadSave.getAtlasSprite(LoadSave.playerSideWalk);

        BufferedImage playerUpIdle = LoadSave.getAtlasSprite(LoadSave.playerUpIdle);
        BufferedImage playerUpRun = LoadSave.getAtlasSprite(LoadSave.playerUpWalk);

        BufferedImage playerDownIdle = LoadSave.getAtlasSprite(LoadSave.playerDownIdle);
        BufferedImage playerDownRun = LoadSave.getAtlasSprite(LoadSave.playerDownWalk);

        this.AnimationsIDLE = new BufferedImage[4][4];
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                AnimationsIDLE[2][i+j*3] = playerSideIdle.getSubimage(i * 64, j * 64, 64, 64);
                if (j==1 && i==0){
                    break;
                }
            }
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                AnimationsIDLE[0][i+j*3] = playerSideIdle.getSubimage(i * 64, j * 64, 64, 64);
                if (j==1 && i==0){
                    break;
                }
            }
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                AnimationsIDLE[3][i+j*3] = playerDownIdle.getSubimage(i * 64, j * 64, 64, 64);
                if (j==1 && i==0){
                    break;
                }
            }
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                AnimationsIDLE[1][i+j*3] = playerUpIdle.getSubimage(i * 64, j * 64, 64, 64);
                if (j==1 && i==0){
                    break;
                }
            }
        }
        this.AnimationsRUNNING = new BufferedImage[4][5];
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                AnimationsRUNNING[2][i+j*3] = playerSideRun.getSubimage(i * 64, j * 64, 64, 64);
                if (j==1 && i==1){
                    break;
                }
            }
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                AnimationsRUNNING[0][i+j*3] = playerSideRun.getSubimage(i * 64, j * 64, 64, 64);
                if (j==1 && i==1){
                    break;
                }
            }
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                AnimationsRUNNING[3][i+j*3] = playerDownRun.getSubimage(i * 64, j * 64, 64, 64);
                if (j==1 && i==1){
                    break;
                }
            }
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                AnimationsRUNNING[1][i+j*3] = playerUpRun.getSubimage(i * 64, j * 64, 64, 64);
                if (j==1 && i==1){
                    break;
                }
            }
        }

    }

    private void updatePlayerAction() {
        int startAni = player_action;
        if (isPlayerMoving){
            player_action = RUNNING;
        }else {
            player_action= IDLE;
        }
        if (isAttacking){
            this.player_action=ATTACK_1;
        }
        if (startAni != player_action){
            aniIndex=0;
            aniTick=0;
        }
    }

    private void updateAnimation() {
        aniTick++;
        if (aniTick >= aniSpeed){
            aniIndex++;
            if (isPlayerMoving){
                if (aniIndex >= 5 ){
                    aniIndex =0;
                }
            }else{
                if (aniIndex >= 4){
                    aniIndex =0;
                }
            }

            aniTick=0;
        }
    }

    public void setLevel(Level level) {
        this.level = level;
    };

    public int[] getSpriteDataOnLocation(){
        int xSprite = (int) Math.floor(hitBox.x / Game.TILES_SIZE);
        int ySprite = (int) Math.floor(hitBox.y /Game.TILES_SIZE);
        System.out.println(level.getLevelDataList().get(1).getLevelData()[ySprite][xSprite]);
        System.out.println(xSprite + " " + ySprite);
        return new int[]{ySprite,xSprite};
    }
    public int[] getPlayerLocationTiles(){
        int xSprite = (int) Math.floor(hitBox.x / Game.TILES_SIZE);
        int ySprite = (int) Math.floor(hitBox.y /Game.TILES_SIZE);
        return new int[]{xSprite,ySprite};
    }

    public void resetPlayerPos(int[]spawnCord){
        this.hitBox.x=spawnCord[1] * Game.TILES_SIZE +10;
        this.hitBox.y=spawnCord[0]* Game.TILES_SIZE+10;
    }

    public void setAniIndex(int num){
        aniIndex=num;
    }
    public void SetisPlayerMoving(boolean bool) {
        this.isPlayerMoving = bool;
    }

    public boolean isKiri() {
        return kiri;
    }

    public void setKiri(boolean kiri) {
        this.kiri = kiri;
    }

    public boolean isKanan() {
        return kanan;
    }

    public void setKanan(boolean kanan) {
        this.kanan = kanan;
    }

    public boolean isAtas() {
        return atas;
    }

    public void setAtas(boolean atas) {
        this.atas = atas;
    }

    public boolean isBawah() {
        return bawah;
    }

    public void setBawah(boolean bawah) {
        this.bawah = bawah;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }
}
