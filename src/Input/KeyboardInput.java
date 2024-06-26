package Input;
import Gamestate.GameState;
import Gamestate.Menu;
import Main.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyboardInput implements KeyListener {
    private GamePanel gamePanel;
    public KeyboardInput(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameState.state){
            case PlAYING:{
                gamePanel.getGame().getPlaying().keyPressed(e);
            break;
            }
            case MENU:{
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            GameState.state = GameState.PlAYING;
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            GameState.state = GameState.MENU;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameState.state){
            case PlAYING:{
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            }
            case MENU:{
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            }
        }
    }
}
