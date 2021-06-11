package Things.Dungeon;

import Things.Thing;
import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DungeonStoneRoad extends Thing {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(GeneralProperty.ROAD);
    }

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
