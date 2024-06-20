package gamestates;

import entities.Player;
import levels.LevelManager;
import mainpack.Game;
import ui.pause.PauseOverley;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods{

    private Player _player;
    private LevelManager _levelManager;

    private PauseOverley _pauseOverley;
    private boolean _pausedGame = false;

    private int _xLvlOffset;
    private final int _leftBorder = (int) (0.2 * Game.GAME_WIDTH), _rightBorder = (int) (0.8 * Game.GAME_WIDTH);
    private final int _lvlTilesWide = LoadSave.getLevelData()[0].length;
    private final int _maxTilesOffset = _lvlTilesWide - Game.TILES_IN_WIDTH;
    private final int _maxLvlOffsetX = _maxTilesOffset * Game.TILES_SIZE;

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
            checkCloseToBoarder();
        }
    }

    private void checkCloseToBoarder() {
        int playerX = (int) _player.getHitBox().x;
        int diff = playerX - _xLvlOffset;

        if ((diff) > _rightBorder)_xLvlOffset += (diff - _rightBorder);
        else if (diff < _leftBorder) _xLvlOffset += (diff - _leftBorder);

        if (_xLvlOffset > _maxLvlOffsetX) _xLvlOffset = _maxLvlOffsetX;
        else if (_xLvlOffset < 0) _xLvlOffset = 0;

    }

    @Override
    public void draw(Graphics g) {
        _levelManager.draw(g, _xLvlOffset);
        _player.render(g, _xLvlOffset);

        if (_pausedGame) {
            g.setColor(new Color(0,0,0,150));
            g.fillRect(0,0,Game.GAME_WIDTH,Game.GAME_HEIGHT);
            _pauseOverley.draw(g);
        }
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
