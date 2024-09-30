package character;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class ThoreauNPC extends Entity {
    public ThoreauNPC(GamePanel gp) {
        super(gp);

        direction = "left";
        speed = 2;

        setThoreauImage();
        setDialogue();
    }

    public void setThoreauImage() {
        try {
                front = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ghost_thoreau/sprite_0.png")));
                up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ghost_thoreau/sprite_0.png")));
                up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ghost_thoreau/sprite_1.png")));
                down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ghost_thoreau/sprite_0.png")));
                down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ghost_thoreau/sprite_1.png")));
                left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ghost_thoreau/sprite_0.png")));
                left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ghost_thoreau/sprite_1.png")));
                right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ghost_thoreau/sprite_0.png")));
                right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ghost_thoreau/sprite_1.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAction() {
        actCtr++;

        if (actCtr >= 120) {
            Random rand = new Random();
            int i = rand.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }
            actCtr = 0;
        }
    }

    public void setDialogue() {
        dialogues[0] = "ohmygodohmygodohmygod";
        dialogues[1] = "you HAVE to help me";
        dialogues[2] = "it's me, HDT.\ni've come back from the dead to \nfind my missing manuscript";
        dialogues[3] = "i know i left it around here \nsomewhere...";
        dialogues[4] = "please bring all THREE pages to my \ncabin, like, asap!";
        dialogues[5] = "thanks bestie. <3";
    }

    public void speak() {
        if (dialogues[dialogueIdx] == null) {
            dialogueIdx = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIdx];
        dialogueIdx++;
    }


}
