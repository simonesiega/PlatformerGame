package gamestates;

import mainpack.Game;
import ui.MenuButton;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements Statemethods{

    private final MenuButton[] _buttons = new MenuButton[3];

    private BufferedImage _background, _backgroundMenu;
    private int _menuX, _menuY, _menuWidth, _menuHeight;

    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
        _backgroundMenu = LoadSave.getSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
    }

    private void loadButtons() {
        _buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, Gamestate.PLAYING);
        _buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, Gamestate.OPTIONS);
        _buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, Gamestate.QUIT);
    }

    private void loadBackground() {
        _background = LoadSave.getSpriteAtlas(LoadSave.MENU_BACKGROUND);
        _menuWidth = (int) (_background.getWidth() * Game.SCALE);
        _menuHeight = (int) (_background.getHeight() * Game.SCALE);
        _menuX = Game.GAME_WIDTH / 2 - (_menuWidth / 2);
        _menuY = (int) (45 * Game.SCALE);
    }

    @Override
    public void update() {
        for (MenuButton button : _buttons) {
            button.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(_backgroundMenu,0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);

        g.drawImage(_background, _menuX, _menuY, _menuWidth, _menuHeight, null);

        for (MenuButton button : _buttons) {
            button.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton button : _buttons) {
            if (isMouseIn(e, button)) {
                button.set_mousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton button : _buttons) {
            if (isMouseIn(e, button)) {
                if (button.is_mousePressed()){
                    button.applyGameState();
                }
                break;
            }
        }
        resetButton();
    }

    private void resetButton() {
        for (MenuButton button : _buttons) {
            button.resetBools();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton button : _buttons) {
           button.set_mouseOver(false);
        }

        for (MenuButton button : _buttons) {
            if (isMouseIn(e, button)) {
                button.set_mouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void KeyReleased(KeyEvent e) {

    }
}
