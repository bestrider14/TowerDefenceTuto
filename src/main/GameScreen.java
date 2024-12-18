package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel {

    private final Random rand = new Random();
    private final BufferedImage img;

    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    public GameScreen(BufferedImage img) {

        this.img = img;

        loadSprites(32,32, 10,3);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        for(int y = 0; y < 20; y++) {
            for(int x = 0; x < 20; x++) {
                g.drawImage(sprites.get(rand.nextInt(sprites.size())), x * 32, y * 32, null);
            }
        }
    }

    private void loadSprites(int spriteWidth, int spriteHeight, int numSpritesByRow, int numSpritesByColumn) {
            for(int y = 0; y < numSpritesByColumn; y++) {
                for(int x = 0; x < numSpritesByRow; x++) {
                    sprites.add(img.getSubimage(x * spriteWidth,y * spriteHeight,spriteWidth,spriteHeight));
                }
            }
    }

    private Color getRandomColor() {
        return new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }
}
