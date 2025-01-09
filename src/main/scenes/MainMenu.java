package main.scenes;

import main.Game;
import main.GameStates;
import main.scenes.ui.Button;

import java.awt.*;

import static main.GameStates.*;

public class MainMenu extends GameScene implements SceneMethods
{
	private Button buttonStart, buttonEdit, buttonExit, buttonSettings;

	public MainMenu(Game game)
	{
		super(game);

		initButtons();
	}

	private void initButtons()
	{
		buttonStart = 		new Button("Start", 50,50, 100, 30);
		buttonEdit = 		new Button("Edit", 50,100, 100, 30);
		buttonSettings = 	new Button("Settings", 50,150, 100, 30);
		buttonExit = 		new Button("Exit", 50,200, 100, 30);
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
		{
			GameStates.setGameState(PLAYING);
			buttonStart.resetStates();
		}
		else if (buttonEdit.getBounds().contains(x, y))
		{
			GameStates.setGameState(EDITING);
			buttonEdit.resetStates();
		}
		else if (buttonSettings.getBounds().contains(x, y))
		{
			GameStates.setGameState(SETTINGS_MENU);
			buttonSettings.resetStates();
		}
		else if (buttonExit.getBounds().contains(x, y))
			game.setRunning(false);
	}

	@Override
	public void mouseMoved(int x, int y)
	{
		buttonStart.setMouseOver(buttonStart.getBounds().contains(x, y));
		buttonEdit.setMouseOver(buttonEdit.getBounds().contains(x, y));
		buttonSettings.setMouseOver(buttonSettings.getBounds().contains(x, y));
		buttonExit.setMouseOver(buttonExit.getBounds().contains(x, y));
	}

	@Override
	public void mousePressed(int x, int y)
	{
		buttonStart.setMousePressed(buttonStart.getBounds().contains(x, y));
		buttonEdit.setMousePressed(buttonEdit.getBounds().contains(x, y));
		buttonSettings.setMousePressed(buttonSettings.getBounds().contains(x, y));
		buttonExit.setMousePressed(buttonExit.getBounds().contains(x, y));
	}

	@Override
	public void mouseReleased(int x, int y)
	{
		resetButtons();
		mouseMoved(x, y);
	}

	@Override
	public void mouseDragged(int x, int y)
	{

	}

	@Override
	public void keyPressed(int key, int modifiers)
	{
	}

	private void drawButtons(Graphics2D g)
	{
		buttonStart.draw(g);
		buttonEdit.draw(g);
		buttonSettings.draw(g);
		buttonExit.draw(g);
	}

	private void resetButtons()
	{
		buttonStart.resetStates();
		buttonEdit.resetStates();
		buttonSettings.resetStates();
		buttonExit.resetStates();
	}

}
