package Locations;

import LiveCreatures.*;
import Things.*;

public class Map {

    private int playerVision;
    private GodCreature[][] map;
    protected int mapWidth;
    protected int mapHeight;
    private Player player;

    public Map(Player player, int mapWidth, int mapHeight){
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;

        this.player = player;

        playerVision = player.getVision()*2+1;
        map = new GodCreature[mapHeight][mapWidth];
        for(int i = 0; i < mapHeight; i++){
            for(int j = 0; j < mapWidth; j++){
                Thing[] randomThingList = {new Grass(), new Stone(), new Tree()};
                Human[] randomHumanList = {new Bandit(), new Goblin()};

                int chance = (int) Math.ceil(Math.random() * 100);

                GodCreature randomGodCreature = new GodCreature();
                if (chance >= 1 && chance <= 5){
                    randomGodCreature = randomHumanList[(int) (randomHumanList.length * Math.random())];
                    ((LiveCreature)randomGodCreature).setHp((int)(Math.random()*player.getHp()+10));
                    ((LiveCreature)randomGodCreature).setLvl((int)(Math.random()*(player.getLvl()+5)+1));
                } else {
                    randomGodCreature = randomThingList[(int)(randomThingList.length*Math.random())];
                }
                randomGodCreature.setX(j);
                randomGodCreature.setY(i);

                map[i][j] = randomGodCreature;
            }
        }
        int healBlockY = (int)(Math.random()*(mapHeight-1));
        int healBlockX = (int)(Math.random()*(mapWidth-1));
        map[healBlockY][healBlockX] = new HealBlock(healBlockX, healBlockY);
        int doorToUpperLevelLocationY = (int)(Math.random()*(mapHeight-1));
        int doorToUpperLevelLocationX = (int)(Math.random()*(mapWidth-1));
        map[doorToUpperLevelLocationY][doorToUpperLevelLocationX] = new HealBlock(doorToUpperLevelLocationX, doorToUpperLevelLocationY);
    }

    public GodCreature[][] getMap(int x, int y){
        GodCreature[][] currentMap = new GodCreature[playerVision][playerVision];
        int vision = (playerVision-1)/2;
        for (int i = 0; i < playerVision; i++){
            for (int j = 0; j < playerVision; j++){
                if (i + y-vision >= 0 && j + x-vision >= 0 && i + y-vision < mapHeight && j + x-vision < mapWidth) {
                    currentMap[i][j] = map[i + y-vision][j + x-vision];
                    if (i == j && i == player.getVision()){
                        currentMap[i][j] = player;
                    }
                } else {
                    currentMap[i][j] = new GreatWallNullerField();
                }
            }
        }
        return currentMap;
    }

    public void setElementByCoordinates(int x, int y, GodCreature godCreature){
        map[y][x] = godCreature;
    }

    public int getMapHeight() {
        return mapHeight;
    }
    public int getMapWidth() {
        return mapWidth;
    }
}
