package utils;

import mainpack.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "player_sprites.png";
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";
    public static final String MENU_BUTTONS = "button_atlas.png";
    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";

    public static BufferedImage getSpriteAtlas(String fileName){
        BufferedImage img = null;

        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);

        try {
            assert is != null;
             img = ImageIO.read(is);

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

        return img;
    }

    public static int[][] getLevelData(){
        int[][] data = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA);

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i)); // x , y

                int value = color.getRed();
                if (value >= 48) value = 0;
                // System.out.print(value + " ");
                data[i][j] = color.getRed();
            }
            // System.out.println();
        }
        return data;
    }
}
