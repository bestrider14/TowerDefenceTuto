package main.scenes;

import main.Game;
import main.GameStates;
import main.scenes.ui.Button;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static main.GameStates.PLAYING;
import static main.GameStates.SETTINGS_MENU;

public class MainMenu extends GameScene implements SceneMethods
{

	private final BufferedImage img;
	private ArrayList<BufferedImage> sprites = new ArrayList<>();

	private Button buttonStart, buttonExit, buttonSettings;

	public MainMenu(Game game)
	{
		super(game);
		img = importImg("spriteatlas.png");
		loadSprites(32, 32, 10, 3);
		initButtons();
	}

	private void initButtons()
	{
		buttonStart = new Button("Start", 50,50, 100, 30);
		buttonSettings = new Button("Settings", 50,100, 100, 30);
		buttonExit = new Button("Exit", 50,150, 100, 30);
	}

	@Override
	public void render(Graphics2D g)
	{
		drawButtons(g);
	}

	@Override
	public void mouseClicked(int x, int y)
	{
		if(buttonStart.getBounds().contains(x, y))
			GameStates.setGameState(PLAYING);
		else if (buttonSettings.getBounds().contains(x, y))
			GameStates.setGameState(SETTINGS_MENU);
		else if (buttonExit.getBounds().contains(x, y))
			game.setRunning(false);
	}

	@Override
	public void mouseMoved(int x, int y)
	{
		buttonStart.setMouseOver(buttonStart.getBounds().contains(x, y));
		buttonSettings.setMouseOver(buttonSettings.getBounds().contains(x, y));
		buttonExit.setMouseOver(buttonExit.getBounds().contains(x, y));
	}

	@Override
	public void mousePressed(int x, int y)
	{
		buttonStart.setMousePressed(buttonStart.getBounds().contains(x, y));
		buttonSettings.setMousePressed(buttonSettings.getBounds().contains(x, y));
		buttonExit.setMousePressed(buttonExit.getBounds().contains(x, y));
	}

	@Override
	public void mouseReleased(int x, int y)
	{
		resetButtons();
	}

	@Override
	public void keyPressed(int key, int modifiers)
	{
	}

	private void drawButtons(Graphics2D g)
	{
		buttonStart.draw(g);
		buttonSettings.draw(g);
		buttonExit.draw(g);
	}

	private void resetButtons()
	{
		buttonStart.resetStates();
		buttonSettings.resetStates();
		buttonExit.resetStates();
	}

	private void loadSprites(int spriteWidth, int spriteHeight, int numSpritesByRow, int numSpritesByColumn)
	{
		for (int y = 0; y < numSpritesByColumn; y++)
		{
			for (int x = 0; x < numSpritesByRow; x++)
			{
				sprites.add(img.getSubimage(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight));
			}
		}
	}

	private BufferedImage importImg(String name)
	{
		BufferedImage img = null;
		InputStream is = getClass().getResourceAsStream("/" + name);

		if (is != null)
		{
			try
			{
				img = ImageIO.read(is);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			throw new RuntimeException("Could not load image: " + name);
		}

		return img;
	}
}
