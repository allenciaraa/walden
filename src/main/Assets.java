package main;

import object.Alcohol;
import object.Coffee;
import object.Page;

public class Assets {

    GamePanel gp;

    public Assets(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        gp.objs[0] = new Page();
        gp.objs[0].worldX = 8 * gp.tileSize;
        gp.objs[0].worldY = 8 * gp.tileSize;

//        gp.objs[1] = new Page();
//        gp.objs[1].worldX = 10 * gp.tileSize;
//        gp.objs[1].worldY = 8 * gp.tileSize;

//        gp.objs[2] = new Page();
//        gp.objs[2].worldX = 9 * gp.tileSize;
//        gp.objs[2].worldY = 22 * gp.tileSize;

        gp.objs[3] = new Alcohol();
        gp.objs[3].worldX = 10 * gp.tileSize;
        gp.objs[3].worldY = 8 * gp.tileSize;

//        gp.objs[4] = new Alcohol();
//        gp.objs[4].worldX = 12 * gp.tileSize;
//        gp.objs[4].worldY = 7 * gp.tileSize;

//        gp.objs[5] = new Alcohol();
//        gp.objs[5].worldX = 23 * gp.tileSize;
//        gp.objs[5].worldY = 7 * gp.tileSize;

        gp.objs[6] = new Coffee();
        gp.objs[6].worldX = 12 * gp.tileSize;
        gp.objs[6].worldY = 8 * gp.tileSize;

//        gp.objs[7] = new Coffee();
//        gp.objs[7].worldX = 23 * gp.tileSize;
//        gp.objs[7].worldY = 7 * gp.tileSize;

    }
}
