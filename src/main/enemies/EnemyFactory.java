package main.enemies;

public class EnemyFactory
{
	public Enemy createEnemy(int x, int y, int id, int size, Enemy.EnemyType type)
	{
		switch (type)
		{
			case ZOMBIE -> {return new EnemyZombie(x, y, id, size);}
			case BAT -> {return new EnemyBat(x, y, id, size);}
			case KNIGHT -> {return new EnemyKnight(x, y, id, size);}
			case WOLF -> {return new EnemyWolf(x, y, id, size);}
			default -> throw new IllegalStateException("Unexpected value: " + type);
		}
	}
}
