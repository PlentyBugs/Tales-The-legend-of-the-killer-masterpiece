package Locations.Cave;

import Creatures.GodCreature;
import Items.BlackSmith.Resource.*;
import Locations.Map;
import Things.GreatWallNullerField;
import Things.Ore;
import Things.Stone;

public class Cave extends Map {
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

    private void addToUp(){

        mapHeight += 1;

        int[][] oldMap = mapKeys;
        mapKeys = new int[mapHeight][mapWidth];

        for(int j = 0; j < mapWidth; j++){
            mapKeys[0][j] = 0;
        }

        for (int i = 1; i < mapHeight; i++){
            for (int j = 0; j < mapWidth; j++){
                mapKeys[i][j] = oldMap[i-1][j];
            }
        }
    }

    private void addToDown(){

        mapHeight += 1;

        int[][] oldMap = mapKeys;
        mapKeys = new int[mapHeight][mapWidth];

        for(int j = 0; j < mapWidth; j++){
            mapKeys[mapHeight-1][j] = 0;
        }

        for (int i = 0; i < mapHeight-1; i++){
            for (int j = 0; j < mapWidth; j++){
                mapKeys[i][j] = oldMap[i][j];
            }
        }
    }

    private void addToLeft(){

        mapWidth += 1;

        int[][] oldMap = mapKeys;
        mapKeys = new int[mapHeight][mapWidth];

        for(int j = 0; j < mapHeight; j++){
            mapKeys[j][0] = 0;
        }

        for (int i = 0; i < mapHeight; i++){
            for (int j = 1; j < mapWidth; j++){
                mapKeys[i][j] = oldMap[i][j-1];
            }
        }
    }

    private void addToRight(){

        mapWidth += 1;

        int[][] oldMap = mapKeys;

        mapKeys = new int[mapHeight][mapWidth];

        for(int j = 0; j < mapHeight; j++){
            mapKeys[j][mapWidth-1] = 0;
        }

        for (int i = 0; i < mapHeight; i++){
            for (int j = 0; j < mapWidth-1; j++){
                mapKeys[i][j] = oldMap[i][j];
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
}
