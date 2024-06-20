package mainpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {

    private Point _offset;

    public GameWindow(GamePanel gamePanel) {
        JFrame frame = new JFrame();

        // Create a custom title bar
        JPanel titleBar = getjPanel(frame);

        // Use the custom title bar and make the frame undecorated
        frame.setUndecorated(true);
        frame.setLayout(new BorderLayout());
        frame.add(titleBar, BorderLayout.NORTH);
        frame.add(gamePanel, BorderLayout.CENTER); // makes up our window

        // Set up game frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false); // no resizable
        frame.pack();
        frame.setLocationRelativeTo(null);
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

    private JPanel getjPanel(JFrame frame) {
        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(0,0,0,255)); // To change color - 4 ph alpha
        titleBar.setPreferredSize(new Dimension(frame.getWidth(), 30));

        // Add title text
        JLabel titleLabel = new JLabel("PL Game");
        titleLabel.setForeground(Color.WHITE);
        titleBar.add(titleLabel);

        // Allow dragging of the window
        titleBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                _offset = e.getPoint();
            }
        });
        titleBar.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point currentScreenLocation = e.getLocationOnScreen();
                frame.setLocation(currentScreenLocation.x - _offset.x, currentScreenLocation.y - _offset.y);
            }
        });
        return titleBar;
    }
}

// Old class - no black bar
/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {

    public GameWindow(GamePanel gamePanel) {
        JFrame frame = new JFrame("Prova");

        // Set up game frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel); // makes up our window
        frame.setResizable(false); // no resizable
        frame.pack();
        frame.setLocationRelativeTo(null);
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

    public static void main(String[] args) {
        // Assuming GamePanel is another class in your project
        GamePanel gamePanel = new GamePanel();
        new GameWindow(gamePanel);
    }
}
 */
