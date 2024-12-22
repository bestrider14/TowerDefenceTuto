package main;

import inputs.KeyboardListener;
import inputs.MouseListener;
import main.scenes.GameScene;
import main.scenes.MainMenu;
import main.scenes.Playing;
import main.scenes.SettingsMenu;

import javax.swing.*;

public class Game extends JFrame implements Runnable
{
	private GameScreen gameScreen;
	private Render render;
	private Thread gameThread;

	private double fpsSet;
	private final double upsSet;

	private boolean running = true;

	// Scenes
	private Playing playing;
	private MainMenu mainMenu;
	private SettingsMenu settingsMenu;

	public static boolean showCounter = true;

	public Game(double fpsSet, double upsSet)
	{
		this.fpsSet = fpsSet;
		this.upsSet = upsSet;

		setTitle("TowerDefense");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		initScnenes();

		add(gameScreen);
		pack();
		setVisible(true);
	}

	private void initScnenes()
	{
		this.render = new Render(this);
		this.gameScreen = new GameScreen(this);
		this.mainMenu = new MainMenu(this);
		this.settingsMenu = new SettingsMenu(this);
		this.playing = new Playing(this);
	}

	@Override
	public void run()
	{
		double timePerUpdate = 1000000000.0 / upsSet;
		double timePerFrame = 1000000000.0 / fpsSet;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();
        long now;

		int fps = 0;
		int ups = 0;

		while (running)
		{
            now = System.nanoTime();

			// Render
			if (now - lastFrame >= timePerFrame)
			{
				repaint();
				lastFrame = now;
				fps++;
			}

			// Update
			if (now - lastUpdate >= timePerUpdate)
			{
				update();
				lastUpdate = now;
				ups++;
			}

			// FPS | UPS counter
			if (System.currentTimeMillis() - lastTimeCheck >= 1000 && showCounter)
			{
				System.out.println("FPS: " + fps + " | UPS: " + ups);
				fps = 0;
				ups = 0;
				lastTimeCheck = System.currentTimeMillis();
			}
		}

		System.exit(0);
	}

	private void update()
	{

	}

	public void start()
	{
		gameThread = new Thread(this);
		gameScreen.initInputs();
		gameThread.start();
	}

	// Getters & Setters
	public Render getRender()
	{
		return render;
	}

	public Playing getPlaying()
	{
		return playing;
	}

	public MainMenu getMainMenu()
	{
		return mainMenu;
	}

	public SettingsMenu getSettingsMenu()
	{
		return settingsMenu;
	}

	public void setRunning(boolean running)
	{
		this.running = running;
	}
}
