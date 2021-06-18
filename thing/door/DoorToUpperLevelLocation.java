package thing.door;

import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class DoorToUpperLevelLocation extends Door  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Door.propertyList);
        propertyList.add(GeneralProperty.DOOR_TO_UPPER_LEVEL_LOCATION);
    }
    public DoorToUpperLevelLocation(){
        this(0,0);
    }
    public DoorToUpperLevelLocation(int x, int y){
        this.x = x;
        this.y = y;
        isLocked = true;
        name = "Вход в Подземелье";
        color = new Color(100, 24, 22);
        isStep = true;
    }
    @Override
    public DoorToUpperLevelLocation getClearCopy() {
        return new DoorToUpperLevelLocation();
    }
}
