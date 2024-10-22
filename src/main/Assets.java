package main;

import character.ThoreauNPC;
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
        gp.objs[0].worldX = 8 * gp.tileSize;
        gp.objs[0].worldY = 13 * gp.tileSize;

        gp.objs[1] = new Page();
        gp.objs[1].worldX = 11 * gp.tileSize;
        gp.objs[1].worldY = 33 * gp.tileSize;

        gp.objs[2] = new Page();
        gp.objs[2].worldX = 40 * gp.tileSize;
        gp.objs[2].worldY = 22 * gp.tileSize;

        gp.objs[3] = new Alcohol();
        gp.objs[3].worldX = 27 * gp.tileSize;
        gp.objs[3].worldY = 9 * gp.tileSize;

        gp.objs[4] = new Alcohol();
        gp.objs[4].worldX = 36 * gp.tileSize;
        gp.objs[4].worldY = 13 * gp.tileSize;

        gp.objs[5] = new Alcohol();
        gp.objs[5].worldX = 35 * gp.tileSize;
        gp.objs[5].worldY = 30 * gp.tileSize;

        gp.objs[6] = new Coffee();
        gp.objs[6].worldX = 25 * gp.tileSize;
        gp.objs[6].worldY = 21 * gp.tileSize;

        gp.objs[7] = new Coffee();
        gp.objs[7].worldX = 28 * gp.tileSize;
        gp.objs[7].worldY = 38 * gp.tileSize;

        gp.objs[8] = new Door();
        gp.objs[8].worldX = 38 * gp.tileSize;
        gp.objs[8].worldY = 38 * gp.tileSize;


    }

    public void setNPC() {
        gp.npc = new ThoreauNPC(gp);
        gp.npc.worldX = gp.tileSize*29;
        gp.npc.worldY = gp.tileSize*14;
    }
}
