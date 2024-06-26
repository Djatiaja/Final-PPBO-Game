package Main;
import Gamestate.GameState;
import Gamestate.Menu;
import Gamestate.Playing;
import java.awt.*;

public class Game implements  Runnable{
    private GamePanel gamePanel = new GamePanel(this);
    private GameWindow gameWindow ;
    private int FPS = 144;
    private int UPS = 200;
    private Playing playing;
    private Gamestate.Menu menu;
    public static final float SCALES = 2.0f;
    public static final int TILES_DEFAULT_SIZE = 32;
    public static final float TILES_SCALES = 1.5f;
    public static final int TILES_WIDTH=26;
    public static final int TILES_HEIGHT=14;
    public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE *TILES_SCALES);
    public static final int GAME_WIDTH = TILES_SIZE * TILES_WIDTH;
    public static final int GAME_HEIGHT = TILES_SIZE * TILES_HEIGHT;

    Thread gameThread;
    public Game(){
        initClass();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        gameThread = new Thread(this::run);
        gameThread.start();
    }

    public void initClass(){
        playing = new Playing(this);
        menu = new Menu(this);
    }

    public void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void update(){
        switch (GameState.state){
            case MENU :{
                menu.update();
                break;
            }
            case PlAYING:{
                playing.update();
                break;
            }
            default:
                System.exit(0);
                break;
        }
    }



    public void render(Graphics g){
        switch (GameState.state){
            case MENU :{
                menu.render(g);
                break;
            }
            case PlAYING:{
                playing.render(g);
                break;
            }

            case QUIT:{
                break;
            }
        }

    }
    @Override
    public void run() {
        double timePerFrame =  1000000000.0 /FPS;
        double timePerUpdate =  1000000000.0 /UPS;
        int frame = 0;
        double deltaU = 0;
        double deltaF = 0;
        int UPStick = 0;
        long lastCheck = System.nanoTime();
        long previusTime = System.nanoTime();


        while (true){
            long current = System.nanoTime();
            deltaF += (current-previusTime)/timePerFrame;
            deltaU += (current- previusTime)/timePerUpdate;
            previusTime = current;

            if (deltaU >=1){
                this.update();
                UPStick++;
                deltaU--;
            }

            if (deltaF >=1){
                gamePanel.repaint();
                frame ++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS" + frame +"   ||   " + "UPS" +UPStick);
                frame =0;
                UPStick=0;
            }
        }
    }
    public void lostFocus(){
        playing.getPlayer().reset();
    }

    public Playing getPlaying() {
        return playing;
    }

    public Menu getMenu() {
        return menu;
    }

    public GamePanel getGamePanel(){
        return gamePanel;
    }

}
