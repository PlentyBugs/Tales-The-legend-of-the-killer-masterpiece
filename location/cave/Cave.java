package location.cave;

import item.blacksmith.resource.*;
import location.Cell;
import location.GenerationHelper;
import location.Map;
import thing.GreatWall;
import thing.Ore;
import thing.Stone;
import thing.Thing;

public class Cave extends Map implements GenerationHelper {
    private int[][] mapKeys;
    private int playerSafeX;
    private int playerSafeY;

    public Cave() {
        locationName = "Cave";
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

        cells = new Cell[mapKeys.length][mapKeys[0].length];
        for (int i = 0; i < mapKeys.length; i++) {
            for (int j = 0; j < mapKeys[i].length; j++) {
                cells[i][j] = new Cell();
                int chance = (int)(Math.random()*100);

                if(chance < 75)
                    mapKeys[i][j] = 1;

                if(mapKeys[i][j] == 1){
                    cells[i][j].setLowerObject(new Stone().setX(j).setY(i));
                    playerSafeX = j;
                    playerSafeY = i;
                }
                if(mapKeys[i][j] == 2)
                    cells[i][j].setLowerObject(new Ore(new Adamantine()).setX(j).setY(i));
                if(mapKeys[i][j] == 3)
                    cells[i][j].setLowerObject(new Ore(new Crystal()).setX(j).setY(i));
                if(mapKeys[i][j] == 4)
                    cells[i][j].setLowerObject(new Ore(new Mythril()).setX(j).setY(i));
                if(mapKeys[i][j] == 5)
                    cells[i][j].setLowerObject(new Ore(new Iron()).setX(j).setY(i));
                if(mapKeys[i][j] == 6)
                    cells[i][j].setLowerObject(new Ore(new Copper()).setX(j).setY(i));
                if(cells[i][j].getLowerObject() == null)
                    cells[i][j].setLowerObject(GreatWall.getInstance());
            }
        }
        setPlayerX(playerSafeX);
        setPlayerY(playerSafeY);
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

    @Override
    protected Thing getStub() {
        return GreatWall.getInstance();
    }
}
