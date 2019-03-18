package Locations;

import Creatures.Player;
import Locations.Dungeon.Dungeon;

public class Generator {
    private Dungeon dungeon;
    private Player player;
    private int mapWidth;
    private int mapHeight;
    private int[][] mapKeys;

    public Generator(){
        mapHeight = 1;
        mapWidth = 1;
        mapKeys = new int[][]{{1}};
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void generate(){
        boolean isEnded = false;
        while(!isEnded){
            Check: for(int i = 0; i < mapKeys.length; i++){
                for(int j = 0; j < mapKeys[i].length; j++){
                    if(i*j > 10*5){
                        isEnded = true;
                    }
                    if(mapKeys[i][j] != 0){

                        int n = mapKeys[i][j] - (mapKeys[i][j]/10)*10;

                        if(mapKeys[i][j] == 1){
                            if(i-1 < 0){
                                addToUp();
                                break Check;
                            } else {
                                mapKeys[i-1][j] = new int[]{1,1,1,1,51,20,23,30,32,33,43, 0}[(int)(Math.random()*9)];
                            }
                            if(j-1 < 0){
                                addToLeft();
                                break Check;
                            } else {
                                mapKeys[i][j-1] = new int[]{1,1,1,1,50,22,23,31,32,33,42, 0}[(int)(Math.random()*9)];
                            }
                            if(i+1 >= mapKeys.length){
                                addToDown();
                                break Check;
                            } else {
                                mapKeys[i+1][j] = new int[]{1,1,1,1,51,21,22,30,31,32,41, 0}[(int)(Math.random()*9)];
                            }
                            if(j+1 >= mapKeys[i].length){
                                addToRight();
                                break Check;
                            } else {
                                mapKeys[i][j+1] = new int[]{1,1,1,1,50,20,21,30,31,33,40, 0}[(int)(Math.random()*9)];
                            }
                        }

                        if((mapKeys[i][j]/10)*10 == 2){
                            if(n == 1 || n == 2){
                                if(i-1 < 0){
                                    addToUp();
                                    break Check;
                                } else {
                                    mapKeys[i-1][j] = new int[]{1,1,1,1,51,20,23,30,32,33,43, 0}[(int)(Math.random()*9)];
                                }
                            }
                            if(n == 1 || n == 0){
                                if(j-1 < 0){
                                    addToLeft();
                                    break Check;
                                } else {
                                    mapKeys[i][j-1] = new int[]{1,1,1,1,50,22,23,31,32,33,42, 0}[(int)(Math.random()*9)];
                                }
                            }
                            if(n == 0 || n == 3){
                                if(i+1 >= mapKeys.length){
                                    addToDown();
                                    break Check;
                                } else {
                                    mapKeys[i+1][j] = new int[]{1,1,1,1,51,21,22,30,31,32,41, 0}[(int)(Math.random()*9)];
                                }
                            }
                            if(n == 3 || n == 2){
                                if(j+1 >= mapKeys[i].length){
                                    addToRight();
                                    break Check;
                                } else {
                                    mapKeys[i][j+1] = new int[]{1,1,1,1,50,20,21,30,31,33,40, 0}[(int)(Math.random()*9)];
                                }
                            }
                        }

                        if((mapKeys[i][j]/10)*10 == 3){
                            if(n == 1 || n == 2 || n == 3){
                                if(i-1 < 0){
                                    addToUp();
                                    break Check;
                                } else {
                                    mapKeys[i-1][j] = new int[]{1,1,1,1,51,20,23,30,32,33,43, 0}[(int)(Math.random()*9)];
                                }
                            }
                            if(n == 0 || n == 1 || n == 2){
                                if(j-1 < 0){
                                    addToLeft();
                                    break Check;
                                } else {
                                    mapKeys[i][j-1] = new int[]{1,1,1,1,50,22,23,31,32,33,42, 0}[(int)(Math.random()*9)];
                                }
                            }
                            if(n == 0 || n == 1 || n == 3){
                                if(i+1 >= mapKeys.length){
                                    addToDown();
                                    break Check;
                                } else {
                                    mapKeys[i+1][j] = new int[]{1,1,1,1,51,21,22,30,31,32,41, 0}[(int)(Math.random()*9)];
                                }
                            }
                            if(n == 0 || n == 2 || n == 3){
                                if(j+1 >= mapKeys[i].length){
                                    addToRight();
                                    break Check;
                                } else {
                                    mapKeys[i][j+1] = new int[]{1,1,1,1,50,20,21,30,31,33,40, 0}[(int)(Math.random()*9)];
                                }
                            }
                        }
                        if((mapKeys[i][j]/10)*10 == 5){
                            if(n == 0){
                                if(j-1 < 0){
                                    addToLeft();
                                    break Check;
                                } else {
                                    mapKeys[i][j-1] = new int[]{1,1,1,1,50,22,23,31,32,33,42, 0}[(int)(Math.random()*9)];
                                }
                                if(j+1 >= mapKeys[i].length){
                                    addToRight();
                                    break Check;
                                } else {
                                    mapKeys[i][j+1] = new int[]{1,1,1,1,50,20,21,30,31,33,40, 0}[(int)(Math.random()*9)];
                                }
                            }
                            if(n == 1){
                                if(i-1 < 0){
                                    addToUp();
                                    break Check;
                                } else {
                                    mapKeys[i-1][j] = new int[]{1,1,1,1,51,20,23,30,32,33,43, 0}[(int)(Math.random()*9)];
                                }
                                if(i+1 >= mapKeys.length){
                                    addToDown();
                                    break Check;
                                } else {
                                    mapKeys[i+1][j] = new int[]{1,1,1,1,51,21,22,30,31,32,41, 0}[(int)(Math.random()*9)];
                                }
                            }
                        }
                    }
                    if(i == mapKeys.length-1 && j == mapKeys[i].length-1){
                        isEnded = true;
                    }
                }
            }
        }
        for(int i = 0; i < mapKeys.length; i++){
            for(int j = 0; j < mapKeys[i].length; j++){
                System.out.print(mapKeys[i][j] + " ");
            }
            System.out.println();
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

    public int[][] getMapKeys() {
        return mapKeys;
    }
}
