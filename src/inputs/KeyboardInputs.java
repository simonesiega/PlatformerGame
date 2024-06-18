package inputs;

import gamestates.Gamestate;
import mainpack.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private GamePanel _gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this._gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU -> _gamePanel.getGame().getMenu().keyPressed(e);
            case PLAYING -> _gamePanel.getGame().getPlaying().keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state) {
            case MENU -> _gamePanel.getGame().getMenu().KeyReleased(e);
            case PLAYING -> _gamePanel.getGame().getPlaying().KeyReleased(e);
        }
    }
}
