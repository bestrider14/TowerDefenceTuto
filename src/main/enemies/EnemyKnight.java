package main.enemies;


public class EnemyKnight extends Enemy
{
	public EnemyKnight(int x, int y, int id, int size)
	{
		super(x, y, id, size, EnemyType.KNIGHT);
		this.speed = 0.3;
	}
}
