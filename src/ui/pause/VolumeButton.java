package ui.pause;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constant.UI.VolumeButtons.*;

public class VolumeButton extends PauseButton{

    private boolean _mouseOver, _mousePressed;

    private BufferedImage[] _volumeImg;
    private BufferedImage _slider;

    private int _index = 0;
    private int _buttonX;
    private final int _minX, _maxX;

    public VolumeButton(int x, int y, int width, int height) {
        super(x + (width / 2), y, VOLUME_WIDTH, height);

        _hitBoxButton.x -=  (VOLUME_WIDTH / 2);
        this._buttonX = _x + (_width / 2);
        this._x = x;
        this._width = width;

        this._minX = x + (VOLUME_WIDTH / 2);
        this._maxX = x + width - (VOLUME_WIDTH / 2);

        loadVolumeImgs();
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

    public void changeX(int x){
        if (x < _minX) _buttonX = _minX;
        else _buttonX = Math.min(x, _maxX);

        _hitBoxButton.x = _buttonX  - (VOLUME_WIDTH / 2);
    }

    private void loadVolumeImgs() {
        BufferedImage tmp = LoadSave.getSpriteAtlas(LoadSave.VOLUME_BUTTONS);

        _volumeImg = new BufferedImage[3];
        for (int i = 0; i < _volumeImg.length; i++) {
            _volumeImg[i] = tmp.getSubimage(i * VOLUME_DEFAULT_WIDTH, 0, VOLUME_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT);
        }

        _slider = tmp.getSubimage(3 * VOLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, VOLUME_DEFAULT_HEIGHT); // same height
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
        g.drawImage(_slider, _x, _y, _width, _height, null);
        g.drawImage(_volumeImg[_index], _buttonX - (VOLUME_WIDTH / 2), _y, VOLUME_WIDTH, _height, null);
    }
}
