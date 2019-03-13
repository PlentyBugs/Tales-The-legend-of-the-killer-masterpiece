package Locations.Dungeon.DungeonParts;

import Creatures.GodCreature;
import Creatures.Player;
import Things.Dungeon.EntranceSide;

public class PartDungeon implements DungeonPart{

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
}
