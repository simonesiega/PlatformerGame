package utils;

import mainpack.Game;

import java.awt.geom.Rectangle2D;

public class HelpMethods {

    public static boolean canMoveHere(float x, float y, float width, float height, int[][] levelData) {

        return (isNotSolid(x, y, levelData)) && (isNotSolid(x + width, y + height, levelData)) && (isNotSolid(x + width, y, levelData) && (isNotSolid(x, y + height, levelData)));
    }

    /**
     * Check if the cell is solid
     * True if is Not solid
     * False if is solid
     * @param x
     * @param y
     * @param levelData
     * @return
     */
    private static boolean isNotSolid(float x, float y, int[][] levelData){
        if (x < 0 || x >= Game.GAME_WIDTH)
            return false;
        if (y < 0 || y >= Game.GAME_HEIGHT)
            return false;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        int value = levelData[(int) yIndex][(int) xIndex];

        return value == 11; // Free cell
    }

    public static float getEntityXPosNextToWall(Rectangle2D.Float hitBox, float xSpeed){
        int currentTile = (int) (hitBox.x / Game.TILES_SIZE);

        // Check collision  left or right
        // NB no 0 because if == 0 no collision
        if ( xSpeed > 0){
            // Right
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - hitBox.width);
            return tileXPos + xOffset - 1;
        } else {
            // Left
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float getEntityYPos(Rectangle2D.Float hitBox, float xSpeed){
        int currentTile = (int) (hitBox.y / Game.TILES_SIZE);

        // Check collision  up or down
        // NB no 0 because if == 0 no collision
        if ( xSpeed > 0){
            // Falling - touching floor
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int)(Game.TILES_SIZE - hitBox.height);
            return tileYPos + yOffset - 1;
        } else {
            // Jumping
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static boolean isEntityOnFloor(Rectangle2D.Float hitBox, int[][] levelData){
        // Check the pixel below bottomLeft and bottomRight
        // Not because if not is not solid = if is solid
        return !(isNotSolid(hitBox.x, hitBox.y + hitBox.height + 1, levelData) && (isNotSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, levelData)));
    }
}
