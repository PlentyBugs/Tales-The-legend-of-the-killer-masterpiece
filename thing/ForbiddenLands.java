package thing;

import support.GeneralProperty;
import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ForbiddenLands extends Thing {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(GeneralProperty.WALL);
    }

    private static final ForbiddenLands instance = new ForbiddenLands();

    private ForbiddenLands(){
        name = "";
        color = new Color(224, 163, 74);
        isStep = false;
    }

    public static ForbiddenLands getInstance() {
        return instance;
    }

    public boolean isBounded() {
        return false;
    }
}
