package main;

import character.Entity;
import character.Player;
import object.GameObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    final int originalTileSize = 32;
    final int scalar = 2;
    public int tileSize = originalTileSize * scalar;

    public final int maxScreenCol = 12;
    public final int maxScreenRow = 9;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // General Settings
    int fps = 60;
    Thread gameThread;
    KeyHandler kh = new KeyHandler(this);
    TileManager tm = new TileManager(this);
    Sound music = new Sound();
    public int currentSong;
    Sound se = new Sound();
    public UI ui = new UI(this);
    public Collision cc = new Collision(this);
    public Assets as = new Assets(this);
    public Player player = new Player(this, kh);


    // Game State
    public GameObject[] objs = new GameObject[10];
    public Entity npc;

    public int gameState;
    public final int titleState = 0;
    public final int charSelectState = 1;
    public final int playState = 2;
    public final int pauseState = 3;
    public boolean dialogueState = false;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }

    public void setupGame() {
        as.setObjects();
        as.setNPC();
        playMusic(0); // plays theme song from the time the game is opened
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = (nextDrawTime - System.nanoTime())/1000000; // converts into milliseconds for sleep()
                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();
            npc.update();
        }
        if (gameState == pauseState) {

        }


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState == playState) {
            player.setPlayerImages();


            // map
            tm.draw(g2);

            //player
            player.draw(g2);

            //
//            ui.draw(g2);

            for (GameObject obj : objs) {
                if (obj != null) {
                    obj.draw(g2, this);
                }
            }

            npc.draw(g2);
        }

        ui.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i) {
        currentSong = i;
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        se.setFile(i);
        se.play();
    }
}
