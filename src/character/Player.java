package character;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler kh;

    public final int screenX;
    public final int screenY;

    String name = "vonnegut";
    public int hasPages = 0;

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

        setDefaultValues();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize*14;
        worldY = gp.tileSize*6;
        speed = 4;
        direction = "down";
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public void setPlayerImages() {
        try {
            if (name.equals("hemingway")) {
                front = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/front.png"));
                up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/up1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/up2.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/down1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/down2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/left1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/left2.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/right1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/right2.png"));
            } else if (name.equals("plath")) {
                front = ImageIO.read(getClass().getResourceAsStream("/sprites/plath/front.png"));
                up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/plath/up1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/plath/up2.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/plath/down1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/plath/down2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/plath/left1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/plath/left2.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/plath/right1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/plath/right2.png"));
            } else {
                front = ImageIO.read(getClass().getResourceAsStream("/sprites/vonnegut/front.png"));
                up1 = ImageIO.read(getClass().getResourceAsStream("/sprites/vonnegut/up1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/sprites/vonnegut/up2.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/sprites/vonnegut/down1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/sprites/vonnegut/down2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/sprites/vonnegut/left1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/sprites/vonnegut/left2.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/sprites/vonnegut/right1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/sprites/vonnegut/right2.png"));
            }

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
                    hasPages++;
                    gp.objs[idx] = null;
                    gp.playSoundEffect(4);
                    gp.ui.showMessage("I got a page!");
                    break;
                case "Alcohol":
                    if (speed > 4) {
                        speed -= 4;
                    } else {
                        speed = 1;
                    }
                    gp.playSoundEffect(4);
                    gp.ui.showMessage("A little drinky drink? Don't mind if I do.");
                    gp.objs[idx] = null;
                    break;
                case "Coffee":
                    gp.playSoundEffect(4);
                    gp.ui.showMessage("Omg starbucks.");
                    speed += 4;
                    gp.objs[idx] = null;
                    break;
                case "Door":
                    if (hasPages < 1) {
                        gp.playSoundEffect(4);
                        gp.ui.showMessage("You need to complete the manuscript. Get " + (3 - hasPages) + " more!");
                    } else {
                        gp.objs[idx] = null;
                        gp.ui.gameFinished = true;
                        gp.stopMusic();
                        gp.playSoundEffect(3);
                    }
                    break;
            }

            if (speed >= 6) {
                if (gp.currentSong != 2) {
                    gp.stopMusic();
                    gp.playMusic(2);
                }
            } else if (speed <= 2) {
                if (gp.currentSong != 1) {
                    gp.stopMusic();
                    gp.playMusic(1);
                }
            } else {
                if (gp.currentSong != 0) {
                    gp.stopMusic();
                    gp.playMusic(0);
                }
            }
        }
    }

}
