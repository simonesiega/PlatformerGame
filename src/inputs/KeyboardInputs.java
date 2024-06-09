package inputs;

import mainpack.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Constant.DirectionConstants.*;

public class KeyboardInputs implements KeyListener {

    private GamePanel _gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this._gamePanel = gamePanel;
    }

    /**
     * KeyListener Invoked when a key has been typed.
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                _gamePanel.setDir(UP);
                break;
            case KeyEvent.VK_A:
                _gamePanel.setDir(LEFT);
                break;
            case KeyEvent.VK_S:
                _gamePanel.setDir(DOWN);
                break;
            case KeyEvent.VK_D:
                _gamePanel.setDir(RIGHT);
                break;
        }
    }

    /**
     * Invoked when a key has been released.
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D:
                _gamePanel.setMoving(false);
                break;
        }
    }
}
