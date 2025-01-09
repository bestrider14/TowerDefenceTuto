package main.enemies;

import utils.enumeration.Direction;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Enemy
{
	protected final Point2D.Double position;
	protected final Rectangle bounds;
	protected final int size;
	protected int health;
	protected final int id;
	protected final EnemyType type;
	protected Direction direction;
	protected double speed = 0.5;

	public Enemy(int x, int y, int id, int size, EnemyType type)
	{
		this.position = new Point2D.Double(x, y);
		this.id = id;
		this.size = size;
		this.type = type;
		this.bounds = new Rectangle((int) position.x - size / 2, (int) position.y - size / 2, size, size);
		this.direction = null;
		System.out.println("Enemy created");
	}

	public void move()
	{
		switch (direction)
		{
			case UP -> position.y -= speed;
			case DOWN -> position.y += speed;
			case LEFT -> position.x -= speed;
			case RIGHT -> position.x += speed;
			case null ->
			{
			}
		}

		updateBounds();
	}

	public int getId()
	{
		return id;
	}

	private void updateBounds()
	{
		this.bounds.setBounds((int) position.x - size / 2, (int) position.y - size / 2, size, size);
	}

	public Point2D.Double getPosition()
	{
		return position;
	}

	public double getX()
	{
		return position.x;
	}

	public double getY()
	{
		return position.y;
	}

	public EnemyType getType()
	{
		return type;
	}

	public int getHealth()
	{
		return health;
	}

	public Direction getDirection()
	{
		return direction;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}

	public double getSpeed()
	{
		return speed;
	}

	public Rectangle getBounds()
	{
		return bounds;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public void stop()
	{
		this.speed = 0;
	}

	public void setPosition(double x, double y)
	{
		this.position.x = x;
		this.position.y = y;
	}

	public int getSize()
	{
		return this.size;
	}

	public enum EnemyType
	{
		ZOMBIE, BAT, KNIGHT, WOLF,
	}
}
