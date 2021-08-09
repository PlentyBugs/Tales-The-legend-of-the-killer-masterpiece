package location.dungeon.DungeonParts;

import creature.GodCreature;
import creature.Player;
import thing.dungeon.EntranceSide;

public class PartDungeon implements DungeonPart {

    protected GodCreature[][] lower;
    protected GodCreature[][] upper;

    protected Player player;

    public PartDungeon(){}

    public PartDungeon(Player player){
        this.player = player;
        lower = new GodCreature[21][21];
        upper = new GodCreature[21][21];
    }

    public GodCreature[][] getRandomPart(){
        DungeonPart dungeonPart = () -> {
            GodCreature[][] godCreature = new DungeonPartCenter(player).getWay();
            int chance = (int)(Math.random()*6);
            if(chance == 0){
                godCreature = new DungeonPartDeadEnd(player).getWay();
            } else if(chance == 1){
                godCreature = new DungeonPartAngle(player).getWay();
            } else if(chance == 2){
                godCreature = new DungeonPartClear(player).getWay();
            } else if(chance == 3){
                godCreature = new DungeonPartCorridor(player).getWay();
            } else if(chance == 4){
                godCreature = new DungeonPartThreeWay(player).getWay();
            }
            return godCreature;
        };
        return dungeonPart.getWay();
    }

    protected EntranceSide getCounterSide(EntranceSide entranceSide){
        if(entranceSide == EntranceSide.BOTTOM){
            return EntranceSide.TOP;
        }
        if(entranceSide == EntranceSide.LEFT){
            return EntranceSide.RIGHT;
        }
        if(entranceSide == EntranceSide.TOP){
            return EntranceSide.BOTTOM;
        }
        return EntranceSide.LEFT;
    }


    @Override
    public GodCreature[][] getWay() {
        return new GodCreature[0][];
    }

    public Player getPlayer() {
        return player;
    }

    public GodCreature[][] getLower() {
        return lower;
    }

    public GodCreature[][] getUpper() {
        return upper;
    }

    public static GodCreature[][] rotate(int countRotationsToNinetyDegr, GodCreature[][] oldMap){
        if(oldMap.length != 0 & oldMap[0].length != 0){
            GodCreature[][] newMap = new GodCreature[oldMap[0].length][oldMap.length];
            for(int i = 0; i < oldMap.length; i++)
                for (int j = 0; j < oldMap[i].length; j++)
                    newMap[i][j] = oldMap[oldMap[i].length - 1 - j][i];

            if(countRotationsToNinetyDegr < 1){
                return oldMap;
            } else {
                return rotate(countRotationsToNinetyDegr - 1, newMap);
            }
        }
        return oldMap;
    }
}
