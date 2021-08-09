package location;

import creature.GodCreature;
import creature.LiveCreature;
import creature.Player;
import creature.aggressive.Bandit;
import creature.aggressive.Ent;
import creature.aggressive.Goblin;
import creature.aggressive.GoblinKing;
import creature.aggressive.animal.Wolf;
import creature.peaceful.npc.NPC;
import item.Key;
import item.alchemy.ingredient.*;
import thing.*;
import thing.alchemy.Berry;
import thing.alchemy.Herb;
import thing.alchemy.Mushroom;
import thing.craft.AlchemyTable;
import thing.craft.Anvil;
import thing.craft.EnchantTable;
import thing.craft.Smelter;
import thing.door.CaveDoor;
import thing.door.DoorToUpperLevelLocation;

import java.io.Serial;
import java.io.Serializable;

public class Map implements Serializable {

    protected int playerVision;
    private int playerX, playerY;
    protected Cell[][] cells;
    protected int mapWidth;
    protected int mapHeight;
    protected Player player;
    protected String locationName;

    @Serial
    private static final long serialVersionUID = 5350390037103737479L;

    public Map(){}

    public Map(Player player, int mapWidth, int mapHeight) {

        locationName = "Лес";

        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;

        this.player = player;

        playerVision = player.getVision()*2+1;
        cells = new Cell[mapHeight][mapWidth];
        for(int i = 0; i < mapHeight; i++){
            for(int j = 0; j < mapWidth; j++){
                cells[i][j] = new Cell();
                Thing[] randomThingList = {new Grass(), new Stone(), new Tree()};

                GodCreature randomGodCreature;
                randomGodCreature = randomThingList[(int)(randomThingList.length*Math.random())];
                if (randomGodCreature instanceof Grass){
                    int chanceHerb = (int) Math.ceil(Math.random() * 100);
                    Ingredient[] herb = {new RedHerb(), new BlueHerb(), new GreenHerb(), new Hop(), new Grapes()};
                    Ingredient[] berry = {new BlueBerry(), new DrunkenBerry(), new GoblinBerry(), new WildBerry()};
                    Ingredient[] mushroom = {new HellMushroom(), new WhiteMushroom()};
                    if(chanceHerb < 2){
                        randomGodCreature = new Herb(herb[(int) (herb.length * Math.random())]);
                    } else if (chanceHerb < 4){
                        randomGodCreature = new Berry(berry[(int) (berry.length * Math.random())]);
                    } else if (chanceHerb < 6){
                        randomGodCreature = new Mushroom(mushroom[(int) (mushroom.length * Math.random())]);
                    }
                }
                randomGodCreature.setX(j);
                randomGodCreature.setY(i);

                cells[i][j].setLowerObject(randomGodCreature);
                setMapHeight(mapHeight);
                setMapWidth(mapWidth);
            }
        }

        for(int i = 0; i < mapHeight; i++){
            for(int j = 0; j < mapWidth; j++) {
                LiveCreature[] randomHumanList = {new Bandit(), new Goblin(), new Ent(), new Wolf()};
                int chance = (int) Math.ceil(Math.random() * 100);

                if (chance <= 5){
                    LiveCreature randomGodCreature = randomHumanList[(int) (randomHumanList.length * Math.random())];
                    randomGodCreature.setLvl((int)(Math.random()*(player.getLvl()+16)+1) + player.getLvl() - 1);
                    randomGodCreature.countStatsAfterBorn();
                    randomGodCreature.setHp((int)(Math.random()*player.getHp()+70) + 40*player.getLvl() + 70* randomGodCreature.getLvl() + randomGodCreature.getStats().getStrength()*12);

                    randomGodCreature.setMaxHp((int) randomGodCreature.getHp());

                    randomGodCreature.setX(j);
                    randomGodCreature.setY(i);

                    cells[i][j].setUpperObject(randomGodCreature);
                } else {
                    cells[i][j].setUpperObject(null);
                }
            }
        }

        for (int i = 0; i < 4; i++){
            int GoblinKingY = (int)(Math.random()*(mapHeight-1));
            int GoblinKingX = (int)(Math.random()*(mapWidth-1));
            GoblinKing goblinKing = new GoblinKing();
            goblinKing.setX(GoblinKingX);
            goblinKing.setY(GoblinKingY);
            goblinKing.setLvl((int)(Math.random()*(player.getLvl()+16)+1) + player.getLvl() - 1);
            goblinKing.countStatsAfterBorn();
            goblinKing.setHp((int)(Math.random()*player.getHp()+70) + 40*player.getLvl() + 70* goblinKing.getLvl() + goblinKing.getStats().getStrength()*12);

            cells[GoblinKingY][GoblinKingX].setUpperObject(goblinKing);
        }

        for (int i = 0; i < 7; i++){
            int healBlockY = (int)(Math.random()*(mapHeight-1));
            int healBlockX = (int)(Math.random()*(mapWidth-1));
            cells[healBlockY][healBlockX].setLowerObject(new HealBlock(healBlockX, healBlockY));
        }
/*
        for(int s = 0; s < 2; s++){
            GoblinCamp goblinCamp = new GoblinCamp();
            if(goblinCamp.getMap() != null && mapHeight >= 100 && mapWidth >= 100){
                GodCreature[][] goblinCampPart = rotate(s+1, goblinCamp.getMap().getMapLowerObjects());
                int randomCoordinate = (int)(Math.random()*(mapWidth-goblinCampPart.length));
                while(!(randomCoordinate < mapWidth)){
                    randomCoordinate = (int)(Math.random()*(mapWidth-goblinCampPart.length));
                }
                for (int i = 0; i < goblinCampPart.length; i++){
                    for (int j = 0; j < goblinCampPart[0].length; j++){
                        goblinCampPart[i][j].setX(j+randomCoordinate);
                        goblinCampPart[i][j].setY(i+randomCoordinate);
                        if(i+randomCoordinate < mapLowerObjects.length && mapLowerObjects[i+randomCoordinate].length > j+randomCoordinate){
                            mapLowerObjects[i+randomCoordinate][j+randomCoordinate] = goblinCampPart[i][j];
                        }
                    }
                }
            }
        }
*/

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                cells[i][j].setUpperObject(null);
                if(!((i == 9 && j == 4) || (j == 9 && i == 4)))
                    if(i == 9 || j == 9  || i == 0 || j == 0)
                        cells[i][j].setLowerObject(new WoodWall().setX(j).setY(i));
                    else
                        cells[i][j].setLowerObject(new BrickRoad().setX(j).setY(i));
                else
                    cells[i][j].setLowerObject(new BrickRoad().setX(j).setY(i));
            }
        }

        for (int i = 0; i < 40; i++){
            int doorToUpperLevelLocationY = (int)(Math.random()*(mapHeight-1));
            int doorToUpperLevelLocationX = (int)(Math.random()*(mapWidth-1));
            cells[doorToUpperLevelLocationY][doorToUpperLevelLocationX].setLowerObject(new CaveDoor(doorToUpperLevelLocationX, doorToUpperLevelLocationY));
        }

        for (int i = 0; i < 12; i++){
            int doorToUpperLevelLocationY = (int)(Math.random()*(mapHeight-1));
            int doorToUpperLevelLocationX = (int)(Math.random()*(mapWidth-1));
            cells[doorToUpperLevelLocationY][doorToUpperLevelLocationX].setLowerObject(new DoorToUpperLevelLocation(doorToUpperLevelLocationX, doorToUpperLevelLocationY)
                    .setKey(new Key()));
        }

        cells[6][3].setUpperObject(NPC.dealerPetush);
        cells[3][5].setUpperObject(NPC.inhabitantDanil);
        cells[3][4].setUpperObject(NPC.dealerShutep);
        cells[6][7].setUpperObject(NPC.blacksmithDroghan);
        cells[4][6].setUpperObject(new AlchemyTable());
        cells[4][4].setUpperObject(new EnchantTable());
        cells[7][8].setUpperObject(new Anvil());
        cells[8][8].setUpperObject(new Smelter());
    }

    public GodCreature[][] getMap(int x, int y) {
        playerVision = player.getVision()*2+1;
        GodCreature[][] currentMap = new GodCreature[playerVision][playerVision];
        int vision = (playerVision-1)/2;
        for (int i = 0; i < playerVision; i++){
            for (int j = 0; j < playerVision; j++){
                if (i + y-vision >= 0 && j + x-vision >= 0 && i + y-vision < mapHeight && j + x-vision < mapWidth) {
                    Cell cell = cells[i + y - vision][j + x - vision];
                    cell.setVisited(true);
                    if(cell.getUpperObject() != null){
                        currentMap[i][j] = cell.getUpperObject();
                    } else {
                        currentMap[i][j] = cell.getLowerObject();
                    }
                    if (i == j && i == player.getVision()){
                        currentMap[i][j] = player;
                    }
                } else {
                    currentMap[i][j] = getStub();
                }
            }
        }
        return currentMap;
    }

    public void setMapUpperObjects(GodCreature[][] mapUpperObjects) {
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                cells[i][j].setUpperObject(mapUpperObjects[i][j]);
            }
        }
    }

    public void setMapLowerObjects(GodCreature[][] mapLowerObjects) {
        setMapHeight(mapLowerObjects.length);
        setMapWidth(mapLowerObjects[0].length);
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                cells[i][j].setLowerObject(mapLowerObjects[i][j]);
            }
        }
    }

    public void setElementByCoordinates(int x, int y, GodCreature godCreature) {
        cells[y][x].setLowerObject(godCreature);
    }

    public void setElementByCoordinatesUpper(int x, int y, GodCreature godCreature) {
        cells[y][x].setUpperObject(godCreature);
    }

    public GodCreature getElementByCoordinates(int x, int y){
        if(y >= 0 && y < mapHeight && x >= 0 && x < mapWidth){
            if(cells[y][x].getUpperObject() != null){
                return cells[y][x].getUpperObject();
            }
            return cells[y][x].getLowerObject();
        }
        return null;
    }

    public int getMapHeight() {
        return mapHeight;
    }
    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public Map setPlayer(Player player) {
        this.player = player;
        this.player.setX(playerX);
        this.player.setY(playerY);
        return this;
    }

    private GodCreature[][] rotate(int countRotationsToNinetyDegr, GodCreature[][] oldMap){
        if(oldMap.length != 0 & oldMap[0].length != 0){
            GodCreature[][] newMap = new GodCreature[oldMap[0].length][oldMap.length];
            for(int i = 0; i < oldMap.length; i++)
                for(int j = 0; j < oldMap[i].length; j++)
                    newMap[j][i] = oldMap[oldMap[i].length - 1 - j][i];
            if(countRotationsToNinetyDegr == 1){
                return newMap;
            } else {
                return rotate(countRotationsToNinetyDegr - 1, newMap);
            }
        }
        return oldMap;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public Cell[][] getCells() {
        return cells;
    }

    protected Thing getStub() {
        return ForbiddenLands.getInstance();
    }
}
