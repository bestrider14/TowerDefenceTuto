package utils;

import main.objects.PathPoint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadSave
{
	public static BufferedImage getSpriteAtlas(String name)
	{
		BufferedImage img = null;
		InputStream is = LoadSave.class.getClassLoader().getResourceAsStream(name);

		if (is != null)
		{
			try
			{
				img = ImageIO.read(is);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			throw new RuntimeException("Could not load image: " + name);
		}

		return img;
	}

	public static void createFile()
	{
		File file = new File("res/test.txt");

		try
		{
			file.createNewFile();
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	public static void writeToFile(File file, int[] idArray, PathPoint startPoint, PathPoint endPoint)
	{
		try
		{
			PrintWriter writer = new PrintWriter(file);

			for (int i : idArray)
				writer.println(i);

			writer.println(startPoint.toString());
			writer.println(endPoint.toString());

			writer.close();
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}

	private static ArrayList<Integer> readLevelTilesFromFile(File file)
	{
		ArrayList<Integer> list = new ArrayList<>();

		try
		{
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine())
			{
				try
				{
					list.add(Integer.parseInt(scanner.nextLine()));
				} catch (NumberFormatException ignored)
				{
				}
			}

			scanner.close();
		} catch (FileNotFoundException e)
		{
			throw new RuntimeException(e);
		}

		return list;
	}

	public static int[][] getLevelData(String fileName)
	{
		File level = new File("res/" + fileName + ".txt");

		if (!level.exists())
		{
			System.out.println("File not found: " + fileName);
			return null;
		}

		ArrayList<Integer> levelData = readLevelTilesFromFile(level);
		return Misc.ArrayListToInt2D(levelData, 20, 20);
	}

	public static void createLevel(String name, int[] idArray, PathPoint startPoint, PathPoint endPoint)
	{
		File newLevel = new File("res/" + name + ".txt");

		if (newLevel.exists())
		{
			System.out.println("Level: " + name + " already exists");
			return;
		}

		try
		{
			newLevel.createNewFile();
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}

		writeToFile(newLevel, idArray, startPoint, endPoint);
	}

	public static void saveLevel(String name, int[][] levelData, PathPoint start, PathPoint end)
	{
		File levelFile = new File("res/" + name + ".txt");

		if (!levelFile.exists())
		{
			System.out.println("File not found: " + name);
			return;
		}

		writeToFile(levelFile, Misc.int2DToArrayList(levelData), start, end);
		System.out.println("Saved level: " + name);
	}

	public static PathPoint getPathPoint(String name, PathPoint.PathPointType pathPointType) {
		File levelFile = new File("res/" + name + ".txt");

		if (!levelFile.exists()) {
			System.out.println("File not found: " + name);
			return null;
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(levelFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// Parse lines containing PathPoint definitions
				if (line.contains("PathPoint") && line.contains("type=" + pathPointType.name())) {
					PathPoint pathPoint = parsePathPoint(line);
					if (pathPoint != null) {
						return pathPoint; // Return the first matching PathPoint
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null; // Return null if no matching PathPoint was found
	}

	private static PathPoint parsePathPoint(String line) {
		try {
			// Extraire x, y et type à partir de la ligne
			String xPart = line.substring(line.indexOf("x=") + 2, line.indexOf(", y="));
			String yPart = line.substring(line.indexOf("y=") + 2, line.indexOf(", type="));
			String typePart = line.substring(line.indexOf("type=") + 5, line.indexOf("}"));

			int x = (int) Double.parseDouble(xPart.trim());
			int y = (int) Double.parseDouble(yPart.trim());
			PathPoint.PathPointType type = PathPoint.PathPointType.valueOf(typePart.trim());

			// Assume une image par défaut (ou remplace avec un chargement réel d'image si nécessaire)
			BufferedImage sprite = null;

			return new PathPoint(x, y, type, sprite);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
