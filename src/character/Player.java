package character;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Player extends Entity {
    KeyHandler kh;

    public final int screenX;
    public final int screenY;

    String name = "vonnegut";
    public int hasPages = 0;
    public int activeAlcohol = 0;
    int alcoholCtr = 0;
    public int activeCoffee = 0;
    int coffeeCtr = 0;
    int swayDir = 0;
    int swayCtr = 15;

    public Player(GamePanel gp, KeyHandler kh) {
        super(gp);

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
        worldX = gp.tileSize * 28;
        worldY = gp.tileSize * 13;
        speed = 4;
        direction = "down";
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public void setPlayerImages() {
        try {
            if (name.equals("hemingway")) {
                front = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/hemingway/front.png")));
                up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/hemingway/up1.png")));
                up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/hemingway/up2.png")));
                down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/hemingway/down1.png")));
                down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/hemingway/down2.png")));
                left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/hemingway/left1.png")));
                left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/hemingway/left2.png")));
                right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/hemingway/right1.png")));
                right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/hemingway/right2.png")));
            } else if (name.equals("plath")) {
                front = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/plath/front.png")));
                up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/plath/up1.png")));
                up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/plath/up2.png")));
                down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/plath/down1.png")));
                down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/plath/down2.png")));
                left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/plath/left1.png")));
                left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/plath/left2.png")));
                right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/plath/right1.png")));
                right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/plath/right2.png")));
            } else {
                front = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/vonnegut/front.png")));
                up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/vonnegut/up1.png")));
                up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/vonnegut/up2.png")));
                down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/vonnegut/down1.png")));
                down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/vonnegut/down2.png")));
                left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/vonnegut/left1.png")));
                left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/vonnegut/left2.png")));
                right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/vonnegut/right1.png")));
                right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/vonnegut/right2.png")));
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

            // check for NPC collision
            boolean npcClash = gp.cc.checkNPC(this, gp.npc);
            if (npcClash) { interactNPC(); }

            if (!collisionOn) {
                if (activeAlcohol > 0) {
                    if (swayCtr >= 30) {
                        Random random = new Random();
                        swayDir = random.nextInt(2);
                        swayCtr = 0;
                    }
                }

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        if (activeAlcohol > 0) {
                            if (swayDir == 0) {
                                worldX++;
                            } else {
                                worldX--;
                            }
                            swayCtr++;
                        }
                        break;
                    case "down":
                        worldY += speed;
                        if (activeAlcohol > 0) {
                            if (swayDir == 0) {
                                worldX++;
                            } else {
                                worldX--;
                            }
                            swayCtr++;
                        }
                        break;
                    case "left":
                        worldX -= speed;
                        if (activeAlcohol > 0) {
                            if (swayDir == 0) {
                                worldY++;
                            } else {
                                worldY--;
                            }
                            swayCtr++;
                        }
                        break;
                    case "right":
                        worldX += speed;
                        if (activeAlcohol > 0) {
                            if (swayDir == 0) {
                                worldY++;
                            } else {
                                worldY--;
                            }
                            swayCtr++;
                        }
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

        if (activeAlcohol > 0) {
            if (alcoholCtr < 600) {
                alcoholCtr++;
            } else {
                activeAlcohol--;
                speed += 2;
                alcoholCtr = 0;
                setMusic();
            }
        }

        if (activeCoffee > 0) {
            if (coffeeCtr < 600) {
                coffeeCtr++;
            } else {
                activeCoffee--;
                speed -= 2;
                coffeeCtr = 0;
                setMusic();
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
                    activeAlcohol++;
                    speed -= 2;
                    gp.playSoundEffect(4);
                    gp.ui.showMessage("A little drinky drink? Don't mind if I do.");
                    gp.objs[idx] = null;
                    break;
                case "Coffee":
                    gp.playSoundEffect(4);
                    gp.ui.showMessage("Omg starbucks.");
                    activeCoffee++;
                    speed += 2;
                    gp.objs[idx] = null;
                    break;
                case "Door":
                    if (hasPages < 3) {
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

            setMusic();


        }
    }

    // TODO: maybe turn this into a switch statement or something, def at least an if-else block
    public void setMusic() {
        // if no active substances play regular song
        if (activeAlcohol == 0 && activeCoffee == 0) {
            if (gp.currentSong != 0) {
                gp.stopMusic();
                gp.playMusic(0);
            }
        }
        // if 1 drink play slightly drunk music
        if (activeAlcohol == 1 && activeCoffee == 0) {
            if (gp.currentSong != 1) {
                gp.stopMusic();
                gp.playMusic(1);
            }
        }
        // if 2 drinks play very drunk music
        if (activeAlcohol > 1 && activeCoffee == 0) {
            if (gp.currentSong != 1) {
                gp.stopMusic();
                gp.playMusic(1);
            }
        }
        // if 1 coffee play slightly fast music
        if (activeAlcohol == 0 && activeCoffee == 1) {
            if (gp.currentSong != 5) {
                gp.stopMusic();
                gp.playMusic(5);
            }
        }
        // if 2 coffees play very fast music
        if (activeAlcohol == 0 && activeCoffee > 1) {
            if (gp.currentSong != 6) {
                gp.stopMusic();
                gp.playMusic(6);
            }
        }
        // if coffee and alcohol at the same time play cross music
//        if (activeAlcohol > 0 && activeCoffee > 0) {
//            if (gp.currentSong != 6) {
//                gp.stopMusic();
//                gp.playMusic(6);
//            }
//        }
    }

    public void interactNPC() {
        gp.dialogueState = true;
        gp.npc.speak();
    }

}


//getting drunk
    // lasts 10 seconds
    // music changes
    // a little drunk at one drink
    // very drunk at 2 drinks
    // die at three drinks