package mainpack;

import entities.Player;
import levels.LevelManager;

import java.awt.*;

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

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static float SCALE = 1.5f;

    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    private Player _player;
    private LevelManager _levelManager;

    public Game(){
        initClasses();

        _gamePanel = new GamePanel(this);
        _gameWindow = new GameWindow(_gamePanel);
        _gamePanel.requestFocus(); //Requests that this Component gets the input focus

        startGameLoop();
    }

    public Player getPlayer(){
        return _player;
    }

    private void initClasses() {
        _levelManager = new LevelManager(this);
        _player = new Player(200, 200, (int) (64 * SCALE), (int) (40 * SCALE));
        _player.loadLevelData(_levelManager.getCurrentLevel().getLevelData());
    }

    private void startGameLoop(){
        _gameThread = new Thread(this);
        _gameThread.start();
    }

    private void update(){
        _player.update();
        _levelManager.update();
    }

    public void render(Graphics g){
        _levelManager.draw(g);
        _player.render(g);
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

    public void windowFocusLost() {
        _player.resetDirBoolean();
    }
}