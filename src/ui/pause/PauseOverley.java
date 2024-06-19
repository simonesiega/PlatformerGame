package ui.pause;

import gamestates.Gamestate;
import gamestates.Playing;
import mainpack.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constant.UI.PauseButtons.*;
import static utils.Constant.UI.URMButtons.*;
import static utils.Constant.UI.VolumeButtons.*;

public class PauseOverley {

    private BufferedImage _background;
    private int _bgX, _bgY, _bgWidth, _bgHeight;

    private SoundButton _musicButton, _sfxButton;
    private UrmButton _menuButton, _replayButton, _unpauseButton;
    private VolumeButton _volumeButton;

    private final Playing _currentMatch;

    public PauseOverley(Playing pl) {
        this._currentMatch = pl;

        loadBackground();

        createSoundButton();
        createUrmButton();
        createVolumeButton();
    }

    private void createSoundButton() {
        int soundX = (int) (450 * Game.SCALE);
        int musicY = (int) (140 * Game.SCALE);
        int sfxY = (int) (186 * Game.SCALE);

        _musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        _sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    private void createUrmButton() {
        int menuX = (int) (313 * Game.SCALE);
        int replayX = (int) (387 * Game.SCALE);
        int unpauseX = (int) (462 * Game.SCALE);

        int bY = (int) (325 * Game.SCALE);

        _menuButton = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        _replayButton = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
        _unpauseButton = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
    }

    private void createVolumeButton() {
        int volumeX = (int) (309 * Game.SCALE);
        int volumeY = (int) (278 * Game.SCALE);

        _volumeButton = new VolumeButton(volumeX, volumeY, SLIDER_WIDTH, VOLUME_HEIGHT);
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

        _menuButton.update();
        _replayButton.update();
        _unpauseButton.update();

        _volumeButton.update();
    }

    public void draw(Graphics g){
        // Background
        g.drawImage(_background, _bgX, _bgY, _bgWidth, _bgHeight, null);

        // Sound Buttons
        _musicButton.draw(g);
        _sfxButton.draw(g);

        // Urm Buttons
        _menuButton.draw(g);
        _replayButton.draw(g);
        _unpauseButton.draw(g);

        // Volume button
        _volumeButton.draw(g);
    }

    public void mouseDragged(MouseEvent e){
        if (_volumeButton.is_mousePressed()){
            _volumeButton.changeX(e.getX());
        }
    }

    public void mousePressed(MouseEvent e) {
        if (isMouseIn(e, _musicButton)) _musicButton.set_mousePressed(true);
        else if (isMouseIn(e, _sfxButton)) _sfxButton.set_mousePressed(true);
        else if (isMouseIn(e, _menuButton)) _menuButton.set_mousePressed(true);
        else if (isMouseIn(e, _replayButton)) _replayButton.set_mousePressed(true);
        else if (isMouseIn(e, _unpauseButton)) _unpauseButton.set_mousePressed(true);
        else if (isMouseIn(e, _volumeButton)) _volumeButton.set_mousePressed(true);
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
        else if (isMouseIn(e, _menuButton)) {
            if (_menuButton.is_mousePressed()){
                Gamestate.state = Gamestate.MENU;
                _currentMatch.unpauseGame();
            }
        }
        else if (isMouseIn(e, _replayButton)) {
            if (_replayButton.is_mousePressed()){
                System.out.println("reply lv");
            }
        }
        else if (isMouseIn(e, _unpauseButton)) {
            if (_unpauseButton.is_mousePressed()){
                _currentMatch.unpauseGame();
            }
        }

        _musicButton.resetBools();
        _sfxButton.resetBools();
        _menuButton.resetBools();
        _replayButton.resetBools();
        _unpauseButton.resetBools();
        _volumeButton.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        _musicButton.set_mouseOver(false);
        _sfxButton.set_mouseOver(false);
        _menuButton.set_mouseOver(false);
        _replayButton.set_mouseOver(false);
        _unpauseButton.set_mouseOver(false);
        _volumeButton.set_mouseOver(false);

        if (isMouseIn(e, _musicButton)) _musicButton.set_mouseOver(true);
        else if (isMouseIn(e, _sfxButton)) _sfxButton.set_mouseOver(true);
        else if (isMouseIn(e, _menuButton)) _menuButton.set_mouseOver(true);
        else if (isMouseIn(e, _replayButton)) _replayButton.set_mouseOver(true);
        else if (isMouseIn(e, _unpauseButton)) _unpauseButton.set_mouseOver(true);
        else if (isMouseIn(e, _volumeButton)) _volumeButton.set_mouseOver(true);
    }

    public boolean isMouseIn(MouseEvent e, PauseButton b){
        return (b.get_hitBoxButton().contains(e.getX(), e.getY()));
    }
}
