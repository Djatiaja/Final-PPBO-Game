package Gamestate;

import Entity.NPC;
import Entity.Player;
import Entity.Sampah;
import Levels.Level;
import Levels.LevelManager;
import Levels.MapChanger;
import Main.Game;
import ui.DialogWindow;
import utils.Helpers;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import static Levels.LevelManager.*;
import static utils.Helpers.devMode;

public class Playing extends State implements StateMethod{
    private Player player;
    private LevelManager levelManager;
    public static int maxWidthGame ;
    private static float xOfflevelset;
    private static float yOfflevelset;
    private int maXXOfflevelSet;
    private int maxYOfflevelSet;
    private int maxHeightGame;
    private float leftBorder ;
    private float rightBorder;
    private float topBorder ;
    private float bottomBorder;
    private DialogWindow dialogWindow= new DialogWindow(this);
    private NPC currentNPC;
    boolean show = false;
    public Playing(Game game) {
        super(game);
        initClass();
        setBorder();
    }
    @Override
    public void render(Graphics g) {
        levelManager.draw(g, (int) xOfflevelset, (int) yOfflevelset);
        if (show){
            dialogWindow.draw(g);
        }
        game.getGamePanel().repaint();
    }

    @Override
    public void update() {
        checkBorder();
        if (show){
            dialogWindow.update();
        }
        player.update();
        levelManager.update();
    }

    public void initClass() {
        this.levelManager = new LevelManager(game,this);
        this.player = new Player(levelManager.getCurrentSpawn()[1] * Game.TILES_SIZE +10, levelManager.getCurrentSpawn()[0] * Game.TILES_SIZE +10, (int) ((int) 64 * Game.SCALES), (int) (40* Game.SCALES), levelManager.getCurrentLevel());
        levelManager.npcHandle(this.player);
        System.out.println("Playing");
    }
    public void setShow(boolean show){
        this.show = show;
    }
    public NPC getcurrentNPC(){
        return this.currentNPC;
    }

    private void checkBorder() {
        float diff = player.getHitBox().x - xOfflevelset;
        float ydiff = player.getHitBox().y - yOfflevelset;

        if (diff > rightBorder){
            xOfflevelset+= diff - rightBorder;
        } else if (diff < leftBorder) {
            xOfflevelset += diff - leftBorder;
        }

        if (xOfflevelset >= maXXOfflevelSet){
            xOfflevelset = maXXOfflevelSet;
        }else if (xOfflevelset <= 0){
            xOfflevelset=0;
        }


        if (ydiff>=bottomBorder){
            yOfflevelset += ydiff - bottomBorder;
        } else if (ydiff <= topBorder) {
            yOfflevelset += ydiff - topBorder;
        }

        if (yOfflevelset > maxYOfflevelSet){
            yOfflevelset = maxYOfflevelSet;
        } else if (yOfflevelset <= 0) {
            yOfflevelset = 0;
        }

    }
    public  void setBorder(){
        xOfflevelset =0;
        yOfflevelset=0;
        leftBorder = (float) (0.2 * Game.GAME_WIDTH);
        rightBorder = (float) (0.8 * Game.GAME_WIDTH);
        topBorder = (float) (0.2 * Game.GAME_HEIGHT);
        bottomBorder = (float) (0.8 * Game.GAME_HEIGHT);
        maxWidthGame =  (levelManager.getCurrentLevel().getLevelDataList().get(0).getLevelData()[0].length * Game.TILES_SIZE);
        maxHeightGame =  (levelManager.getCurrentLevel().getLevelDataList().get(0).getLevelData().length  * Game.TILES_SIZE);
        maXXOfflevelSet = maxWidthGame - Game.GAME_WIDTH;
        maxYOfflevelSet = maxHeightGame - Game.GAME_HEIGHT;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!show){
            if (e.getKeyCode() ==   KeyEvent.VK_W){
                player.setAtas(true);
            }

            if (e.getKeyCode() ==   KeyEvent.VK_A){
                player.setKiri(true);
            }

            if (e.getKeyCode() ==   KeyEvent.VK_S){
                player.setBawah(true);
            }

            if (e.getKeyCode() ==   KeyEvent.VK_D){
                player.setKanan(true);
            }
            if (e.getKeyCode()== KeyEvent.VK_E){
                player.getSpriteDataOnLocation();

                Level currentLevel = levelManager.getCurrentLevel();
                for(MapChanger mapChanger: currentLevel.getListChanger()){
                    if (mapChanger.getLocation()[0] == getPlayerCoordinate()[0] && mapChanger.getLocation()[1] == getPlayerCoordinate()[1]){
                        levelManager.setCurrentLevel(mapChanger.getMap(), new int[]{getPlayerCoordinate()[0], getPlayerCoordinate()[1]});
                    }
                }

                for(NPC npc: currentLevel.getListNPC()){
                    Integer[][] npcCord = new Integer[][]{
                            {npc.getCord()[0], npc.getCord()[1]-1},
                            {npc.getCord()[0], npc.getCord()[1]+1},
                            {npc.getCord()[0]-1, npc.getCord()[1]},
                            {npc.getCord()[0]+1, npc.getCord()[1]},
                            {npc.getCord()[0]-1, npc.getCord()[1]+1},
                            {npc.getCord()[0]+1, npc.getCord()[1]+1},
                            {npc.getCord()[0]-1, npc.getCord()[1]-1},
                            {npc.getCord()[0]+1, npc.getCord()[1]-1},

                            {npc.getCord()[0]-2, npc.getCord()[1]-2},
                            {npc.getCord()[0]+2, npc.getCord()[1]+2},
                            {npc.getCord()[0]-2, npc.getCord()[1]+2},
                            {npc.getCord()[0]+2, npc.getCord()[1]-2},
                            {npc.getCord()[0]-2, npc.getCord()[1]},
                            {npc.getCord()[0]+2, npc.getCord()[1]},
                            {npc.getCord()[0], npc.getCord()[1]-2},
                            {npc.getCord()[0], npc.getCord()[1]+2},
                            {npc.getCord()[0]-2, npc.getCord()[1]-1},
                            {npc.getCord()[0]+2, npc.getCord()[1]+1},
                            {npc.getCord()[0]-2, npc.getCord()[1]+1},
                            {npc.getCord()[0]+2, npc.getCord()[1]-1},
                            {npc.getCord()[0]-1, npc.getCord()[1]-2},
                            {npc.getCord()[0]+1, npc.getCord()[1]+2},
                            {npc.getCord()[0]-1, npc.getCord()[1]+2},
                            {npc.getCord()[0]+1, npc.getCord()[1]-2},

                    };
                    if (Arrays.deepToString(npcCord).contains(Arrays.toString(getPlayerCoordinate()))){
                        currentNPC = npc;
                        npc.setDialogShow(true);
                        dialogWindow.setText(npc.getDialog());
                        dialogWindow.setNPC(this.currentNPC);
                        setShow(true);
                        break;
                    }
                }

                for(Sampah sampah: currentLevel.getListSampah()){
                    Integer[][] sampahCord = new Integer[][]{
                        {sampah.getCord()[0], sampah.getCord()[1]-1},
                        {sampah.getCord()[0], sampah.getCord()[1]+1},
                        {sampah.getCord()[0]-1, sampah.getCord()[1]},
                        {sampah.getCord()[0]+1, sampah.getCord()[1]},
                        {sampah.getCord()[0]-1, sampah.getCord()[1]+1},
                        {sampah.getCord()[0]+1, sampah.getCord()[1]+1},
                        {sampah.getCord()[0]-1, sampah.getCord()[1]-1},
                        {sampah.getCord()[0]+1, sampah.getCord()[1]-1},
                    };
                    if (Arrays.deepToString(sampahCord).contains(Arrays.toString(getPlayerCoordinate()))){
                        currentLevel.getListSampah().remove(sampah);
                        break;
                    }
                }

                for (int[] gameEnd: currentLevel.getGameEnd()){
                    System.out.println(Arrays.toString(getPlayerCoordinate()));
                    System.out.println(Arrays.toString(gameEnd));
                    if (Arrays.equals(getPlayerCoordinate(), gameEnd)){
                        GameState.state = GameState.MENU;
                        break;
                    }
                }



            }
        }else {
            if (e.getKeyCode() == KeyEvent.VK_SPACE){
                dialogWindow.DialogCounter();
            }
        }


