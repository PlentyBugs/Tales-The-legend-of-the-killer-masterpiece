package location.dungeon;

import creature.GodCreature;
import creature.Player;
import location.Cell;
import location.Generator;
import location.Map;
import location.dungeon.DungeonParts.*;
import thing.Thing;
import thing.dungeon.DungeonStoneRoad;
import thing.dungeon.DungeonWall;

public class Dungeon extends Map {

    public Dungeon(Player player){
        this.player = player;
        boolean isBossHere = false;
        locationName = "Dungeon";
        Generator generator = new Generator();
        generator.setDungeon(this);
        generator.generate();
        int[][] intMap = generator.getMapKeys();
        cells = new Cell[intMap.length * 21][intMap[0].length * 21];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell();
            }
        }
        for(int i = 0; i < intMap.length; i++) {
            for(int j = 0; j < intMap[i].length; j++) {
                if(!isBossHere && (intMap[i][j] == 40 || intMap[i][j] == 41 || intMap[i][j] == 42 || intMap[i][j] == 43)){
                    isBossHere = true;
                    intMap[i][j] = 60 + intMap[i][j] - (intMap[i][j] / 10) * 10;
                }
                if(intMap[i][j] == 0) {
                    DungeonPartClear dungeonPartClear = new DungeonPartClear(player);
                    GodCreature[][] way = dungeonPartClear.getWay();
                    for(int s = 0; s < 21; s++) {
                        GodCreature[] godCreatures = way[s];
                        for(int k = 0; k < 21; k++) {
                            GodCreature godCreature = godCreatures[k];
                            godCreature.setX(j*21 + k);
                            godCreature.setY(i*21 + s);
                            cells[i*21 + s][j*21 + k].setLowerObject(godCreature);
                        }
                    }
                } else if(intMap[i][j] == 1) {
                    DungeonPartCenter dungeonPartCenter = new DungeonPartCenter(player);
                    GodCreature[][] way = dungeonPartCenter.getWay();
                    GodCreature[][] upper = dungeonPartCenter.getUpper();
                    for(int s = 0; s < 21; s++) {
                        GodCreature[] godCreatures1 = upper[s];
                        GodCreature[] godCreatures = way[s];
                        for(int k = 0; k < 21; k++) {
                            GodCreature godCreature = godCreatures[k];
                            GodCreature liveCreature = godCreatures1[k];
                            setCreaturesInTile(cells, i, j, s, k, godCreature, liveCreature);
                        }
                    }
                } else if(intMap[i][j] == 20 || intMap[i][j] == 21 || intMap[i][j] == 22 || intMap[i][j] == 23){
                    DungeonPartAngle dungeonPartAngle = new DungeonPartAngle(player);
                    rotateTile(intMap, cells, i, j, dungeonPartAngle.getWay(), dungeonPartAngle.getUpper());
                } else if(intMap[i][j] == 30 || intMap[i][j] == 31 || intMap[i][j] == 32 || intMap[i][j] == 33){
                    DungeonPartThreeWay dungeonPartThreeWay = new DungeonPartThreeWay(player);
                    rotateTile(intMap, cells, i, j, dungeonPartThreeWay.getWay(), dungeonPartThreeWay.getUpper());
                } else if(intMap[i][j] == 40 || intMap[i][j] == 41 || intMap[i][j] == 42 || intMap[i][j] == 43){
                    DungeonPartDeadEnd dungeonPartDeadEnd = new DungeonPartDeadEnd(player);
                    rotateTile(intMap, cells, i, j, dungeonPartDeadEnd.getWay(), dungeonPartDeadEnd.getUpper());
                } else if(intMap[i][j] == 50 || intMap[i][j] == 51){
                    DungeonPartCorridor dungeonPartCorridor = new DungeonPartCorridor(player);
                    rotateTile(intMap, cells, i, j, dungeonPartCorridor.getWay(), dungeonPartCorridor.getUpper());
                } else if(intMap[i][j] == 60 || intMap[i][j] == 61 || intMap[i][j] == 62 || intMap[i][j] == 63){
                    BossRoom dungeonPartDeadEnd = new BossRoom(player);
                    rotateTile(intMap, cells, i, j, dungeonPartDeadEnd.getWay(), dungeonPartDeadEnd.getUpper());
                }
            }
        }
        setMapHeight(cells.length);
        setMapWidth(cells[0].length);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = cells[i][j];
                if(cell.getUpperObject() == null && cell.getLowerObject() instanceof DungeonStoneRoad) {
                    setPlayerX(j);
                    setPlayerY(i);
                }
            }
        }
        for (int[] mapKey : intMap) {
            for (int i : mapKey) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    private void rotateTile(
            int[][] intMap,
            Cell[][] cells,
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
                setCreaturesInTile(cells, i, j, s, k, godCreature, liveCreature);
            }
        }
    }

    private void setCreaturesInTile(
            Cell[][] cells,
            int i, int j, int s, int k,
            GodCreature godCreature,
            GodCreature liveCreature
    ) {
        int x = j * 21 + k;
        int y = i * 21 + s;
        godCreature.setX(x);
        godCreature.setY(y);
        if(liveCreature != null){
            liveCreature.setX(x);
            liveCreature.setY(y);
            cells[y][x].setUpperObject(liveCreature);
        }
        cells[y][x].setLowerObject(godCreature);
    }

    @Override
    protected Thing getStub() {
        return DungeonWall.getInstance();
    }
}
