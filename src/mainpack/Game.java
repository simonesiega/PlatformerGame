package mainpack;

/**
 * Main class
 * Manages the game
 */
public class Game {

    private GameWindow _gameWindow;
    private GamePanel _gamePanel;

    /**
     *
     */
    public Game(){
        _gameWindow = new GameWindow();
        _gamePanel = new GamePanel();
    }
}
