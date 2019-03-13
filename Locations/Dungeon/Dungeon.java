package Locations.Dungeon;

import Creatures.GodCreature;
import Creatures.Player;
import Locations.Dungeon.DungeonParts.*;
import Locations.Generator;
import Locations.Map;
import Things.Dungeon.DungeonStoneRoad;

public class Dungeon extends Map {

    private int playerXSafety;
    private int playerYSafety;

    public Dungeon(Player player){
        this.player = player;
        playerXSafety = -1;
        playerYSafety = -1;
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
                if(intMap[i][j] == 0){
                    for(int s = 0; s < 21; s++){
                        for(int k = 0; k < 21; k++){
                            DungeonPartClear dungeonPartClear = new DungeonPartClear(player);
                            GodCreature godCreature = dungeonPartClear.getWay()[s][k];
                            godCreature.setX(j*21 + k);
                            godCreature.setY(i*21 + s);
                            mapLower[i*21 + s][j*21 + k] = godCreature;
                        }
                    }
                } else if(intMap[i][j] == 1){
                    for(int s = 0; s < 21; s++){
                        for(int k = 0; k < 21; k++){
                            DungeonPartCenter dungeonPartCenter = new DungeonPartCenter(player);
                            GodCreature godCreature = dungeonPartCenter.getWay()[s][k];
                            GodCreature liveCreature = dungeonPartCenter.getUpper()[s][k];
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
                    }
                } else if(intMap[i][j] == 2){
                    for(int s = 0; s < 21; s++){
                        for(int k = 0; k < 21; k++){
                            DungeonPartAngle dungeonPartAngle = new DungeonPartAngle(player);
                            GodCreature godCreature = dungeonPartAngle.getWay()[s][k];
                            GodCreature liveCreature = dungeonPartAngle.getUpper()[s][k];
                            if(playerXSafety == -1 && playerYSafety == -1 && godCreature instanceof DungeonStoneRoad){
                                playerXSafety = j*21 + k;
                                playerYSafety = i*21 + s;
                            }
                            godCreature.setX(j*21 + k);
                            godCreature.setY(i*21 + s);
                            liveCreature.setX(j*21 + k);
                            liveCreature.setY(i*21 + s);
                            mapLower[i*21 + s][j*21 + k] = godCreature;
                            mapUpper[i*21 + s][j*21 + k] = liveCreature;
                        }
                    }
                } else if(intMap[i][j] == 3){
                    for(int s = 0; s < 21; s++){
                        for(int k = 0; k < 21; k++){
                            DungeonPartThreeWay dungeonPartThreeWay = new DungeonPartThreeWay(player);
                            GodCreature godCreature = dungeonPartThreeWay.getWay()[s][k];
                            GodCreature liveCreature = dungeonPartThreeWay.getUpper()[s][k];
                            if(playerXSafety == -1 && playerYSafety == -1 && godCreature instanceof DungeonStoneRoad){
                                playerXSafety = j*21 + k;
                                playerYSafety = i*21 + s;
                            }
                            godCreature.setX(j*21 + k);
                            godCreature.setY(i*21 + s);
                            liveCreature.setX(j*21 + k);
                            liveCreature.setY(i*21 + s);
                            mapLower[i*21 + s][j*21 + k] = godCreature;
                            mapUpper[i*21 + s][j*21 + k] = liveCreature;
                        }
                    }
                } else if(intMap[i][j] == 4){
                    for(int s = 0; s < 21; s++){
                        for(int k = 0; k < 21; k++){
                            DungeonPartDeadEnd dungeonPartDeadEnd = new DungeonPartDeadEnd(player);
                            GodCreature godCreature = dungeonPartDeadEnd.getWay()[s][k];
                            GodCreature liveCreature = dungeonPartDeadEnd.getUpper()[s][k];
                            if(playerXSafety == -1 && playerYSafety == -1 && godCreature instanceof DungeonStoneRoad){
                                playerXSafety = j*21 + k;
                                playerYSafety = i*21 + s;
                            }
                            godCreature.setX(j*21 + k);
                            godCreature.setY(i*21 + s);
                            liveCreature.setX(j*21 + k);
                            liveCreature.setY(i*21 + s);
                            mapLower[i*21 + s][j*21 + k] = godCreature;
                            mapUpper[i*21 + s][j*21 + k] = liveCreature;
                        }
                    }
                } else if(intMap[i][j] == 5){
                    for(int s = 0; s < 21; s++){
                        for(int k = 0; k < 21; k++){
                            DungeonPartCorridor dungeonPartCorridor = new DungeonPartCorridor(player);
                            GodCreature godCreature = dungeonPartCorridor.getWay()[s][k];
                            GodCreature liveCreature = dungeonPartCorridor.getUpper()[s][k];
                            if(playerXSafety == -1 && playerYSafety == -1 && godCreature instanceof DungeonStoneRoad){
                                playerXSafety = j*21 + k;
                                playerYSafety = i*21 + s;
                            }
                            godCreature.setX(j*21 + k);
                            godCreature.setY(i*21 + s);
                            liveCreature.setX(j*21 + k);
                            liveCreature.setY(i*21 + s);
                            mapLower[i*21 + s][j*21 + k] = godCreature;
                            mapUpper[i*21 + s][j*21 + k] = liveCreature;
                        }
                    }
                }
            }
        }
        return new GodCreature[][][]{mapLower, mapUpper};
    }

    public int getPlayerXSafety() {
        return playerXSafety;
    }

    public int getPlayerYSafety(){
        return playerYSafety;
    }
}
