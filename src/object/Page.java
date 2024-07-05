package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Page extends GameObject {

    public Page() {
        name = "Page";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/page.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
