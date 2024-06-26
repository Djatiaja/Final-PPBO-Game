package Gamestate;

import Main.Game;
import ui.MenuButton;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements StateMethod {
    private MenuButton[] buttons = new MenuButton[2];
    private BufferedImage backgroundImg;
    private BufferedImage logoImg;
    private int menuX, menuY, menuWidth, menuHeight;
    private int logoX, logoY, logoWidth, logoHeight;

    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
        loadLogo();
    }

    private void loadBackground() {
        this.backgroundImg = LoadSave.getAtlasSprite(LoadSave.menuBackgrund);
        menuWidth = this.backgroundImg.getWidth();
        menuHeight = this.backgroundImg.getHeight();
    }

    private void loadLogo() {
        this.logoImg = LoadSave.getAtlasSprite(LoadSave.logoGame);
        logoWidth = this.logoImg.getWidth();
        logoHeight = this.logoImg.getHeight();
        logoX = (Game.GAME_WIDTH - logoWidth) / 2;
        logoY = 50;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(logoImg, logoX, logoY, logoWidth, logoHeight, null);

        for (MenuButton mb : buttons) {
            mb.draw(g);
        }
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, 300, 0, GameState.PlAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, 450, 1, GameState.QUIT);
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons) {
            mb.update();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed()) {
                    mb.applyGamestate();
                }
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (MenuButton mb : buttons) {
            mb.resetBools();
        }
    }

    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons) {
            mb.setMouseOver(false);
        }

        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
