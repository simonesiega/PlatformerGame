package mainpack;

/**
 * Main class
 * Manages the game
 */
public class Game implements Runnable {

    private final GameWindow _gameWindow;
    private final GamePanel _gamePanel;
    private Thread _gameThread;

    private final int _FPS_SET = 120;
    private final int _UPS_SET = 200;

    /**
     *
     */
    public Game(){
        _gamePanel = new GamePanel();
        _gameWindow = new GameWindow(_gamePanel);
        _gamePanel.requestFocus(); //Requests that this Component gets the input focus

        startGameLoop();
    }

    private void startGameLoop(){
        _gameThread = new Thread(this);
        _gameThread.start();
    }

    private void update(){
        _gamePanel.updateGame();
    }

    @Override
    public void run() {
        // Variables for reach FPS and UPS target
        double timerPerFrame = 1000000000.0 / _FPS_SET;
        double timePerUpdate = 1000000000.0 / _UPS_SET;

        double deltaU = 0;
        double deltaF = 0;

        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        int updates = 0;
        int frames = 0;

        while (true){

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timerPerFrame;
            previousTime = currentTime;

            if (deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1){
                _gamePanel.repaint();
                frames++;
                deltaF--;
            }

            // FPS and UPS print count
            if (System.currentTimeMillis() - lastCheck > 1000){
                System.out.println("FPS: " + frames + "| UPS: " + updates);
                lastCheck = System.currentTimeMillis();
                frames = 0;
                updates = 0;
            }
        }
    }
}