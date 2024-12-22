package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

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
		} else
		{
			throw new RuntimeException("Could not load image: " + name);
		}

		return img;
	}
}
