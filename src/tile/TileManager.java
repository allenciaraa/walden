package tile;

import main.GamePanel;
import main.ImageScalar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[70];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        setTileImages();
        loadMap("/maps/map_world01.csv");
    }

    public void setTileImages() {
        setup(1, "1_forest_grass_btm_rt.png", false);
        setup(2, "2_forest_grass_btm.png", false);
        setup(3, "3_forest_grass_btm_left.png", false);
        setup(4, "4_forest_grass_right.png", false);
        setup(5, "5_grass.png", false);
        setup(6, "6_forest_grass_left.png", false);
        setup(7, "7_forest_grass_top_right.png", false);
        setup(8, "8_forest_grass_top.png", false);
        setup(9, "9_forest_grass_top_left.png", false);
        setup(10, "10_grass_sand_btm_right.png", false);
        setup(11, "11_grass_sand_btm.png", false);
        setup(12, "12_grass_sand_btm_left.png", false);
        setup(13, "13_grass_sand_right.png", false);
        setup(14, "14_sand.png", false);
        setup(15, "15_grass_sand_left.png", false);
        setup(16, "16_grass_sand_top_right.png", false);
        setup(17, "17_grass_sand_top.png", false);
        setup(18, "18_grass_sand_top_left.png", false);
        setup(19, "19_trees_top.png", true);
        setup(20, "20_trees_btm.png", true);
        setup(21, "21_tree_single.png", true);
        setup(22, "22_grass_flowers.png", false);
        setup(23, "23_forest.png", false);
        setup(24, "24_grass_forest_btm_right.png", false);
        setup(25, "25_grass_forest_btm_left.png", false);
        setup(26, "26_grass_forest_top_right.png", false);
        setup(27, "27_grass_forest_top_left.png", false);
        setup(28, "28_sand_grass_btm_right.png", false);
        setup(29, "29_sand_grass_btm_left.png", false);
        setup(30, "30_sand_grass_top_right.png", false);
        setup(31, "31_sand_grass_top_left.png", false);
        setup(32, "32_forest_water_btm_right.png", true);
        setup(33, "33_forest_water_btm.png", true);
        setup(34, "34_forest_water_btm_left.png", true);
        setup(35, "35_forest_water_right.png", true);
        setup(36, "36_water.png", true);
        setup(37, "37_forest_water_left.png", true);
        setup(38, "38_forest_water_top_left.png", true);
        setup(39, "39_forest_water_top.png", true);
        setup(40, "40_forest_water_top_left.png", true);
        setup(41, "41_water_forest_btm_right.PNG", true);
        setup(42, "42_water_forest_btm_left.PNG", true);
        setup(43, "43_water_forest_top_right.PNG", true);
        setup(44, "44_water_forest_top_left.PNG", true);
        setup(45, "45_house.png", true);
        setup(46, "46_house.png", true);
        setup(47, "47_house.png", true);
        setup(48, "48_house.png", true);
        setup(49, "49_house.png", false);
        setup(50, "50_house.png", true);
        setup(51, "51_house.png", true);
        setup(52, "52_house.png", true);
        setup(53, "53_house.png", true);
        setup(54, "54_house.png", true);
        setup(55, "55_house.png", true);
        setup(56, "56_house.png", true);
        setup(57, "57_house.png", true);
        setup(58, "58_house.png", true);
        setup(59, "59_house.png", true);
        setup(60, "60_house.png", true);
        setup(61, "61_house.png", true);
        setup(62, "62_house.png", true);
        setup(63, "63_house.png", true);
        setup(64, "64_house.png", true);
        setup(65, "65_house.png", true);
        setup(66, "66_house.png", true);
        setup(67, "67_house.png", true);
        setup(68, "68_house.png", true);
        setup(69, "69_house.png", true);
    }

    public void setup(int idx, String name, boolean collision) {
        ImageScalar scale = new ImageScalar();
        try {
            tile[idx] = new Tile();
            tile[idx].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + name)));
            tile[idx].image = scale.scaleImage(tile[idx].image, gp.tileSize, gp.tileSize);
            tile[idx].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapLink) {
        try {
            InputStream is = getClass().getResourceAsStream(mapLink);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] nums = line.split(",");
                    int num = Integer.parseInt(nums[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            int tileNum = mapTileNum[col][row];

            int worldX = col * gp.tileSize;
            int worldY = row * gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            col++;

            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }
}
