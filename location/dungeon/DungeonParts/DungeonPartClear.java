package location.dungeon.DungeonParts;

import creature.GodCreature;
import creature.Player;
import thing.dungeon.DungeonWall;

public class DungeonPartClear extends PartDungeon implements DungeonPart {


    public DungeonPartClear(Player player){
        super(player);
        lower = new GodCreature[21][21];
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                lower[i][j] = DungeonWall.getInstance();
            }
        }
    }

    public DungeonPartClear(){
        super();
    }

    @Override
    public GodCreature[][] getWay() {
        return lower;
    }
}
