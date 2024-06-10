package levels;

public class Level {

    private final int[][] _lvlData;

    public Level(int[][] data) {
        this._lvlData = data;
    }

    public int getSpriteIndex(int x, int y) {
        return this._lvlData[y][x];
    }

    public int[][] getLevelData() {
        return this._lvlData;
    }
}
