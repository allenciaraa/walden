package main;

import java.awt.*;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80b;
    int menuNum = 0;
    int charNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80b = new Font("Arial", Font.BOLD, 80);
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
            // TODO: implement play state
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
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        String name = "Hemingway";
        int x = gp.tileSize;
        int y = gp.tileSize*4;
        g2.drawString(name, x, y);

        name = "Vonnegut";
        x += gp.tileSize*4;
        g2.drawString(name, x, y);

        name = "Plath";
        x += gp.tileSize*4;
        g2.drawString(name, x, y);

        name = "Fitzgerald";
        x = gp.tileSize;
        y += gp.tileSize*4;
        g2.drawString(name, x, y);

        name = "Salinger";
        x += gp.tileSize*4;
        g2.drawString(name, x, y);

        name = "Dickinson";
        x += gp.tileSize*3;
        g2.drawString(name, x, y);

    }

    public void drawPauseScreen() {
        // TODO: implement pause screen
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = (gp.screenWidth / 2) - (length / 2);
        return x;
    }
}
