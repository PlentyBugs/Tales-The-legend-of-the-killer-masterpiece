package Things;

import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Grass extends Thing  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(GeneralProperty.GRASS);
    }

    public Grass(){
        name = "";
        color = Color.GREEN;
        isStep = true;
    }

    public Grass(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
    @Override
    public Grass getClearCopy() {
        return new Grass();
    }
}
