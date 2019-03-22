package Locations.Dungeon.DungeonParts;

import Creatures.AggressiveNPC.Bandit;
import Creatures.AggressiveNPC.Knight;
import Creatures.AggressiveNPC.Skeleton;
import Creatures.AggressiveNPC.Zombie;
import Creatures.GodCreature;
import Creatures.Human;
import Creatures.LiveCreature;
import Creatures.PeacefulNPC.NPC;
import Creatures.Player;
import Things.Craft.AlchemyTable;
import Things.Dungeon.DungeonStoneRoad;
import Things.Dungeon.DungeonWall;

public class DungeonPartCenter extends PartDungeon implements DungeonPart {

    public DungeonPartCenter(Player player){
        super(player);
    }

    @Override
    public GodCreature[][] getWay() {
        lower = new GodCreature[][]{
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad()},
                {new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad()},
                {new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad()},
                {new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad()},
                {new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
                {new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonStoneRoad(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall(), new DungeonWall()},
        };
        int chancePeacefulRoom = (int) Math.ceil(Math.random() * 2000);
        for(int i = 0; i < 21; i++){
            for(int j = 0; j < 21; j++){
                if(lower[i][j] instanceof DungeonStoneRoad){
                    Human[] randomHumanList = {new Bandit(), new Zombie(), new Skeleton(), new Knight()};
                    int chance = (int) Math.ceil(Math.random() * 2000);

                    if (chance <= 1){
                        LiveCreature randomGodCreature = randomHumanList[(int) (randomHumanList.length * Math.random())];
                        randomGodCreature.setLvl((int)(Math.random()*(player.getLvl()+16)+1) + player.getLvl() - 1);
                        randomGodCreature.countStatsAfterBorn();
                        randomGodCreature.setHp((int)(Math.random()*player.getHp()+70) + 40*player.getLvl() + 70* randomGodCreature.getLvl() + randomGodCreature.getStats().getStrength()*12);

                        randomGodCreature.setMaxHp((int) randomGodCreature.getHp());

                        randomGodCreature.setX(j);
                        randomGodCreature.setY(i);

                        upper[i][j] = randomGodCreature;
                    }
                }
                lower[i][j].setX(j);
                lower[i][j].setY(i);
            }
        }

        if (chancePeacefulRoom < 4){
            upper[9][9] = NPC.dealerPetush;
        }

        if(chancePeacefulRoom < 8){
            upper[10][10] = NPC.dealerShutep;
        }

        if(chancePeacefulRoom < 12){
            upper[11][11] = new AlchemyTable();
        }

        return lower;
    }
}
