package Entity;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entitty {
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitBox;
    public Entitty(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height= height;
        this.width = width;
        setHitBox( x,  y,width,height);
    }

    public void setHitBox(float x, float y,int width,int height){
        this.hitBox = new Rectangle2D.Float();
        this.hitBox.height=height;
        this.hitBox.width = width;
        this.hitBox.y =y;
        this.hitBox.x =x;
    }

    public void drawHitbox(Graphics g, int xleveloffset, int ylevelOffset){
        g.setColor(Color.red);
        g.drawRect((int) hitBox.x -xleveloffset, (int) hitBox.y - ylevelOffset, (int) hitBox.width, (int) hitBox.height);
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }
}
