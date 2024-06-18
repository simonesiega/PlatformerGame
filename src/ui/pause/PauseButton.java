package ui.pause;

import java.awt.*;

public class PauseButton {

    protected int _x, _y, _width, _height;
    protected Rectangle _hitBoxButton;

    public PauseButton(int x, int y, int width, int height) {
        this._x = x;
        this._y = y;
        this._width = width;
        this._height = height;

        initBounds();
    }

    public int get_x() {
        return _x;
    }

    public void set_x(int _x) {
        this._x = _x;
    }

    public int get_y() {
        return _y;
    }

    public void set_y(int _y) {
        this._y = _y;
    }

    public int get_width() {
        return _width;
    }

    public void set_width(int _width) {
        this._width = _width;
    }

    public int get_height() {
        return _height;
    }

    public void set_height(int _height) {
        this._height = _height;
    }

    public Rectangle get_hitBoxButton() {
        return _hitBoxButton;
    }

    public void set_hitBoxButton(Rectangle _hitBoxButton) {
        this._hitBoxButton = _hitBoxButton;
    }

    private void initBounds() {
        _hitBoxButton = new Rectangle(_x, _y, _width, _height);
    }
}
