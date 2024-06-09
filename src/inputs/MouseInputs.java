package inputs;

import mainpack.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel _gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this._gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * MouseMotionListener Invoked when the mouse cursor has been moved onto a component
     * No button
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
