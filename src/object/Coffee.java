package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Coffee extends GameObject {

    public Coffee() {
        name = "Coffee";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/coffee.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
