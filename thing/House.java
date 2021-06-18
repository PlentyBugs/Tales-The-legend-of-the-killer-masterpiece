package thing;

import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class House extends Thing {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(GeneralProperty.HOUSE);
    }
    public House(){
        name = "Дом";
        color = new Color(170, 15, 4);
        isStep = false;
    }

    public House(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
    @Override
    public House getClearCopy() {
        return new House();
    }
}
