package main;

import main.managers.TileManager;
import main.objects.PathPoint;
import main.objects.Tile;
import main.scenes.*;
import utils.LoadSave;

import javax.swing.*;

public class Game extends JFrame implements Runnable
{
	private GameScreen gameScreen;
	private final TileManager tileManager;
	private Render render;
	private Thread gameThread;

	private double fpsSet;
	private final double upsSet;

	private boolean running = true;

	// Scenes
	private Playing playing;
	private MainMenu mainMenu;
	private SettingsMenu settingsMenu;
	private Editing editing;

	public static boolean showCounter = true;

	public Game(double fpsSet, double upsSet)
	{
		this.fpsSet = fpsSet;
		this.upsSet = upsSet;

		setTitle("TowerDefense");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		tileManager = new TileManager();

		initScnenes();
		createDefaultLevel();

		add(gameScreen);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initScnenes()
	{
		this.render = new Render(this);
		this.gameScreen = new GameScreen(this);
		this.mainMenu = new MainMenu(this);
		this.settingsMenu = new SettingsMenu(this);
		this.playing = new Playing(this);
		this.editing = new Editing(this);

	}

	private void createDefaultLevel()
	{
		int[] array = new int[400];
		PathPoint start = new PathPoint(0,0, PathPoint.PathPointType.START, null);
		PathPoint end = new PathPoint(0,0, PathPoint.PathPointType.END, null);
		LoadSave.createLevel("new_level", array, start, end);
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
		updateTick();

		switch (GameStates.gameState)
		{
			case PLAYING -> playing.update();
			case MAIN_MENU ->
			{
			}
			case SETTINGS_MENU ->
			{
			}
			case EDITING ->
			{
			}
		}
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

	public Editing getEditing()
	{
		return editing;
	}

	public void setRunning(boolean running)
	{
		this.running = running;
	}

	public TileManager getTileManager()
	{
		return tileManager;
	}

	private void updateTick()
	{
		for (Tile tile : tileManager.tiles.values())
			tile.updateTick();
	}
}
