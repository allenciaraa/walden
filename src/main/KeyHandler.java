package main;

import character.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
                gp.ui.menuNum--;
                if (gp.ui.menuNum < 0) {
                    gp.ui.menuNum = 2;
                }
            }
            if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
                gp.ui.menuNum++;
                if (gp.ui.menuNum > 2) {
                    gp.ui.menuNum = 0;
                }
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                if (gp.ui.menuNum == 0) {
                    gp.gameState = gp.charSelectState;
                }
                if (gp.ui.menuNum == 1) {
                    // TODO: implement tutorial
                }
                if (gp.ui.menuNum == 2) {
                    System.exit(0);
                }
            }
        }

        if (gp.gameState == gp.charSelectState) {
            if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_LEFT) {
                gp.ui.writerNum--;
                if (gp.ui.writerNum < 0) {
                    gp.ui.writerNum = 2;
                }
            }
            if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_RIGHT) {
                gp.ui.writerNum++;
                if (gp.ui.writerNum > 2) {
                    gp.ui.writerNum = 0;
                }
            }

            if (keyCode == KeyEvent.VK_SPACE) {

                if (gp.ui.writerNum == 0) {
                    gp.player.setPlayerName("hemingway");
                }
                if (gp.ui.writerNum == 1) {
                    gp.player.setPlayerName("plath");
                }
                if (gp.ui.writerNum == 2) {
                    gp.player.setPlayerName("vonnegut");
                }
                gp.gameState = gp.playState;
            }
        }

        if (gp.gameState == gp.playState) {
            if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (keyCode == KeyEvent.VK_P) {
                System.out.println("PAUSE TEST TEST");
                gp.gameState = gp.pauseState;
            }
        }

        if (gp.gameState == gp.pauseState) {
            if (keyCode == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W  || keyCode == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (keyCode == KeyEvent.VK_A  || keyCode == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (keyCode == KeyEvent.VK_S  || keyCode == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (keyCode == KeyEvent.VK_D  || keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

    }
}
