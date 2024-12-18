package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame {

    private final GameScreen gameScreen;
    public Game() {

        BufferedImage img = importImg("spriteatlas.png");

        setTitle("TowerDefense");
        setSize(640, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameScreen = new GameScreen(img);
        add(gameScreen);

        setVisible(true);
    }

    private BufferedImage importImg(String name) {

        BufferedImage img = null;
        InputStream is = getClass().getResourceAsStream("/" + name);

        if (is != null) {
            try {
                img = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new RuntimeException("Could not load image: " + name);
        }

        return img;
    }

    public static void main(String[] args) {

        Game game = new Game();
    }
}
