package thing;

import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Stone extends Thing  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(GeneralProperty.STONE);
    }

    public Stone(){
        name = "";
        color = Color.LIGHT_GRAY;
        isStep = true;
    }
    @Override
    public Stone getClearCopy() {
        return new Stone();
    }
}
