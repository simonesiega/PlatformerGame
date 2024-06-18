package ui;

import gamestates.Gamestate;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constant.UI.Buttons.*;

public class MenuButton {

    private final int _xPos, yPos;
    private final int _xOffsetCenter = B_WIDTH / 2;
    private final int _rowIndex;
    private int _indexImg;

    private boolean _mouseOver, _mousePressed;

    private final Gamestate _state;

    private BufferedImage[] _imgs;

    private Rectangle _hitBoxButton;

    public MenuButton(int x, int y, int rowIndex, Gamestate state) {
        this._xPos = x;
        this.yPos = y;
        this._rowIndex = rowIndex;
        this._state = state;

        loadImg();

        initBounds();
    }

    public boolean is_mouseOver() {
        return _mouseOver;
    }

    public void set_mouseOver(boolean _mouseOver) {
        this._mouseOver = _mouseOver;
    }

    public boolean is_mousePressed() {
        return _mousePressed;
    }

    public void set_mousePressed(boolean _mousePressed) {
        this._mousePressed = _mousePressed;
    }

    public Rectangle get_hitBoxButton() {
        return _hitBoxButton;
    }

    private void loadImg() {
        _imgs = new BufferedImage[3];
        BufferedImage tmp = LoadSave.getSpriteAtlas(LoadSave.MENU_BUTTONS);

        for (int i = 0; i < _imgs.length; i++) {
            _imgs[i] = tmp.getSubimage(i * B_WIDTH_DEFAULT, _rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
    }

    private void initBounds() {
        _hitBoxButton = new Rectangle(_xPos - _xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
    }

    public void draw(Graphics g) {
        g.drawImage(_imgs[_indexImg], _xPos - _xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }

    public void update(){
        _indexImg = 0;

        if (_mouseOver) _indexImg = 1;
        if (_mousePressed) _indexImg = 2;
    }

    public void applyGameState() {
        Gamestate.state = _state;
    }

    public void resetBools(){
        _mousePressed = false;
        _mouseOver = false;
    }
}
