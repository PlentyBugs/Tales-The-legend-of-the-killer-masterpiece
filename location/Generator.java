package location;

import location.dungeon.Dungeon;

public class Generator implements GenerationHelper {
    private Dungeon dungeon;
    private int mapWidth;
    private int mapHeight;
    private int[][] mapKeys;

    public Generator(){
        mapHeight = 1;
        mapWidth = 1;
        mapKeys = new int[][]{{1, 40}};
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
                    if(i * j > 50){
                        isEnded = true;
                    }
                    if(mapKeys[i][j] != 0){

                        int n = mapKeys[i][j] - (mapKeys[i][j]/10)*10;

                        if(mapKeys[i][j] == 1){
                            if (addToUp(i, j)) break Check;
                            if (addToLeft(i, j)) break Check;
                            if (addToDown(i, j)) break Check;
                            if (addToRight(i, j)) break Check;
                        }

                        if((mapKeys[i][j] / 10) * 10 == 2) {
                            if((n == 1 || n == 2) && addToUp(i, j)) break;
                            if((n == 1 || n == 0) && addToLeft(i, j)) break;
                            if((n == 0 || n == 3) && addToDown(i, j)) break;
                            if((n == 3 || n == 2) && addToRight(i, j)) break;
                        }

                        if((mapKeys[i][j] / 10) * 10 == 3) {
                            if((n == 1 || n == 2 || n == 3) && addToUp(i, j)) break;
                            if((n == 0 || n == 1 || n == 2) && addToLeft(i, j)) break;
                            if((n == 0 || n == 1 || n == 3) && addToDown(i, j)) break;
                            if((n == 0 || n == 2 || n == 3) && addToRight(i, j)) break;
                        }
                        if((mapKeys[i][j] / 10) * 10 == 5) {
                            if(n == 0){
                                if (addToLeft(i, j)) break;
                                if (addToRight(i, j)) break;
                            }
                            if(n == 1){
                                if (addToUp(i, j)) break;
                                if (addToDown(i, j)) break;
                            }
                        }
                    }
                    if(i == mapKeys.length-1 && j == mapKeys[i].length-1){
                        isEnded = true;
                    }
                }
            }
        }
    }

    private boolean addToRight(int i, int j) {
        if(j+1 >= mapKeys[i].length){
            addToRight();
            return true;
        } else if(mapKeys[i][j+1] == 0){
            mapKeys[i][j+1] = new int[]{1,1,1,1,50,20,21,30,31,33,40, 0}[(int)(Math.random()*12)];
            return true;
        }
        return false;
    }

    private boolean addToDown(int i, int j) {
        if(i+1 >= mapKeys.length){
            addToDown();
            return true;
        } else if(mapKeys[i+1][j] == 0){
            mapKeys[i+1][j] = new int[]{1,1,1,1,51,21,22,30,31,32,41, 0}[(int)(Math.random()*12)];
            return true;
        }
        return false;
    }

    private boolean addToLeft(int i, int j) {
        if(j-1 < 0){
            addToLeft();
            return true;
        } else if(mapKeys[i][j-1] == 0){
            mapKeys[i][j-1] = new int[]{1,1,1,1,50,22,23,31,32,33,42, 0}[(int)(Math.random()*12)];
            return true;
        }
        return false;
    }

    private boolean addToUp(int i, int j) {
        if(i-1 < 0){
            addToUp();
            return true;
        } else if(mapKeys[i-1][j] == 0){
            mapKeys[i-1][j] = new int[]{1,1,1,1,51,20,23,30,32,33,43, 0}[(int)(Math.random()*12)];
            return true;
        }
        return false;
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

    public int[][] getMapKeys() {
        return mapKeys;
    }

    @Override
    public void setMapKeys(int[][] mapKeys) {
        this.mapKeys = mapKeys;
    }
}
