package main.scenes;

import main.Game;
import main.enemies.Enemy;
import main.managers.EnemyManager;
import main.managers.TileManager;
import main.managers.TowerManager;
import main.objects.PathPoint;
import main.objects.Tile;
import main.scenes.ui.bar.ActionBar;
import main.towers.Tower;
import utils.LoadSave;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Playing extends GameScene implements SceneMethods
{
	private int[][] level;
	PathPoint startPoint;
	PathPoint endPoint;
	private final ActionBar actionBar;
	private final EnemyManager enemyManager;
	private final TowerManager towerManager;
	private int mouseX, mouseY;
	private Tower.TowerType selectedTowerType;
	private boolean drawSelection;

	public Playing(Game game)
	{
		super(game);
		enemyManager = new EnemyManager(this);
		towerManager = new TowerManager(this);
		loadDefaulLevel();
		actionBar = new ActionBar(0, 640, 640, 100, this);

		int x = startPoint.getX();
		int y = startPoint.getY();
		enemyManager.addEnemy(x, y, Enemy.EnemyType.ZOMBIE);
		enemyManager.addEnemy(x, y, Enemy.EnemyType.WOLF);
	}

	private void loadDefaulLevel()
	{
		level = LoadSave.getLevelData("new_level");
		startPoint = LoadSave.getPathPoint("new_level", PathPoint.PathPointType.START);
		endPoint = LoadSave.getPathPoint("new_level", PathPoint.PathPointType.END);
	}

	@Override
	public void render(Graphics2D g)
	{
		drawLevel(g);
		actionBar.draw(g);
		enemyManager.draw(g);
		towerManager.draw(g);
		drawTowerCursor(g);
	}

	private void drawTowerCursor(Graphics2D g)
	{
		if (selectedTowerType != null && drawSelection)
			g.drawImage(towerManager.getSprite(selectedTowerType), mouseX, mouseY, null);
	}

	private void updateTick()
	{
		for (Tile tile : game.getTileManager().tiles.values())
			tile.updateTick();
	}

	private void drawLevel(Graphics2D g)
	{
		for (int y = 0; y < level.length; y++)
			for (int x = 0; x < level[y].length; x++)
				g.drawImage(getSprite(level[y][x]), x * SPRITE_SIZE, y * SPRITE_SIZE, null);
	}

	private BufferedImage getSprite(int id)
	{
		return game.getTileManager().getTile(id).getSprite();
	}

	public void reloadLevel(int[][] level)
	{
		this.level = level;
	}

	@Override
	public void mouseClicked(int x, int y)
	{
		if (y >= 640)
			actionBar.mouseClicked(x, y);
		else {
			if (selectedTowerType != null && isTileBuildable(mouseX, mouseY)) {
				towerManager.createTower(mouseX, mouseY, selectedTowerType);
				actionBar.unToggle(selectedTowerType);
				selectedTowerType = null;
			}
		}
	}

	private boolean isTileBuildable(int x, int y)
	{
		return isTileGrass(x, y) && isTileAvailable(x, y);
	}

	private boolean isTileAvailable(int x, int y)
	{
		for (Tower tower : towerManager.getTowers())
			if (tower.getX() == x && tower.getY() == y)
				return false;
		return true;
	}

	private boolean isTileGrass(int x, int y)
	{
		return Objects.equals(getGame().getTileManager().getTile(level[y / SPRITE_SIZE][x / SPRITE_SIZE]).getName(), "Grass");
	}

	@Override
	public void mouseMoved(int x, int y)
	{
		if (y >= 640) {
			actionBar.mouseMoved(x, y);
			drawSelection = false;
		}
		else {
			mouseX = (x / SPRITE_SIZE) * SPRITE_SIZE;
			mouseY = (y / SPRITE_SIZE) * SPRITE_SIZE;
			drawSelection = true;
		}
	}

	@Override
	public void mousePressed(int x, int y)
	{
		if (y >= 640)
			actionBar.mousePressed(x, y);
	}

	@Override
	public void mouseReleased(int x, int y)
	{
		actionBar.mouseReleased(x, y);
	}

	@Override
	public void mouseDragged(int x, int y)
	{
	}

	@Override
	public void keyPressed(int key, int modifiers)
	{
		actionBar.keyPressed(key);
	}

	public TileManager.TileCategory getTileType(Point2D.Double position)
	{
		int cordX = (int) Math.floor(position.x / SPRITE_SIZE);
		int cordY = (int) Math.floor(position.y / SPRITE_SIZE);

		if (cordX < 0 || cordX >= level.length)
			return null;
		if (cordY < 0 || cordY >= level[cordX].length)
			return null;

		int id = level[cordY][cordX];
		return game.getTileManager().getTile(id).getCategory();
	}

	public void update()
	{
		updateTick();
		enemyManager.update();
		towerManager.update();
	}

	public PathPoint getEndPoint()
	{
		return endPoint;
	}

	public PathPoint getStartPoint()
	{
		return startPoint;
	}

	public TowerManager getTowerManager()
	{
		return towerManager;
	}

	public void setSelectedTower(Tower.TowerType selectedTowerType)
	{
		this.selectedTowerType = selectedTowerType;
	}
}
