package mainpack;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constant.PlayerConstants.*;
import static utils.Constant.DirectionConstants.*;


public class GamePanel extends JPanel {

    private float _xDelta = 100, _yDelta = 100;
    private int _animationTick, _animationIndex, _animationSpeed = 15;
    private int _playerAction = IDLE;
    private int _playerDirection = -1;

    private boolean _playerMoving = false;

    private BufferedImage _img;
    private BufferedImage[][] _animations;

    public GamePanel() {
        MouseInputs mouseInputs = new MouseInputs(this);

        importImg();
        loadAnimations();
        
        setPanelSize();

        addKeyListener(new KeyboardInputs(this)); // Keyboard input
        addMouseListener(mouseInputs); // Mouse input
        addMouseMotionListener(mouseInputs);

        setFocusable(true);
        requestFocus();
    }

    private void loadAnimations() {
        _animations = new BufferedImage[9][6];

        for (int i = 0; i < _animations.length; i++) {
            for (int j = 0; j < _animations[i].length; j++) {
                _animations[i][j] = _img.getSubimage(j * 64, i * 40, 64, 40);
            }
        }
    }

    private void updateAnimationTick() {
        _animationTick++;
        if (_animationTick >= _animationSpeed) {
            _animationTick = 0;

            // Check correct img in the animation
            _animationIndex++;
            if (_animationIndex >= getSpriteAmount(_playerAction)) {
                _animationIndex = 0;
            }
        }
    }

    private void setAnimation() {
        if (_playerMoving) _playerAction = RUNNING;
        else _playerAction = IDLE;
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");

        try {
            assert is != null;
            _img = ImageIO.read(is);
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 720);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    private void updatePosition() {
        if (_playerMoving){
            switch (_playerDirection) {
                case LEFT -> _xDelta -= 5;
                case RIGHT -> _xDelta += 5;
                case UP -> _yDelta -= 5;
                case DOWN -> _yDelta += 5;
            }
        }
    }

    public void setDir(int dir){
        this._playerDirection = dir;
        _playerMoving = true;
    }

    public void setMoving(boolean moving){
        this._playerMoving = moving;
    }

    public void updateGame(){
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }

    /**
     * Call the method paintComponent in super class
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(_animations[_playerAction][_animationIndex], (int) _xDelta, (int) _yDelta, 256, 160, null);
    }

}
