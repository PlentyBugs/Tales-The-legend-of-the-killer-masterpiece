package Things.Doors;

import support.Property;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class CaveDoor extends Door {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Door.propertyList);
        propertyList.add(Property.CAVE_DOOR);
    }

    public CaveDoor(){
        this(0,0);
    }

    public CaveDoor(int x, int y){
        this.x = x;
        this.y = y;
        isLocked = false;
        name = "Вход в Пещеру";
        color = new Color(100, 100, 97);
        isStep = true;
    }
}