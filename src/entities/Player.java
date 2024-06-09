package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constant.PlayerConstants.*;

public class Player extends Entity{

    private BufferedImage[][] _animations;

    private int _animationTick, _animationIndex;
    private final int _animationSpeed = 15;
    private int _playerAction = IDLE;
    private int _playerDirection = -1;

    private boolean _playerMoving = false, _playerAttacking = false;
    private boolean _leftPressed, _rightPressed, _upPressed, _downPressed;

    private final float _speedMov = 2.0f;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public boolean is_playerAttacking() {
        return _playerAttacking;
    }

    public void set_playerAttacking(boolean _playerAttacking) {
        this._playerAttacking = _playerAttacking;
    }

    public boolean is_downPressed() {
        return _downPressed;
    }

    public void set_downPressed(boolean downPressed) {
        this._downPressed = downPressed;
    }

    public boolean is_upPressed() {
        return _upPressed;
    }

    public void set_upPressed(boolean upPressed) {
        this._upPressed = upPressed;
    }

    public boolean is_rightPressed() {
        return _rightPressed;
    }

    public void set_rightPressed(boolean rightPressed) {
        this._rightPressed = rightPressed;
    }

    public boolean is_leftPressed() {
        return _leftPressed;
    }

    public void set_leftPressed(boolean leftPressed) {
        this._leftPressed = leftPressed;
    }

    public void update(){
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(_animations[_playerAction][_animationIndex], (int) _x, (int) _y, 256, 160, null);
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");

        try {
            assert is != null;
            BufferedImage img = ImageIO.read(is);

            _animations = new BufferedImage[9][6];

            for (int i = 0; i < _animations.length; i++) {
                for (int j = 0; j < _animations[i].length; j++) {
                    _animations[i][j] = img.getSubimage(j * 64, i * 40, 64, 40);
                }
            }

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

    private void updateAnimationTick() {
        _animationTick++;
        if (_animationTick >= _animationSpeed) {
            _animationTick = 0;

            // Check correct img in the animation
            _animationIndex++;
            if (_animationIndex >= getSpriteAmount(_playerAction)) {
                _animationIndex = 0;

                _playerAttacking = false;
            }
        }
    }

    private void setAnimation() {

        int startAnimation = _playerAction;

        if (_playerMoving) _playerAction = RUNNING;
        else _playerAction = IDLE;

        if (_playerAttacking) _playerAction = ATTACK_1;

        // No change animation - no problem
        // otherwise reset animation counter
        if (startAnimation != _playerAction) resetAnimationTick();
    }

    private void resetAnimationTick() {
        _animationTick = 0;
        _animationIndex = 0;
    }

    private void updatePosition() {

        _playerMoving = false;

        // Check x
        if (_leftPressed && !_rightPressed) {
            _x -= _speedMov;
            _playerMoving = true;
        }
        else if (_rightPressed && !_leftPressed) {
            _x += _speedMov;
            _playerMoving = true;
        }

        // Check y
        if (_upPressed && !_downPressed) {
            _y -= _speedMov;
            _playerMoving = true;
        }
        else if (_downPressed && !_upPressed) {
            _y += _speedMov;
            _playerMoving = true;
        }
    }

    public void resetDirBoolean() {
        _rightPressed = false;
        _leftPressed = false;
        _upPressed = false;
        _downPressed = false;
    }
}
