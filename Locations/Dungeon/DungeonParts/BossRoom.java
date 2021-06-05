package Locations.Dungeon.DungeonParts;

import Creatures.AggressiveNPC.Boss.Boss;
import Creatures.GodCreature;
import Creatures.Player;
import Locations.Map;
import Things.Doors.DoorToUpperLevelLocation;
import Things.Dungeon.DungeonStoneRoad;
import Things.Dungeon.DungeonWall;

public class BossRoom extends PartDungeon implements DungeonPart {

    public BossRoom(Player player){
        super(player);
    }

    @Override
    public GodCreature[][] getWay() {
        Boss boss = new Boss().getRandomBoss();
        boss.setLvl((int)(player.getLvl()*1.7 + Math.random()*30 + 15));
        boss.countStatsAfterBorn();
        boss.setHp(boss.getStats().getStrength()*5*boss.getLvl());
        lower = new GodCreature[][]{
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DoorToUpperLevelLocation().setKey(boss.getKey()).setOut(new Map(player, 100, 100).setPlayer(player)), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()}
        };
        upper[10][9] = boss;
        return lower;
    }
}
