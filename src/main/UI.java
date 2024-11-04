package main;

import Helper.ScriptReader;
import object.Page;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font pixelFont;
    int menuNum = 0;
    int writerNum = 0;
    BufferedImage pageImg;
    public boolean msgOn = false;
    public String msg = "";
    int msgCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public String currentCutSceneText = "You've actually done it";
    int csCt = 0;
    int manIdx = 0;
    int manY = 0;


    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        Page page = new Page();
        pageImg = page.image;
    }

    public void showMessage(String text) {
        msg = text;
        msgOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(pixelFont);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.charSelectState) {
            drawCharacterSelectScreen();
        }
        if (gp.gameState == gp.playState) {
            drawUI();
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        if (gp.dialogueState) {
            drawDialogueScreen();
            gp.player.speed = 0;
            gp.npc.speed = 0;
        }
    }

    public void drawTitleScreen() {

        g2.setColor(new Color(96, 122, 66));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // set up title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "WALDEN";
        int x = getXForCenteredText(text);
        int y = gp.tileSize*3;
        // title shadow
        g2.setColor(new Color(54, 61, 39));
        g2.drawString(text, x+5, y+5);
        // draw title
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // draw tree icon
        x = gp.screenWidth/2 - gp.tileSize;
        y += gp.tileSize/2;
        g2.drawImage(gp.tm.tile[21].image, x, y, gp.tileSize*2, gp.tileSize*2, null);

        // setup menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.setColor(Color.BLACK);
        text = "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if (menuNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (menuNum == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawCharacterSelectScreen() {
        g2.setColor(new Color(96, 122, 66));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // header setup
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 72F));
        String text = "Choose Your Writer";
        int x = getXForCenteredText(text);
        int y = gp.tileSize*2;
        // draw header shadow
        g2.setColor(new Color(54, 61, 39));
        g2.drawString(text, x+5, y+5);
        // draw header
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        BufferedImage hemingway = null;
        BufferedImage plath = null;
        BufferedImage vonnegut = null;

        try {
            hemingway = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/hemingway/front.png")));
            vonnegut = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/vonnegut/front.png")));
            plath = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/plath/front.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        int textY;
        int picY;

        String name = "Plath";
        x = gp.tileSize + gp.tileSize/2;
        picY = y + gp.tileSize*2 + 5;
        textY = picY + gp.tileSize*2 + (gp.tileSize/2);
        g2.drawImage(plath, x, picY, gp.tileSize*2, gp.tileSize*2, null);
        x += gp.tileSize/2 + 5;
        g2.drawString(name, x, textY);
        if (writerNum == 0) {
            g2.drawString(">", x-gp.tileSize/2, textY);
        }

        name = "Hemingway";
        x = gp.screenWidth/2 - (gp.tileSize) - 8;
        g2.drawImage(hemingway, x - 8, picY, gp.tileSize*2, gp.tileSize*2, null);
        x -= 10;
        g2.drawString(name, x, textY);
        if (writerNum == 1) {
            g2.drawString(">", x-gp.tileSize/2, textY);
        }

        name = "Vonnegut";
        x += gp.tileSize*3 + gp.tileSize/2;
        g2.drawImage(vonnegut, x, picY, gp.tileSize*2, gp.tileSize*2, null);
        x += 8;
        g2.drawString(name, x, textY);
        if (writerNum == 2) {
            g2.drawString(">", x-gp.tileSize/2, textY);
        }


    }

    public void drawCutScene() {
        gp.player.speed = 0;
        if (csCt < 240) {
            g2.setColor(new Color(96, 122, 66));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            BufferedImage ghost = null;
            BufferedImage playerAvatar = null;

            try {
                ghost = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ghost_thoreau/sprite_0.png")));
                if (gp.player.name.equals("vonnegut")) {
                    playerAvatar = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/vonnegut/front.png")));
                } else if (gp.player.name.equals("plath")) {
                    playerAvatar = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/plath/front.png")));
                } else {
                    playerAvatar = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/hemingway/front.png")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            int x = gp.tileSize / 2;
            int y = gp.tileSize / 2;
            drawSubWindow(x, y, gp.tileSize * 7 + (gp.tileSize / 2), gp.tileSize * 3);

            // DRAW CUT SCENE TEXT;
            x += gp.tileSize / 2;
            y += gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

            g2.drawString(currentCutSceneText, x, y);

//        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            if (csCt >= 10 && csCt <= 40) {
                if (csCt % 10 == 0) {
                    for (int i = 0; i < csCt / 10; i++) {
                        currentCutSceneText += ".";
                    }
                }
            }

            g2.drawString(currentCutSceneText, x, y);

            if (csCt > 60) {
                String text = "You found my missing masterpiece.";
                y += gp.tileSize / 2;
                g2.drawString(text, x, y);
            }

            if (csCt > 120) {
                String text = "Wanna read it?";
                y += gp.tileSize / 2;
                g2.drawString(text, x, y);
            }

            if (csCt > 180) {
                x = gp.screenWidth / 2 + (gp.tileSize);
                drawSubWindow(x, y, gp.screenWidth / 3, gp.tileSize * 2);
                String text = "Do I have to?";
                g2.drawString(text, x + gp.tileSize / 2, y + gp.tileSize);
            }

            x = gp.tileSize * 4;
            y = gp.tileSize * 4;
            g2.drawImage(ghost, x, y, gp.tileSize * 3, gp.tileSize * 3, null);

            x += gp.tileSize * 4;
            g2.drawImage(playerAvatar, x, y, gp.tileSize * 3, gp.tileSize * 3, null);

            csCt++;
        } else {
            drawManuscript();
        }

    }

    public void drawManuscript() {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26F));
        g2.setColor(new Color(255, 255, 255));

        int y = manY;

        for (int i = 0; i < 10; i++) {
            String text = gp.sr.lines[manIdx + i];

            if (text != null) {
                g2.drawString(text, getXForCenteredText(text), y);
            } else {
                g2.drawString(" ", gp.screenWidth/2, y - gp.tileSize/2);
            }

            y += gp.tileSize;
        }

        if (csCt % 5 == 0) {
            manY--;
        }

        if (manY < -60) {
            manY = 0;
            manIdx++;
        }

    }


    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public void drawUI() {
        if (gameFinished) {
            drawCutScene();
        } else {
            int x = gp.tileSize/2;
            int y = gp.tileSize/2;
            g2.drawImage(pageImg, x, y, gp.tileSize, gp.tileSize, null);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            g2.setColor(Color.WHITE);
            String text = "x " + gp.player.hasPages;
            x += gp.tileSize + 5;
            y += gp.tileSize - 15;
            g2.drawString(text, x, y);


            if (msgOn) {
                g2.setColor(Color.BLACK);
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F)); // takes default font, makes it smaller
                g2.drawString(msg, gp.tileSize / 2, gp.tileSize * 7);
                msgCounter++;
            }

            // make message disappear after 2 seconds
            if (msgCounter > 120) {
                msgCounter = 0;
                msgOn = false;
            }


        }

    }

    public void drawDialogueScreen() {

        // Dialogue Window
        int x = gp.tileSize;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize*3;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));

        y += gp.tileSize/2;

        for (String line : currentDialogue.split("\n")) {
            int l = (int) g2.getFontMetrics().getStringBounds(line, g2).getWidth();
            x = gp.tileSize + (width / 2) - (l / 2);
            y += gp.tileSize / 2;
            g2.drawString(line, x, y);
        }


    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 220);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 35, 35);
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.screenWidth / 2) - (length / 2);
    }
}
