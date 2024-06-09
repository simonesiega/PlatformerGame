package mainpack;

import javax.swing.*;

public class GameWindow {
    private final JFrame _frame;

    public GameWindow() {
        _frame = new JFrame();

        // Set up game frame
        _frame.setSize(400, 400);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setVisible(true);
    }
}
