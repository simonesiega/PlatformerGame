package mainpack;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {

    public GameWindow(GamePanel gamePanel) {
        JFrame frame = new JFrame();

        // Set up game frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel); // makes up our window
        frame.setLocationRelativeTo(null);
        frame.setResizable(false); // no resizable
        frame.pack();
        frame.setVisible(true);

        frame.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
        });
    }
}
