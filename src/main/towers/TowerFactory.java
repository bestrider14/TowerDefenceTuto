package main.towers;

public class TowerFactory
{

	public Tower createTower(int x, int y, int id, Tower.TowerType towerType)
	{
		switch (towerType) {
			case CANNON -> {return new TowerCannon(x, y, id);}
			case ARCHER -> {return new TowerArcher(x, y, id);}
			case WIZZARD -> {return new TowerWizzard(x, y, id);}
			default -> throw new IllegalStateException("Unexpected value: " + towerType);
		}
	}
}
