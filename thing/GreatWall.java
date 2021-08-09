package thing;

import support.GeneralProperty;
import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GreatWall extends Thing {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(GeneralProperty.WALL);
    }

    private static final GreatWall instance = new GreatWall();

    private GreatWall(){
        name = "";
        color = Color.BLACK;
        isStep = false;
    }

    @Override
    public GreatWall getClearCopy() {
        return new GreatWall();
    }

    public static GreatWall getInstance() {
        return instance;
    }

    public boolean isBounded() {
        return false;
    }
}
