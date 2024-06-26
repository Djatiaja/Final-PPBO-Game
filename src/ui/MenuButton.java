package ui;

import java.awt.*;

import Gamestate.GameState;
import utils.LoadSave;
import static utils.Constants.UI.Buttons.*;

import java.awt.image.BufferedImage;

public class MenuButton {
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = B_WIDTH /2;
    private GameState state;
    private BufferedImage[] imgs = new BufferedImage[2];
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public MenuButton(int xPos, int yPost, int rowIndex, GameState state){
        this.xPos = xPos;
        this.yPos = yPost;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }

    private void initBounds(){

        bounds = new Rectangle(xPos-xOffsetCenter, yPos,B_WIDTH, B_HEIGHT);
    }

    private void loadImgs(){

        BufferedImage temp = LoadSave.getAtlasSprite(LoadSave.menuButtons);
        this.imgs[0] = temp;
        BufferedImage cek = LoadSave.getAtlasSprite(LoadSave.quit);
        this.imgs[1] = cek;


    }
    public void draw(Graphics g){
        g.drawImage(imgs[rowIndex], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }
    public void update(){
        index = 0;
        if(mouseOver)
            index =1;
        if(mousePressed)
            index = 2;
    }

    public boolean isMouseOver(){

        return mouseOver = mouseOver;
    }

    public void setMouseOver(boolean mouseOver){

        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed(){

        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed){

        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds(){

        return bounds;
    }

    public void applyGamestate(){

        GameState.state = state;
    }
    public void resetBools(){
        mouseOver = false;
        mousePressed = true;

    }
}
