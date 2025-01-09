package main.enemies;

public class EnemyBat extends Enemy
{
	public EnemyBat(int x, int y, int id, int size)
	{
		super(x, y, id, size, EnemyType.BAT);
		this.speed = 1;
	}
}
