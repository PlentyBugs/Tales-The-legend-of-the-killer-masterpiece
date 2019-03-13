package Things.Dungeon;

import Things.Thing;

import java.awt.*;

public class DungeonStoneRoad extends Thing {
    public DungeonStoneRoad(){
        name = "";
        int colorInt = (int)(20*Math.random())+160;
        color = new Color(colorInt, colorInt, colorInt);
        isStep = true;
    }

    public DungeonStoneRoad(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
    @Override
    public DungeonStoneRoad getClearCopy() {
        return new DungeonStoneRoad();
    }
}
