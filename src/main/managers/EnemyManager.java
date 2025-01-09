package main.managers;

import main.enemies.Enemy;
import main.enemies.EnemyFactory;
import main.scenes.Playing;
import utils.enumeration.Direction;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnemyManager
{
	private final Playing playing;
	private final Map<Enemy.EnemyType, BufferedImage> sprites = new HashMap<>();
	private final ArrayList<Enemy> enemies = new ArrayList<>();
	private final EnemyFactory enemyFactory;
	private int spriteSize;

	public EnemyManager(Playing playing)
	{
		this.playing = playing;
		enemyFactory = new EnemyFactory();

		loadEnemiesSprite();
	}

	private void loadEnemiesSprite()
	{
		BufferedImage[] enemiesSprites;
		enemiesSprites = SpriteManager.getInstance().getSprites(10, 11, 12, 13);

		sprites.put(Enemy.EnemyType.ZOMBIE, enemiesSprites[0]);
		sprites.put(Enemy.EnemyType.BAT, enemiesSprites[1]);
		sprites.put(Enemy.EnemyType.KNIGHT, enemiesSprites[2]);
		sprites.put(Enemy.EnemyType.WOLF, enemiesSprites[3]);

		spriteSize = enemiesSprites[0].getWidth();
	}

	public void update()
	{
		List<Enemy> enemiesToRemove = new ArrayList<>();

		for (Enemy e : enemies)
			enemiesToRemove.add(findPath(e));

		enemies.removeAll(enemiesToRemove);
	}

	private Enemy findPath(Enemy e)
	{
		if (isNextTileIsRoad(e) && e.getDirection() != null)
			e.move();
		else if (isAtEnd(e))
		{
			enemyAtTheEnd();
			return e;
		}
		else if (findNewPath(e))
			e.move();

		return null;
	}

	private void enemyAtTheEnd()
	{
		System.out.println("You lose some HP");
	}

	private boolean findNewPath(Enemy e)
	{
		for (Direction direction : getPossibleDirections(e))
		{
			Point2D.Double newPosition = calculateNewPosition(e, direction);
			if (isRoad(playing.getTileType(newPosition)))
			{
				e.setDirection(direction);
				fixOffset(e);
				return true;
			}
		}
		return false;
	}

	private void fixOffset(Enemy e)
	{
		double alignedX = (int) (e.getPosition().x / spriteSize) * spriteSize + 16;
		double alignedY = (int) (e.getPosition().y / spriteSize) * spriteSize + 16;

		e.setPosition(alignedX, alignedY);
	}

	private boolean isAtEnd(Enemy e)
	{
		Point2D.Double endPosition = playing.getEndPoint().getPoint();
		return roundPosition(e.getPosition()).equals(roundPosition(endPosition));
	}

	private Point roundPosition(Point2D.Double position)
	{
		return new Point((int) position.x / spriteSize, (int) position.y / spriteSize);
	}

	private boolean isNextTileIsRoad(Enemy e)
	{
		Point2D.Double newPosition = calculateNewPosition(e, e.getDirection());
		TileManager.TileCategory newTileType = playing.getTileType(newPosition);
		return isRoad(newTileType);
	}

	private Point2D.Double calculateNewPosition(Enemy e, Direction direction)
	{
		Point2D.Double newPosition = new Point2D.Double(e.getPosition().x, e.getPosition().y);

		switch (direction)
		{
			case UP -> newPosition.y -= e.getSpeed() + (double) spriteSize / 2;
			case DOWN -> newPosition.y += e.getSpeed() + (double) spriteSize / 2;
			case LEFT -> newPosition.x -= e.getSpeed() + (double) spriteSize / 2;
			case RIGHT -> newPosition.x += e.getSpeed() + (double) spriteSize / 2;
			case null ->
			{
			}
		}
		return newPosition;
	}

	private List<Direction> getPossibleDirections(Enemy e)
	{
		return switch (e.getDirection())
		{
			case UP, DOWN -> List.of(Direction.LEFT, Direction.RIGHT);
			case LEFT, RIGHT -> List.of(Direction.UP, Direction.DOWN);
			case null -> List.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);
		};
	}


	private boolean isRoad(TileManager.TileCategory tileType)
	{
		return tileType == TileManager.TileCategory.ROADS_CORNER || tileType == TileManager.TileCategory.ROADS_STRAIGHT;
	}

	public void addEnemy(int x, int y, Enemy.EnemyType type)
	{
		Enemy newEnemy = enemyFactory.createEnemy(x, y, enemies.size() + 1, spriteSize, type);
		enemies.add(newEnemy);
	}

	public void removeEnemy(Enemy enemy)
	{
		enemies.remove(enemy);
	}

	public void draw(Graphics2D g)
	{
		for (Enemy e : enemies)
			g.drawImage(sprites.get(e.getType()), (int) e.getX() - spriteSize/2, (int) e.getY() - spriteSize/2, null);
	}
}
