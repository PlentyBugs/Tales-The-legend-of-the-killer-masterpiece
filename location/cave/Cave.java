package location.cave;

import creature.GodCreature;
import item.blacksmith.resource.*;
import location.GenerationHelper;
import location.Map;
import thing.GreatWallNullerField;
import thing.Ore;
import thing.Stone;

public class Cave extends Map implements GenerationHelper {
    private int[][] mapKeys;
    private int playerSafeX;
    private int playerSafeY;

    public Cave(){
        mapHeight = 1;
        mapWidth = 1;
        mapKeys = new int[][]{{1}};
        boolean isEnded = false;
        while(!isEnded) {
            Check:
            for (int i = 0; i < mapKeys.length; i++) {
                for(int j = 0; j < mapKeys[i].length; j++){
                    if(i*j > 10){
                        isEnded = true;
                    }
                    if(mapKeys[i][j] == 0){
                        mapKeys[i][j] = (int)(Math.random()*7);
                    } else if(mapKeys[i][j] != 0){
                        if(i-1 < 0){
                            addToUp();
                            break Check;
                        } else if(mapKeys[i-1][j] == 0){
                            mapKeys[i-1][j] = new int[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}[(int)(Math.random()*12)];
                            break Check;
                        }
                        if(j-1 < 0){
                            addToLeft();
                            break Check;
                        } else if(mapKeys[i][j-1] == 0){
                            mapKeys[i][j-1] = new int[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, (int)(Math.random()*7 + 1)}[(int)(Math.random()*12)];
                            break Check;
                        }
                        if(i+1 >= mapKeys.length){
                            addToDown();
                            break Check;
                        } else if(mapKeys[i+1][j] == 0){
                            mapKeys[i+1][j] = new int[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}[(int)(Math.random()*12)];
                            break Check;
                        }
                        if(j+1 >= mapKeys[i].length){
                            addToRight();
                            break Check;
                        } else if(mapKeys[i][j+1] == 0){
                            mapKeys[i][j+1] = new int[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, (int)(Math.random()*7 + 1)}[(int)(Math.random()*12)];
                            break Check;
                        }
                    }
                    if(i == mapKeys.length-1 && j == mapKeys[i].length-1){
                    isEnded = true;
                    }
                }
            }
        }

        mapLowerObjects = new GodCreature[mapKeys.length][mapKeys[0].length];
        for (int i = 0; i < mapKeys.length; i++) {
            for (int j = 0; j < mapKeys[i].length; j++) {
                int chance = (int)(Math.random()*100);

                if(chance < 75)
                    mapKeys[i][j] = 1;

                if(mapKeys[i][j] == 1){
                    mapLowerObjects[i][j] = new Stone().setX(j).setY(i);
                    playerSafeX = j;
                    playerSafeY = i;
                }
                if(mapKeys[i][j] == 2)
                    mapLowerObjects[i][j] = new Ore(new Adamantine()).setX(j).setY(i);
                if(mapKeys[i][j] == 3)
                    mapLowerObjects[i][j] = new Ore(new Crystal()).setX(j).setY(i);
                if(mapKeys[i][j] == 4)
                    mapLowerObjects[i][j] = new Ore(new Mythril()).setX(j).setY(i);
                if(mapKeys[i][j] == 5)
                    mapLowerObjects[i][j] = new Ore(new Iron()).setX(j).setY(i);
                if(mapKeys[i][j] == 6)
                    mapLowerObjects[i][j] = new Ore(new Copper()).setX(j).setY(i);
                if(mapLowerObjects[i][j] == null)
                    mapLowerObjects[i][j] = new GreatWallNullerField().setX(j).setY(i);
            }
        }
    }

    public GodCreature[][] getCave(){
        return mapLowerObjects;
    }

    public int getPlayerSafeX() {
        return playerSafeX;
    }

    public int getPlayerSafeY() {
        return playerSafeY;
    }

    @Override
    public int getMapWidth() {
        return mapWidth;
    }

    @Override
    public int getMapHeight() {
        return mapHeight;
    }

    @Override
    public void incrementMapWidth() {
        mapWidth++;
    }

    @Override
    public void incrementMapHeight() {
        mapHeight++;
    }

    @Override
    public int[][] getMapKeys() {
        return mapKeys;
    }

    @Override
    public void setMapKeys(int[][] mapKeys) {
        this.mapKeys = mapKeys;
    }
}
