package main;

import inputs.KeyboardListener;
import inputs.MouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel
{
	private final Game game;

	private MouseListener mouseListener;
	private KeyboardListener keyboardListener;

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
		mouseListener = new MouseListener(game);
		keyboardListener = new KeyboardListener();

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
