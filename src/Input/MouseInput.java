package Input;

import Gamestate.GameState;
import Main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {
    private GamePanel gamePanel;
    public MouseInput(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state){
            case PlAYING :
                gamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
        }
//        if (e.getButton()==MouseEvent.BUTTON1){
//            gamePanel.getGame().getPlayer().setAttacking(true);
//            System.out.println("Atas "+gamePanel.getGame().getPlayer().isAtas());
//            System.out.println("Bawah "+gamePanel.getGame().getPlayer().isBawah());
//            System.out.println("Kiri "+gamePanel.getGame().getPlayer().isKiri());
//            System.out.println("Kanan "+gamePanel.getGame().getPlayer().isKanan());
//
//        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state){
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            case PlAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state){
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
                break;
            case PlAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            default:
                break;
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state){
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            case PlAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            default:
                break;
        }

    }
}
