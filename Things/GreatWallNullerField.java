package Things;

import Creatures.GodCreature;
import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class GreatWallNullerField extends GodCreature  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(GodCreature.propertyList);
        propertyList.add(GeneralProperty.WALL);
    }
    public GreatWallNullerField(){
        name = "Великая стена";
        color = Color.BLACK;
        isStep = false;
    }
    @Override
    public GreatWallNullerField getClearCopy() {
        return new GreatWallNullerField();
    }
}
