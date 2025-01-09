package main;

import inputs.KeyboardListener;
import inputs.MouseListener;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel
{
	private final Game game;

	public GameScreen(Game game)
	{
		this.game = game;
		setPanelSize(640, 740);
	}

	private void setPanelSize(int width, int height)
	{
		Dimension size = new Dimension(width, height);

		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
	}

	public void initInputs()
	{
		MouseListener mouseListener = new MouseListener(game);
		KeyboardListener keyboardListener = new KeyboardListener(game);

		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
		addKeyListener(keyboardListener);

		requestFocus();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		game.getRender().render(g2d);
	}

}
