package utils;

import java.awt.*;

import java.awt.image.BufferedImage;

public class ImageEdit
{
	public static BufferedImage getRotatedImage(BufferedImage image, int angle)
	{
		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage rotatedImage = new BufferedImage(width, height, image.getType());
		Graphics2D g = rotatedImage.createGraphics();

		g.rotate(Math.toRadians(angle), (double) width / 2, (double) height / 2);
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return rotatedImage;
	}


	public static BufferedImage buildSprite(BufferedImage[] images)
	{
		int[] array = new int[images.length];
		return buildRotatedSprite(images, array);
	}


	public static BufferedImage buildRotatedSprite(BufferedImage[] images, int[] angles)
	{
		if(angles.length != images.length)
			throw new RuntimeException("Angles and images must have the same length");

		int width = images[0].getWidth();
		int height = images[0].getHeight();

		BufferedImage rotatedImage = new BufferedImage(width, height, images[0].getType());
		Graphics2D g = rotatedImage.createGraphics();

		for(int i = 0; i < angles.length; i++)
			g.drawImage(getRotatedImage(images[i], angles[i]), 0, 0, null);

		g.dispose();

		return rotatedImage;
	}

	public static BufferedImage[] buildRotatedAnimatedSprite(BufferedImage[] imagesAnimated, BufferedImage imageFix)
	{
		return buildRotatedAnimatedSprite(imagesAnimated, imageFix, 0);
	}

	public static BufferedImage[] buildRotatedAnimatedSprite(BufferedImage[] imagesAnimated, BufferedImage imageFix, int angles)
	{
		BufferedImage[] imagesArray = new BufferedImage[imagesAnimated.length];

		for(int i = 0; i < imagesAnimated.length; i++)
		{
			BufferedImage[] bufferedImages = new BufferedImage[]{imagesAnimated[i], imageFix};
			int[] bufferedAngles = new int[]{0, angles};
			imagesArray[i] = buildRotatedSprite(bufferedImages, bufferedAngles);
		}

		return imagesArray;
	}
}
