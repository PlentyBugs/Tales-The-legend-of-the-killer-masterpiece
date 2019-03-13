package Locations.Dungeon.DungeonParts;

import Creatures.AggressiveNPC.*;
import Creatures.GodCreature;
import Creatures.Human;
import Creatures.LiveCreature;
import Creatures.Player;
import Things.Dungeon.DungeonStoneRoad;
import Things.Dungeon.DungeonWall;

public class DungeonPartDeadEnd extends PartDungeon implements DungeonPart {

    public DungeonPartDeadEnd(Player player){
        super(player);
    }

    @Override
    public GodCreature[][] getWay() {
        lower = new GodCreature[][]{
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()}
        };
        for(int i = 0; i < 21; i++){
            for(int j = 0; j < 21; j++){
                if(lower[i][j] instanceof DungeonStoneRoad){
                    Human[] randomHumanList = {new Bandit(), new Zombie(), new Skeleton(), new Knight()};int chance = (int) Math.ceil(Math.random() * 100);

                    if (chance <= 5){
                        LiveCreature randomGodCreature = randomHumanList[(int) (randomHumanList.length * Math.random())];
                        randomGodCreature.setLvl((int)(Math.random()*(player.getLvl()+16)+1) + player.getLvl() - 1);
                        randomGodCreature.countStatsAfterBorn();
                        randomGodCreature.setHp((int)(Math.random()*player.getHp()+70) + 40*player.getLvl() + 70* randomGodCreature.getLvl() + randomGodCreature.getStats().getStrength()*12);

                        randomGodCreature.setX(j);
                        randomGodCreature.setY(i);

                        upper[i][j] = randomGodCreature;
                    }
                }
                lower[i][j].setX(j);
                lower[i][j].setY(i);
            }
        }
        return lower;
    }
}
