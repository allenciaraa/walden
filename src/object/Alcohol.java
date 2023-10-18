package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Alcohol extends GameObject {

    public Alcohol() {
        name = "Alcohol";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/alcohol.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
