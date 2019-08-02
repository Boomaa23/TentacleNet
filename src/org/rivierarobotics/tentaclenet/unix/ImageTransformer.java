package org.rivierarobotics.tentaclenet.unix;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.WebcamImageTransformer;

public class ImageTransformer implements WebcamImageTransformer {

	@Override
	public BufferedImage transform(BufferedImage image) {
		BufferedImage imageFrame = null;
		try {
			imageFrame = ImageIO.read(ImageTransformer.class.getResourceAsStream("overlay.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int w = image.getWidth();
		int h = image.getHeight();
		
		BufferedImage modified = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = modified.createGraphics();
		g2.drawImage(image, null, 0, 0);
		g2.drawImage(imageFrame, null, 0, 0);
		g2.dispose();
		modified.flush();
		
		return modified;
	}

}
