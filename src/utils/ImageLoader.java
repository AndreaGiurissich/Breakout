package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageLoader {

	
	
	BufferedImage img;
	public BufferedImage loadImg(String fileName) {
		//InputStream is = ImageLoader.class.getResourceAsStream("resource/" + fileName);
		// lo slash serve a dire a java che voglio l'immagine in un altra cartella,
		// non nella cartella della classe da cui chiamo
		try {
			img = ImageIO.read(getClass().getResource(fileName));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// TRY/catch is like a stronger if used for loading
		} 

		return img;
	}
}
