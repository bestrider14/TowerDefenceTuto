package main.scenes;

import main.Game;

import java.awt.*;

public class SettingsMenu extends GameScene implements SceneMethods
{
	public SettingsMenu(Game game)
	{
		super(game);
	}

	@Override
	public void render(Graphics2D g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(0,0,640,640);
	}

	@Override
	public void mouseClicked(int x, int y)
	{

	}

	@Override
	public void mouseMoved(int x, int y)
	{

	}

	@Override
	public void mousePressed(int x, int y)
	{

	}

	@Override
	public void mouseReleased(int x, int y)
	{

	}

	@Override
	public void keyPressed(int key, int modifiers)
	{

	}
}
