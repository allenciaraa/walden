package main;

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
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        if (menuNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "TUTORIAL";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (menuNum == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (menuNum == 2) {
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

        String name = "Hemingway";
        x = gp.tileSize;
        picY = y + gp.tileSize*2 + 5;
        textY = picY + gp.tileSize*2 + (gp.tileSize/2);
        g2.drawImage(hemingway, x, picY, gp.tileSize*2, gp.tileSize*2, null);
        g2.drawString(name, x, textY);
        if (writerNum == 0) {
            g2.drawString(">", x-(gp.tileSize/2), textY);
        }

        name = "Plath";
        x = gp.screenWidth/2 - (gp.tileSize);
        g2.drawImage(plath, x, picY, gp.tileSize*2, gp.tileSize*2, null);
        x += gp.tileSize/2 + 8;
        g2.drawString(name, x, textY);
        if (writerNum == 1) {
            g2.drawString(">", x-(gp.tileSize/2), textY);
        }

        name = "Vonnegut";
        x += gp.tileSize*3;
        g2.drawImage(vonnegut, x, picY, gp.tileSize*2, gp.tileSize*2, null);
        x += 8;
        g2.drawString(name, x, textY);
        if (writerNum == 2) {
            g2.drawString(">", x-(gp.tileSize/2), textY);
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
            g2.setColor(Color.YELLOW);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Congratulations!";
            int x = getXForCenteredText(text);
            int y = gp.tileSize*4;
            g2.drawString(text, x, y);

            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
            text = "You found the missing manuscript!";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
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
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString(currentDialogue, x, y);
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
