package inputs;

import main.Game;
import main.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener
{
	private Game game;

	public KeyboardListener(Game game)
	{
		this.game = game;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		if (e.getKeyChar() == ']')
		{
			Game.showCounter = !Game.showCounter;
			if(Game.showCounter)
				System.out.println("Show counter");
			else
				System.out.println("Hide counter");
		}

		switch (GameStates.gameState)
		{
			case PLAYING ->			game.getPlaying().keyPressed(e.getKeyChar(), e.getModifiersEx());
			case EDITING -> 		game.getEditing().keyPressed(e.getKeyChar(), e.getModifiersEx());
			case MAIN_MENU ->		{}
			case SETTINGS_MENU ->	{}
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{

	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}
}
