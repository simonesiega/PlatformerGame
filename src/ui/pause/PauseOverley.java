package ui.pause;

import mainpack.Game;
import ui.MenuButton;
import utils.Constant;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constant.UI.PauseButtons.*;

public class PauseOverley {

    private BufferedImage _background;
    private int _bgX, _bgY, _bgWidth, _bgHeight;

    private SoundButton _musicButton, _sfxButton;

    public PauseOverley() {
        loadBackground();

        createSoundButton();
    }

    private void createSoundButton() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);

        _musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        _sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    private void loadBackground() {
        _background = LoadSave.getSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        _bgWidth = (int)(_background.getWidth() * Game.SCALE);
        _bgHeight = (int)(_background.getHeight() * Game.SCALE);
        _bgX = (Game.GAME_WIDTH / 2) - (_bgWidth / 2);
        _bgY = (int) (25 * Game.SCALE);
    }

    public void update(){
        _musicButton.update();
        _sfxButton.update();
    }

    public void draw(Graphics g){
        // Background
        g.drawImage(_background, _bgX, _bgY, _bgWidth, _bgHeight, null);

        // Sound Buttons
        _musicButton.draw(g);
        _sfxButton.draw(g);
    }

    public void mouseDragged(MouseEvent e){

    }

    public void mousePressed(MouseEvent e) {
        if (isMouseIn(e, _musicButton)) _musicButton.set_mousePressed(true);
        else if (isMouseIn(e, _sfxButton)) _sfxButton.set_mousePressed(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isMouseIn(e, _musicButton)){
            if (_musicButton.is_mousePressed()){
                _musicButton.set_muted(!_musicButton.is_muted()); // true to false, false to true
            }
        }
        else if (isMouseIn(e, _sfxButton)) {
            if (_sfxButton.is_mousePressed()){
                _sfxButton.set_muted(!_sfxButton.is_muted()); // true to false, false to true
            }
        }

        _musicButton.resetBools();
        _sfxButton.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        _musicButton.set_mouseOver(false);
        _sfxButton.set_mouseOver(false);

        if (isMouseIn(e, _musicButton)) _musicButton.set_mouseOver(true);
        else if (isMouseIn(e, _sfxButton)) _sfxButton.set_mouseOver(true);
    }

    public boolean isMouseIn(MouseEvent e, PauseButton b){
        return (b.get_hitBoxButton().contains(e.getX(), e.getY()));
    }
}
