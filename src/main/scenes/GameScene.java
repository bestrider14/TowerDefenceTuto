package main.scenes;

import main.Game;

public abstract class GameScene
{
	protected final int SPRITE_SIZE = 32;

	protected Game game;
	public GameScene(Game game)
	{
		this.game = game;
	}
	public Game getGame()
	{
		return game;
	}
}
