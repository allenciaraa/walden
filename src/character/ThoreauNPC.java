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


}
