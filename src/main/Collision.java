package main;

import character.Character;

public class Collision {
    GamePanel gp;

    public Collision(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Character character) {
        // check perimeter for collisions with the character
        int charLeftWorldX = character.worldX + character.solidArea.x;
        int charRightWorldX = character.worldX + character.solidArea.x + character.solidArea.width;
        int charTopWorldY = character.worldY + character.solidArea.y;
        int charBottomWorldY = character.worldY + character.solidArea.y + character.solidArea.height;

        int charLeftCol = charLeftWorldX/gp.tileSize;
        int charRightCol = charRightWorldX/gp.tileSize;
        int charTopRow = charTopWorldY/gp.tileSize;
        int charBottomRow = charBottomWorldY/gp.tileSize;

        int tile1, tile2;
        switch (character.direction) {
            case "up":
                charTopRow = (charTopWorldY - character.speed)/gp.tileSize;
                tile1 = gp.tm.mapTileNum[charLeftCol][charTopRow];
                tile2 = gp.tm.mapTileNum[charRightCol][charTopRow];
                break;
            case "down":
                charBottomRow = (charBottomWorldY + character.speed)/gp.tileSize;
                tile1 = gp.tm.mapTileNum[charLeftCol][charBottomRow];
                tile2 = gp.tm.mapTileNum[charRightCol][charBottomRow];
                break;
            case "left":
                charLeftCol = (charLeftWorldX - character.speed)/gp.tileSize;
                tile1 = gp.tm.mapTileNum[charLeftCol][charTopRow];
                tile2 = gp.tm.mapTileNum[charLeftCol][charBottomRow];
                break;
            case "right":
                charRightCol = (charRightWorldX - character.speed)/gp.tileSize;
                tile1 = gp.tm.mapTileNum[charRightCol][charTopRow];
                tile2 = gp.tm.mapTileNum[charRightCol][charBottomRow];
                break;
            default:
                tile1 = -1;
                tile2 = -1;
        }

        if (gp.tm.tile[tile1].collision || gp.tm.tile[tile2].collision) {
            character.collisionOn = true;
        }
    }

    public int checkObject(Character character, boolean player) {
        int idx = 999;

        for (int i = 0; i < gp.objs.length; i++) {

            if (gp.objs[i] != null) {
                character.solidArea.x = character.worldX + character.solidArea.x;
                character.solidArea.y = character.worldY + character.solidArea.y;

                gp.objs[i].solidArea.x = gp.objs[i].worldX + gp.objs[i].solidArea.x;
                gp.objs[i].solidArea.y = gp.objs[i].worldY + gp.objs[i].solidArea.y;

                switch (character.direction) {
                    case "up":
                        character.solidArea.y -= character.speed;
                        if (character.solidArea.intersects(gp.objs[i].solidArea)) {
                            character.collisionOn = true;
                        }
                        if (player) {
                            idx = i;
                        }
                        break;
                    case "down":
                        character.solidArea.y += character.speed;
                        if (character.solidArea.intersects(gp.objs[i].solidArea)) {
                            character.collisionOn = true;
                        }
                        if (player) {
                            idx = i;
                        }
                        break;
                    case "left":
                        character.solidArea.x -= character.speed;
                        if (character.solidArea.intersects(gp.objs[i].solidArea)) {
                            character.collisionOn = true;
                        }
                        if (player) {
                            idx = i;
                        }
                        break;
                    case "right":
                        character.solidArea.x += character.speed;
                        if (character.solidArea.intersects(gp.objs[i].solidArea)) {
                            character.collisionOn = true;
                        }
                        if (player) {
                            idx = i;
                        }
                        break;
                }
                character.solidArea.x = character.solidAreaDefaultX;
                character.solidArea.y = character.solidAreaDefaultY;
                gp.objs[i].solidArea.x = gp.objs[i].solidAreaDefaultX;
                gp.objs[i].solidArea.y = gp.objs[i].solidAreaDefaultY;
            }

        }
        return idx;
    }
}
