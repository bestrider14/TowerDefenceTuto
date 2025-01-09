package main;

public enum GameStates
{
	PLAYING, MAIN_MENU, SETTINGS_MENU, EDITING;

	public static GameStates gameState = MAIN_MENU;

	public static void setGameState(GameStates state)
	{
		gameState = state;
	}
}
