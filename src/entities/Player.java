package entities;

import mainpack.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;


import static utils.HelpMethods.*;
import static utils.Constant.PlayerConstants.*;

public class Player extends Entity{

    private BufferedImage[][] _animations;

    private int _animationTick, _animationIndex;
    private final int _animationSpeed = 30;
    private int _playerAction = IDLE;

    private int[][] _levelData;

    private boolean _playerMoving = false, _playerAttacking = false, _playerInAir = false;
    private boolean _leftPressed, _rightPressed, _upPressed, _downPressed, _spacebarPressed;

    private final float _speedMov = 2.0f * Game.SCALE;
    private float _airSpeed = 1f;
    private final float _gravity = 0.04f * Game.SCALE, _jumpSpeed = -2.25f * Game.SCALE, fallSpeedAfterCollision = 0.5f * Game.SCALE;

    private float xDrawOffset = 21 * Game.SCALE, yDrawOffset = 4 * Game.SCALE;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitBox(x, y, 20*Game.SCALE, 27*Game.SCALE);
    }

    public void update(){
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(_animations[_playerAction][_animationIndex], (int)(_hitBox.x - xDrawOffset), (int) (_hitBox.y - yDrawOffset), _width, _height, null);
    //    drawHitBox(g); Only for debugging
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

        _animations = new BufferedImage[9][6];

        for (int i = 0; i < _animations.length; i++) {
            for (int j = 0; j < _animations[i].length; j++) {
                _animations[i][j] = img.getSubimage(j * 64, i * 40, 64, 40);
            }
        }
    }

    public void loadLevelData(int[][] levelData){
        _levelData = levelData;
        if (!isEntityOnFloor(_hitBox, _levelData)) _playerInAir = true;
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

        if (_playerInAir){
            // Going up
            if (_airSpeed < 0) _playerAction = JUMPING;
            else _playerAction = FALLING;
        }

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

        if (_spacebarPressed) playerJump();

        if (!_leftPressed && !_rightPressed && !_playerInAir)
            return;

        float xSpeed = 0;

        if (_leftPressed)
            xSpeed -= _speedMov;
        if (_rightPressed)
            xSpeed += _speedMov;

        if (!_playerInAir){
            if (!isEntityOnFloor(_hitBox, _levelData)){
                _playerInAir = true;
            }
        }

        if (_playerInAir) {
            if (canMoveHere(_hitBox.x,  _hitBox.y + _airSpeed, _hitBox.width, _hitBox.height, _levelData)){
                _hitBox.y += _airSpeed;
                _airSpeed += _gravity;
                updateXPos(xSpeed);
            } else {
                // Under roof or above floor
                _hitBox.y = getEntityYPos(_hitBox, _airSpeed);
                if (_airSpeed > 0){
                    resetInAir();
                } else {
                    _airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
        }

        _playerMoving = true;
    }

    private void playerJump() {
        // No double jump
        if (_playerInAir) return;

        _playerInAir = true;
        _airSpeed = _jumpSpeed;
    }

    private void updateXPos(float xSpeed) {
        if (canMoveHere(_hitBox.x + xSpeed, _hitBox.y, _hitBox.width, _hitBox.height, _levelData)) {
            _hitBox.x += xSpeed;
        } else {
            _hitBox.x = getEntityXPosNextToWall(_hitBox, xSpeed);
        }
    }

    private void resetInAir() {
        _playerInAir = false;
        _airSpeed = 0;
    }

    public void resetDirBoolean() {
        _rightPressed = false;
        _leftPressed = false;
        _upPressed = false;
        _downPressed = false;
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

    public boolean is_spacebarPressed() {
        return _spacebarPressed;
    }

    public void set_spacebarPressed(boolean _spacebarPressed) {
        this._spacebarPressed = _spacebarPressed;
    }
}
