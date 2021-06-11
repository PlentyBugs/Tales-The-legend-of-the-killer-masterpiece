package Things.Dungeon;

import Things.Thing;
import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DungeonWall extends Thing {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(Property.WALL);
    }


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
