package character;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Character {
    public int worldX, worldY;
    public int speed;

    public BufferedImage front, back, up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int stepCounter = 0; /// what loop are we on before changing image version
    public int spriteVersion = 1; // which version of the image (1 or 2)

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
