package Things.Dungeon;

import Things.Thing;

import java.awt.*;

public class DungeonWall extends Thing {

    public static final DungeonWall instance = new DungeonWall();

    public DungeonWall(){
        name = "Стена";
        color = new Color(103, 101, 100);
        isStep = false;
    }

    public DungeonWall(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    @Override
    public DungeonWall getClearCopy() {
        return DungeonWall.getInstance();
    }

    public static DungeonWall getInstance() {
        return instance;
    }
}
