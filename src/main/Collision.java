package main;

import character.Entity;

public class Collision {
    GamePanel gp;

    public Collision(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        // check perimeter for collisions with the character
        int charLeftWorldX = entity.worldX + entity.solidArea.x;
        int charRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int charTopWorldY = entity.worldY + entity.solidArea.y;
        int charBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int charLeftCol = charLeftWorldX/gp.tileSize;
        int charRightCol = charRightWorldX/gp.tileSize;
        int charTopRow = charTopWorldY/gp.tileSize;
        int charBottomRow = charBottomWorldY/gp.tileSize;

        int tile1, tile2;
        switch (entity.direction) {
            case "up":
                charTopRow = (charTopWorldY - entity.speed)/gp.tileSize;
                tile1 = gp.tm.mapTileNum[charLeftCol][charTopRow];
                tile2 = gp.tm.mapTileNum[charRightCol][charTopRow];
                break;
            case "down":
                charBottomRow = (charBottomWorldY + entity.speed)/gp.tileSize;
                tile1 = gp.tm.mapTileNum[charLeftCol][charBottomRow];
                tile2 = gp.tm.mapTileNum[charRightCol][charBottomRow];
                break;
            case "left":
                charLeftCol = (charLeftWorldX - entity.speed)/gp.tileSize;
                tile1 = gp.tm.mapTileNum[charLeftCol][charTopRow];
                tile2 = gp.tm.mapTileNum[charLeftCol][charBottomRow];
                break;
            case "right":
                charRightCol = (charRightWorldX - entity.speed)/gp.tileSize;
                tile1 = gp.tm.mapTileNum[charRightCol][charTopRow];
                tile2 = gp.tm.mapTileNum[charRightCol][charBottomRow];
                break;
            default:
                tile1 = -1;
                tile2 = -1;
        }

        if (gp.tm.tile[tile1].collision || gp.tm.tile[tile2].collision) {
            entity.collisionOn = true;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int idx = 999;

        for (int i = 0; i < gp.objs.length; i++) {

            if (gp.objs[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.objs[i].solidArea.x = gp.objs[i].worldX + gp.objs[i].solidArea.x;
                gp.objs[i].solidArea.y = gp.objs[i].worldY + gp.objs[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.objs[i].solidArea)) {
                            if (gp.objs[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                idx = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.objs[i].solidArea)) {
                            if (gp.objs[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                idx = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.objs[i].solidArea)) {
                            if (gp.objs[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                idx = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.objs[i].solidArea)) {
                            if (gp.objs[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                idx = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.objs[i].solidArea.x = gp.objs[i].solidAreaDefaultX;
                gp.objs[i].solidArea.y = gp.objs[i].solidAreaDefaultY;
            }

        }
        return idx;
    }
}
