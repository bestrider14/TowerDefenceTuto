package main.enemies;

public class EnemyZombie extends Enemy
{
	public EnemyZombie(int x, int y, int id, int size)
	{
		super(x, y, id, size, EnemyType.ZOMBIE);
		this.speed = 0.5;
		System.out.println("Zombie");
	}
}
