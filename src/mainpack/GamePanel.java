package mainpack;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static mainpack.Game.GAME_HEIGHT;
import static mainpack.Game.GAME_WIDTH;

public class GamePanel extends JPanel {

    private Game _game;

    public GamePanel(Game game) {
        MouseInputs mouseInputs = new MouseInputs(this);
        _game = game;
        
        setPanelSize();

        addKeyListener(new KeyboardInputs(this)); // Keyboard input
        addMouseListener(mouseInputs); // Mouse input
        addMouseMotionListener(mouseInputs);

        setFocusable(true);
        requestFocus();
    }

    public Game getGame() {
        return _game;
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        _game.render(g);
    }
}
