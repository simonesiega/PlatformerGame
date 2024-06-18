package ui.pause;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constant.UI.PauseButtons.*;

public class SoundButton extends PauseButton{

    private boolean _mouseOver, _mousePressed;
    private boolean _muted;

    private BufferedImage[][] _soundImg;
    private int _rowIndex, _colIndex;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);

        loadSoundImg();
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

    public boolean is_muted() {
        return _muted;
    }

    public void set_muted(boolean _muted) {
        this._muted = _muted;
    }

    private void loadSoundImg() {
        BufferedImage tmp = LoadSave.getSpriteAtlas(LoadSave.SOUND_BUTTONS);

        _soundImg = new BufferedImage[2][3];
        for (int i = 0; i < _soundImg.length; i++) {
            for (int j = 0; j < _soundImg[i].length; j++) {
                _soundImg[i][j] = tmp.getSubimage(j * SOUND_SIZE_DEFAULT, i * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
            }
        }
    }

    public void update(){
        _rowIndex = (_muted) ? 1 : 0;

        // Switch 0 1 2 con due boolean
        _colIndex = (_mouseOver) ? 1 : 0;
        _colIndex = (_mousePressed) ? 2 : _colIndex;
    }

    public void resetBools(){
        _mouseOver = false;
        _mousePressed = false;
    }

    public void draw(Graphics g){
        g.drawImage(_soundImg[_rowIndex][_colIndex], _x, _y, _width, _height, null);
    }
}
