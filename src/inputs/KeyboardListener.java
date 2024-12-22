package inputs;

import main.Game;
import main.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener
{
	@Override
	public void keyTyped(KeyEvent e)
	{
		if(e.getKeyChar() == 'a')
			GameStates.gameState = GameStates.MAIN_MENU;
		else if (e.getKeyChar() == 's')
			GameStates.gameState = GameStates.SETTINGS_MENU;
		else if (e.getKeyChar() == 'd')
			GameStates.gameState = GameStates.PLAYING;

		else if (e.getKeyChar() == ']')
		{
			Game.showCounter = !Game.showCounter;
			if(Game.showCounter)
				System.out.println("Show counter");
			else
				System.out.println("Hide counter");
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
