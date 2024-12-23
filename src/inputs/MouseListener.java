package inputs;

import main.Game;
import main.GameStates;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseListener implements java.awt.event.MouseListener, MouseMotionListener
{
	private Game game;

	public MouseListener(Game game)
	{
		this.game = game;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		switch (GameStates.gameState)
		{
			case PLAYING ->			game.getPlaying().mouseClicked(e.getX(), e.getY());
			case MAIN_MENU ->		game.getMainMenu().mouseClicked(e.getX(), e.getY());
			case SETTINGS_MENU ->	{}
			case EXIT_MENU ->		{}
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		switch (GameStates.gameState)
		{
			case PLAYING ->			game.getPlaying().mousePressed(e.getX(), e.getY());
			case MAIN_MENU ->		game.getMainMenu().mousePressed(e.getX(), e.getY());
			case SETTINGS_MENU ->	{}
			case EXIT_MENU ->		{}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		switch (GameStates.gameState)
		{
			case PLAYING ->			game.getPlaying().mouseReleased(e.getX(), e.getY());
			case MAIN_MENU ->		game.getMainMenu().mouseReleased(e.getX(), e.getY());
			case SETTINGS_MENU ->	{}
			case EXIT_MENU ->		{}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		switch (GameStates.gameState)
		{
			case PLAYING ->			game.getPlaying().mouseDragged(e.getX(), e.getY());
			case MAIN_MENU ->		{}
			case SETTINGS_MENU ->	{}
			case EXIT_MENU ->		{}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		switch (GameStates.gameState)
		{
			case PLAYING ->			game.getPlaying().mouseMoved(e.getX(), e.getY());
			case MAIN_MENU ->		game.getMainMenu().mouseMoved(e.getX(), e.getY());
			case SETTINGS_MENU ->	{}
			case EXIT_MENU ->		{}
		}
	}
}
