package main.enemies;

public class EnemyWolf extends Enemy
{
	public EnemyWolf(int x, int y, int id, int size)
	{
		super(x, y, id, size, EnemyType.WOLF);
		this.speed = 0.6;
	}
}
