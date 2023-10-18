package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Coffee extends GameObject {

    public Coffee() {
        name = "Coffee";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/coffee.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
