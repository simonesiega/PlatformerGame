package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float _x, _y;
    protected int _width, _height;

    protected Rectangle2D.Float _hitBox;

    public Entity(float x, float y, int width, int height){
        this._x = x;
        this._y = y;
        this._width = width;
        this._height = height;
    }

    // Ony for debugging
    protected void drawHitBox(Graphics g){
        g.setColor(Color.YELLOW);
        g.drawRect((int)_hitBox.x, (int)_hitBox.y, (int) _hitBox.width, (int) _hitBox.height);
    }

    protected void initHitBox(float x, float y, int width, int height) {
        _hitBox = new Rectangle2D.Float( x,y, width, height);
    }

    /*
    protected void updateHitBox(){
        _hitBox.x = (int) _x;
        _hitBox.y = (int) _y;
        // No width or height - always the same
    }
    */

    public Rectangle2D.Float getHitBox(){
        return _hitBox;
    }
}
