package Things;

import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class BrickRoad extends Thing {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(GeneralProperty.ROAD);
    }
    public BrickRoad(){
        name = "";
        color = new Color((int)(Math.random()*5) + 150, (int)(Math.random()*5) + 76, (int)(Math.random()*5) + 65);
        isStep = true;
    }

    public BrickRoad(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
    @Override
    public BrickRoad getClearCopy() {
        return new BrickRoad();
    }

    @Override
    public BrickRoad setX(int x) {
        super.setX(x);
        return this;
    }

    @Override
    public BrickRoad setY(int y) {
        super.setY(y);
        return this;
    }
}
