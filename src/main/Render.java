package main;

import java.awt.*;

public class Render
{
	private final Game game;

	public Render(Game game)
	{
		this.game = game;
	}

	public void render(Graphics2D g)
	{
		switch (GameStates.gameState)
		{
			case PLAYING ->			game.getPlaying().render(g);
			case EDITING -> 		game.getEditing().render(g);
			case SETTINGS_MENU ->	game.getSettingsMenu().render(g);
			case MAIN_MENU ->		game.getMainMenu().render(g);
		}
	}
}
