package main;

import object.Page;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80b;
    int menuNum = 0;
    int charNum = 0;
    BufferedImage pageImg;
    public boolean msgOn = false;
    public String msg = "";
    int msgCounter = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80b = new Font("Arial", Font.BOLD, 80);
        Page page = new Page();
        pageImg = page.image;
    }

    public void showMessage(String text) {
        msg = text;
        msgOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

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
    }

    public void drawTitleScreen() {
        g2.setFont(arial_80b);
        g2.setColor(Color.WHITE);
        String text = "WALDEN";
        int x = getXForCenteredText(text);
        int y = gp.tileSize*3;
        g2.drawString(text, x, y);

        g2.setFont(arial_40);
        g2.setColor(Color.CYAN);

        text = "new game";
        x = getXForCenteredText(text);
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        if (menuNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "load game";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (menuNum == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "quit";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (menuNum == 2) {
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawCharacterSelectScreen() {

        BufferedImage hemingway = null;
        BufferedImage plath = null;
        BufferedImage vonnegut = null;

        try {
            hemingway = ImageIO.read(getClass().getResourceAsStream("/sprites/hemingway/front.png"));
            vonnegut = ImageIO.read(getClass().getResourceAsStream("/sprites/vonnegut/front.png"));
            plath = ImageIO.read(getClass().getResourceAsStream("/sprites/plath/front.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        String name = "Hemingway";
        int x = gp.tileSize;
        int y = gp.tileSize * 5;
        g2.drawString(name, x, y);
        if (charNum == 0) {
            g2.drawString(">", x-gp.tileSize/2, y);
        }

        int x2 = x + (gp.tileSize/2);
        int y2 = y - gp.tileSize * 3;
        g2.drawImage(hemingway, x2, y2, gp.tileSize*2, gp.tileSize*2, null);

        name = "Vonnegut";
        x += gp.tileSize*4;
        g2.drawString(name, x, y);
        if (charNum == 1) {
            g2.drawString(">", x-gp.tileSize/2, y);
        }

        x2 = x + (gp.tileSize/2);
        g2.drawImage(vonnegut, x2, y2, gp.tileSize*2, gp.tileSize*2, null);


        name = "Plath";
        x += gp.tileSize*4;
        g2.drawString(name, x, y);
        if (charNum == 2) {
            g2.drawString(">", x-gp.tileSize/2, y);
        }

        g2.drawImage(plath, x, y2, gp.tileSize*2, gp.tileSize*2, null);


        String instructions = "Press SPACE to begin!";
        x = 100;
        y = gp.tileSize*7;
        g2.drawString(instructions, x, y);

    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public void drawUI() {
        if (gameFinished) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int x, y;

            text = "You found the missing manuscript!";
            x = getXForCenteredText(text);
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            g2.setFont(arial_80b);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            x = getXForCenteredText(text);
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);
        } else {

            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(pageImg, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasPages, 97, 77);

            if (msgOn) {
                g2.setFont(g2.getFont().deriveFont(30F)); // takes default font, makes it smaller
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

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (gp.screenWidth / 2) - (length / 2);
    }
}
