package location.dungeon;

import creature.GodCreature;
import creature.Player;
import location.dungeon.DungeonParts.*;
import location.Generator;
import location.Map;
import thing.dungeon.DungeonStoneRoad;

public class Dungeon extends Map {

    private int playerXSafety;
    private int playerYSafety;
    private boolean isBossHere;

    public Dungeon(Player player){
        this.player = player;
        playerXSafety = -1;
        playerYSafety = -1;
        isBossHere = false;
        locationName = "Подземелье";
    }

    public GodCreature[][][] getMap(){
        Generator generator = new Generator();
        generator.setDungeon(this);
        generator.generate();
        int[][] intMap = generator.getMapKeys();
        GodCreature[][] mapLower = new GodCreature[intMap.length*21][intMap[0].length*21];
        GodCreature[][] mapUpper = new GodCreature[intMap.length*21][intMap[0].length*21];
        for(int i = 0; i < intMap.length; i++){
            for(int j = 0; j < intMap[i].length; j++){
                if(!isBossHere && (intMap[i][j] == 40 || intMap[i][j] == 41 || intMap[i][j] == 42 || intMap[i][j] == 43)){
                    isBossHere = true;
                    intMap[i][j] = 60 + intMap[i][j] - (intMap[i][j] / 10) * 10;
                }
                if(intMap[i][j] == 0){
                    DungeonPartClear dungeonPartClear = new DungeonPartClear(player);
                    for(int s = 0; s < 21; s++){
                        for(int k = 0; k < 21; k++){
                            GodCreature godCreature = dungeonPartClear.getWay()[s][k];
                            godCreature.setX(j*21 + k);
                            godCreature.setY(i*21 + s);
                            mapLower[i*21 + s][j*21 + k] = godCreature;
                        }
                    }
                } else if(intMap[i][j] == 1){
                    DungeonPartCenter dungeonPartCenter = new DungeonPartCenter(player);
                    for(int s = 0; s < 21; s++){
                        for(int k = 0; k < 21; k++){
                            GodCreature godCreature = dungeonPartCenter.getWay()[s][k];
                            GodCreature liveCreature = dungeonPartCenter.getUpper()[s][k];
                            setCreaturesInTile(mapLower, mapUpper, i, j, s, k, godCreature, liveCreature);
                        }
                    }
                } else if(intMap[i][j] == 20 || intMap[i][j] == 21 || intMap[i][j] == 22 || intMap[i][j] == 23){
                    DungeonPartAngle dungeonPartAngle = new DungeonPartAngle(player);
                    rotateTile(intMap, mapLower, mapUpper, i, j, dungeonPartAngle.getWay(), dungeonPartAngle.getUpper());
                } else if(intMap[i][j] == 30 || intMap[i][j] == 31 || intMap[i][j] == 32 || intMap[i][j] == 33){
                    DungeonPartThreeWay dungeonPartThreeWay = new DungeonPartThreeWay(player);
                    rotateTile(intMap, mapLower, mapUpper, i, j, dungeonPartThreeWay.getWay(), dungeonPartThreeWay.getUpper());
                } else if(intMap[i][j] == 40 || intMap[i][j] == 41 || intMap[i][j] == 42 || intMap[i][j] == 43){
                    DungeonPartDeadEnd dungeonPartDeadEnd = new DungeonPartDeadEnd(player);
                    rotateTile(intMap, mapLower, mapUpper, i, j, dungeonPartDeadEnd.getWay(), dungeonPartDeadEnd.getUpper());
                } else if(intMap[i][j] == 50 || intMap[i][j] == 51){
                    DungeonPartCorridor dungeonPartCorridor = new DungeonPartCorridor(player);
                    rotateTile(intMap, mapLower, mapUpper, i, j, dungeonPartCorridor.getWay(), dungeonPartCorridor.getUpper());
                } else if(intMap[i][j] == 60 || intMap[i][j] == 61 || intMap[i][j] == 62 || intMap[i][j] == 63){
                    BossRoom dungeonPartDeadEnd = new BossRoom(player);
                    rotateTile(intMap, mapLower, mapUpper, i, j, dungeonPartDeadEnd.getWay(), dungeonPartDeadEnd.getUpper());
                }
            }
        }
        for (int[] mapKey : intMap) {
            for (int i : mapKey) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        return new GodCreature[][][]{mapLower, mapUpper};
    }

    private void rotateTile(
            int[][] intMap,
            GodCreature[][] mapLower,
            GodCreature[][] mapUpper,
            int i, int j,
            GodCreature[][] way,
            GodCreature[][] upper
    ) {
        GodCreature[][] godCreatureArray = PartDungeon.rotate(intMap[i][j] - (intMap[i][j]/10)*10, way);
        GodCreature[][] liveCreatureArray = PartDungeon.rotate(intMap[i][j] - (intMap[i][j]/10)*10, upper);
        for(int s = 0; s < 21; s++){
            for(int k = 0; k < 21; k++){
                GodCreature godCreature = godCreatureArray[s][k];
                GodCreature liveCreature = liveCreatureArray[s][k];
                setCreaturesInTile(mapLower, mapUpper, i, j, s, k, godCreature, liveCreature);
            }
        }
    }

    private void setCreaturesInTile(
            GodCreature[][] mapLower,
            GodCreature[][] mapUpper,
            int i, int j, int s, int k,
            GodCreature godCreature,
            GodCreature liveCreature
    ) {
        if(playerXSafety == -1 && playerYSafety == -1 && godCreature instanceof DungeonStoneRoad){
            playerXSafety = j*21 + k;
            playerYSafety = i*21 + s;
        }
        godCreature.setX(j*21 + k);
        godCreature.setY(i*21 + s);
        if(liveCreature != null){
            liveCreature.setX(j*21 + k);
            liveCreature.setY(i*21 + s);
            mapUpper[i*21 + s][j*21 + k] = liveCreature;
        }
        mapLower[i*21 + s][j*21 + k] = godCreature;
    }

    public int getPlayerXSafety() {
        return playerXSafety;
    }

    public int getPlayerYSafety(){
        return playerYSafety;
    }
}
