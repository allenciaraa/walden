package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageScalar {

    public BufferedImage scaleImage(BufferedImage img, int width, int height) {
        BufferedImage scaled = new BufferedImage(width, height, img.getType());
        Graphics2D g2 = scaled.createGraphics();
        g2.drawImage(img, 0, 0, width, height, null);
        g2.dispose();

        return scaled;
    }
}
