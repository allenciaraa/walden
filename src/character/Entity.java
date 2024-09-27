package character;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Entity {
    public GamePanel gp;

    public int worldX, worldY;
    public int speed;

    public BufferedImage front, back, up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int stepCounter = 0; /// what loop are we on before changing image version
    public int spriteVersion = 1; // which version of the image (1 or 2)

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public int actCtr = 0;


    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {

    }

    public void update() {
        setAction();
        collisionOn = false;
        gp.cc.checkTile(this);
        gp.cc.checkObject(this, false);
        gp.cc.checkPlayer(this);

        if (!collisionOn) {

            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        stepCounter++;
        if (stepCounter > 10) {
            if (spriteVersion == 1) {
                spriteVersion = 2;
            } else if (spriteVersion == 2) {
                spriteVersion = 1;
            }
            stepCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {



            switch (direction) {
                case "up":
                    if (spriteVersion == 1) {
                        image = up1;
                    }
                    if (spriteVersion == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteVersion == 1) {
                        image = down1;
                    }
                    if (spriteVersion == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteVersion == 1) {
                        image = left1;
                    }
                    if (spriteVersion == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteVersion == 1) {
                        image = right1;
                    }
                    if (spriteVersion == 2) {
                        image = right2;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
