package main;

import object.Alcohol;
import object.Coffee;
import object.Door;
import object.Page;

public class Assets {

    GamePanel gp;

    public Assets(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        gp.objs[0] = new Page();
        gp.objs[0].worldX = 6 * gp.tileSize;
        gp.objs[0].worldY = 8 * gp.tileSize;

        gp.objs[1] = new Alcohol();
        gp.objs[1].worldX = 10 * gp.tileSize;
        gp.objs[1].worldY = 8 * gp.tileSize;

        gp.objs[2] = new Coffee();
        gp.objs[2].worldX = 14 * gp.tileSize;
        gp.objs[2].worldY = 8 * gp.tileSize;

        gp.objs[3] = new Door();
        gp.objs[3].worldX = 12 * gp.tileSize;
        gp.objs[3].worldY = 22 * gp.tileSize;


    }
}
