package org.rivierarobotics.tentaclenet.unix;

import java.awt.Graphics2D;
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
		
		/*AffineTransform at = new AffineTransform();
		at.concatenate(AffineTransform.getScaleInstance(1, -1));
		at.concatenate(AffineTransform.getTranslateInstance(0, -imageFrame.getHeight()));
		Graphics2D g = imageFrame.createGraphics();
		g.transform(at);
		g.drawImage(imageFrame, 0, 0, null);
		g.dispose();*/
		
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
