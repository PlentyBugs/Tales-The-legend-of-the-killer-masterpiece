package thing;

import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class WoodWall extends Thing {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(GeneralProperty.WALL);
    }
    public WoodWall(){
        name = "Стена";
        color = new Color((int)(Math.random()*5) + 163, (int)(Math.random()*5) + 95, (int)(Math.random()*5) + 7);
        isStep = false;
    }

    public WoodWall(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
    @Override
    public WoodWall getClearCopy() {
        return new WoodWall();
    }

    @Override
    public WoodWall setX(int x) {
        super.setX(x);
        return this;
    }

    @Override
    public WoodWall setY(int y) {
        super.setY(y);
        return this;
    }
}
