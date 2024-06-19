package inputs;

import gamestates.Gamestate;
import mainpack.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Objects;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel _gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this._gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Objects.requireNonNull(Gamestate.state) == Gamestate.PLAYING) {
            _gamePanel.getGame().getPlaying().mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                _gamePanel.getGame().getMenu().mousePressed(e);
                break;

            case PLAYING:
                _gamePanel.getGame().getPlaying().mousePressed(e);
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                _gamePanel.getGame().getMenu().mouseReleased(e);
                break;

            case PLAYING:
                _gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (Objects.requireNonNull(Gamestate.state) == Gamestate.PLAYING) {
            _gamePanel.getGame().getPlaying().mouseDragged(e);
        }
        /*
        *switch (Gamestate.state) {
            case PLAYING:
                _gamePanel.getGame().getPlaying().mouseDragged(e);
                break;
        }
         */
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                _gamePanel.getGame().getMenu().mouseMoved(e);
                break;

            case PLAYING:
                _gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
        }
    }
}
