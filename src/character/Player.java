package character;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Character {
    GamePanel gp;
    KeyHandler kh;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;

        screenX = (gp.screenWidth/2) - (gp.tileSize/2);
        screenY = (gp.screenHeight/2) - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 32;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setPlayerImages();
        setDefaultValues();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize*14;
        worldY = gp.tileSize*6;
        speed = 4;
        direction = "down";

    }

    public void setPlayerImages() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (kh.upPressed || kh.downPressed || kh.rightPressed || kh.leftPressed) {
            if (kh.upPressed) {
                direction = "up";
            } else if (kh.downPressed) {
                direction = "down";
            } else if (kh.leftPressed) {
                direction = "left";
            } else if (kh.rightPressed) {
                direction = "right";
            }

            // check for tile collision
            collisionOn = false;
            gp.cc.checkTile(this);

            // check for object collision
            int objIdx = gp.cc.checkObject(this, true);
            pickUpObject(objIdx);

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
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

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

    public void pickUpObject(int idx) {
        if (idx != 999) {
            String objName = gp.objs[idx].name;
            switch (objName) {
                case "Page":
                    // play sound effect
                    // increase page count
                    // set object to null
                    // show a message in ui
                    break;
                case "Alcohol":
                    // play sound effect
                    // change music
                    // slow down speed
                    // set object to null
                    // show a message in ui
                    break;
                case "Coffee":
                    // play sound effect
                    // change music
                    // set object to null
                    // show a message in ui
                    break;
            }
        }
    }
}