        if (e.getKeyCode()== KeyEvent.VK_Q){
            if (devMode) {
                Helpers.setDevMode(false);
            }else {
                Helpers.setDevMode(true);
            };
        }

//        if (e.getKeyCode() == KeyEvent.VK_1){
//            levelManager.setCurrentLevel(CITY);
//        } else if (e.getKeyCode() == KeyEvent.VK_2) {
//            levelManager.setCurrentLevel(DEBUGGING);
//        } else if(e.getKeyCode() == KeyEvent.VK_3){
//            levelManager.setCurrentLevel(OFFICE);
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() ==   KeyEvent.VK_W){
            player.setAtas(false);
        }

        if (e.getKeyCode() ==   KeyEvent.VK_A){
            player.setKiri(false);

        }

        if (e.getKeyCode() ==   KeyEvent.VK_S){
            player.setBawah(false);

        }

        if (e.getKeyCode() ==   KeyEvent.VK_D){
            player.setKanan(false);
        }
        player.SetisPlayerMoving(false);
        player.setAniIndex(0);
    }

    public void resetMaxXYOffset( Level curenLevel){
        this.maxHeightGame =  (curenLevel.getLevelDataList().get(0).getLevelData().length * Game.TILES_SIZE);
        this.maxWidthGame =  (curenLevel.getLevelDataList().get(0).getLevelData()[0].length * Game.TILES_SIZE);
        this.maXXOfflevelSet = maxWidthGame - Game.GAME_WIDTH;
        this.maxYOfflevelSet = maxHeightGame - Game.GAME_HEIGHT;
    }

    public Player getPlayer() {
        return player;
    }

    public int[] getPlayerCoordinate() {
        int[] playerCoordinate = new int[2];
        playerCoordinate[0] = (int) Math.floor(player.getHitBox().y / Game.TILES_SIZE);
        playerCoordinate[1] = (int) Math.floor(player.getHitBox().x / Game.TILES_SIZE);
        return playerCoordinate;
    }


}
