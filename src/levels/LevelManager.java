package levels;

import mainpack.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static mainpack.Game.TILES_SIZE;

public class LevelManager {

    private Game _game;

    private BufferedImage[] _levelSprite;

    private Level _levelOne;

    public LevelManager(Game game) {
        this._game = game;
    //    _levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutsideSprites();
        this._levelOne = new Level(LoadSave.getLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);

        _levelSprite = new BufferedImage[48];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                int index = i * 12 + j;
                _levelSprite[index] = img.getSubimage(j * 32, i * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < Game.TILES_IN_WIDTH; j++) {
                int index = _levelOne.getSpriteIndex(j, i);
                g.drawImage(_levelSprite[index], TILES_SIZE * j, TILES_SIZE * i, TILES_SIZE, TILES_SIZE,null);
            }
        }
    }

    public void update(){

    }

    public Level getCurrentLevel() {
        return _levelOne;
    }
}
