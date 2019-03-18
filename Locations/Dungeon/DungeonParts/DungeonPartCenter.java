package Locations.Dungeon.DungeonParts;

import Abilities.Active.DamageUp;
import Abilities.Active.DecreaseDamage;
import Abilities.Auras.Vision;
import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Abilities.Passive.Professions.Steal;
import Abilities.Passive.TwoOneHandedWeapon;
import Creatures.AggressiveNPC.Bandit;
import Creatures.AggressiveNPC.Knight;
import Creatures.AggressiveNPC.Skeleton;
import Creatures.AggressiveNPC.Zombie;
import Creatures.GodCreature;
import Creatures.Human;
import Creatures.LiveCreature;
import Creatures.PeacefulNPC.Dealer;
import Creatures.Player;
import Items.Alchemy.Potions.HealPotion;
import Items.Alchemy.Potions.PoisonPotion;
import Things.Craft.AlchemyTable;
import Things.DoorToUpperLevelLocation;
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
            lower[8][8] = new DoorToUpperLevelLocation(8, 8);
            Dealer dealer = new Dealer(9,9,"Петуш", 57, 59000);
            dealer.setStarterPhrase("Добрый день, путник.");
            dealer.addConversationShop(1, "Магазин", new Object[] {new HealPotion(), 4000, 300}, new Object[] {new PoisonPotion(), 6000, 300});
            dealer.addConversationShop(2, "Тренировка", new Object[] {new TwoOneHandedWeapon(), 188000, 1}, new Object[] {new CriticalStrike(), 45000, 1}, new Object[] {new Evasion(), 38000, 1}, new Object[] {new Steal(), 99000, 1});
            dealer.getConversationWindow().setPlayer(player);

            dealer.setX(9);
            dealer.setY(9);
            upper[9][9] = dealer;

            Dealer shutep = new Dealer(10,10,"Шутеп", 15623, 8461315);
            shutep.setX(10);
            shutep.setY(10);
            shutep.addConversationShop(2, "Тренировка", new Object[] {new DamageUp(), 99000, 1}, new Object[] {new DecreaseDamage(), 99000, 1}, new Object[] {new Vision(), 99000, 1});
            shutep.getConversationWindow().setPlayer(player);
            upper[10][10] = shutep;

            upper[11][11] = new AlchemyTable();
        }
        return lower;
    }
}
