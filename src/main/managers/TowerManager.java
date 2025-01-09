package main.managers;

import main.scenes.Playing;
import main.towers.Tower;
import main.towers.TowerFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.Map;

public class TowerManager
{
	private final Playing playing;
	private final TowerFactory towerFactory;
	private final Map<Tower.TowerType, BufferedImage> sprites = new HashMap<>();
	private final List<Tower> towers = new ArrayList<>();

	public TowerManager(Playing playing)
	{
		this.playing = playing;
		towerFactory = new TowerFactory();
		loadTowerSprite();
	}

	private void loadTowerSprite()
	{
		BufferedImage[] towersSprites;
		towersSprites = SpriteManager.getInstance().getSprites(14, 15, 16);

		sprites.put(Tower.TowerType.CANNON, towersSprites[0]);
		sprites.put(Tower.TowerType.ARCHER, towersSprites[1]);
		sprites.put(Tower.TowerType.WIZZARD, towersSprites[2]);
	}

	public void addTower(Tower tower)
	{
		this.towers.add(tower);
	}

	public void removeTower(Tower tower)
	{
		this.towers.remove(tower);
	}

	public void createTower(int x, int y, Tower.TowerType towerType)
	{
		this.towers.add(towerFactory.createTower(x,y, towers.size(), towerType));
	}

	public void update()
	{

	}

	public void draw(Graphics2D g)
	{
		for (Tower tower : towers)
			g.drawImage(sprites.get(tower.getType()), (int) tower.getX(), (int) tower.getY(), null);
	}

	public List<Tower> getTowers()
	{
		return towers;
	}

	public BufferedImage getSprite(Tower.TowerType towerType)
	{
		return sprites.get(towerType);
	}
}
