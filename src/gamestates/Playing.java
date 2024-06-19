package gamestates;

import entities.Player;
import levels.LevelManager;
import mainpack.Game;
import ui.pause.PauseOverley;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods{

    private Player _player;
    private LevelManager _levelManager;

    private PauseOverley _pauseOverley;
    private boolean _pausedGame = false;

    public Playing(Game game) {
        super(game);

        initClasses();
    }

    public Player getPlayer(){
        return _player;
    }

    private void initClasses() {
        _levelManager = new LevelManager(_game);
        _player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
        _player.loadLevelData(_levelManager.getCurrentLevel().getLevelData());
        _pauseOverley = new PauseOverley(this);
    }

    public void windowFocusLost() {
        _player.resetDirBoolean();
    }

    @Override
    public void update() {
        if (_pausedGame) _pauseOverley.update();

        else {
            _levelManager.update();
            _player.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        _levelManager.draw(g);
        _player.render(g);

        if (_pausedGame) _pauseOverley.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            _player.set_playerAttacking(true);
        }
    }

    public void mouseDragged(MouseEvent e){
        if (_pausedGame) _pauseOverley.mouseDragged(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (_pausedGame) _pauseOverley.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (_pausedGame) _pauseOverley.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (_pausedGame) _pauseOverley.mouseMoved(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                _player.set_spacebarPressed(true);
                break;
            case KeyEvent.VK_A:
                _player.set_leftPressed(true);
                break;
            case KeyEvent.VK_D:
                _player.set_rightPressed(true);
                break;
            case KeyEvent.VK_ESCAPE:
                _pausedGame = !_pausedGame;
        }
    }

    @Override
    public void KeyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                _player.set_spacebarPressed(false);
                break;
            case KeyEvent.VK_A:
                _player.set_leftPressed(false);
                break;
            case KeyEvent.VK_D:
                _player.set_rightPressed(false);
                break;
        }
    }

    public void unpauseGame(){
        _pausedGame = false;
    }
}
