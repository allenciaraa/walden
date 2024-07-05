package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Alcohol extends GameObject {

    public Alcohol() {
        name = "Alcohol";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/alcohol.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
