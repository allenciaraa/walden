package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Page extends GameObject {

    public Page() {
        name = "Page";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/page.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
