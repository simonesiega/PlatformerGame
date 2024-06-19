package ui.pause;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constant.UI.URMButtons.*;

public class UrmButton extends PauseButton{

    private boolean _mouseOver, _mousePressed;

    private BufferedImage[] _urmImg;

    private final int _rowIndex;
    private int _index;


    public UrmButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        _rowIndex = rowIndex;

        loadUrmImg();
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

    private void loadUrmImg() {
        BufferedImage tmp = LoadSave.getSpriteAtlas(LoadSave.URM_BUTTONS);

        _urmImg = new BufferedImage[3];
        for (int i = 0; i < _urmImg.length; i++) {
            _urmImg[i] = tmp.getSubimage(i * URM_SIZE_DEFAULT,_rowIndex * URM_SIZE_DEFAULT,URM_SIZE_DEFAULT,URM_SIZE_DEFAULT);
        }
    }

    public void update(){
        // Switch 0 1 2 con due boolean
        _index = (_mouseOver) ? 1 : 0;
        _index = (_mousePressed) ? 2 : _index;
    }

    public void resetBools(){
        _mouseOver = false;
        _mousePressed = false;
    }

    public void draw(Graphics g){
        g.drawImage(_urmImg[_index], _x, _y, URM_SIZE, URM_SIZE, null);
    }
}
