package mainpack;

import javax.swing.*;

public class GameWindow {
    private final JFrame _frame;

    public GameWindow(GamePanel gamePanel) {
        _frame = new JFrame();

        // Set up game frame
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.add(gamePanel); // makes up our window
        _frame.setLocationRelativeTo(null);
        _frame.setResizable(false); // no resizable
        _frame.pack();
        _frame.setVisible(true);
    }
}
