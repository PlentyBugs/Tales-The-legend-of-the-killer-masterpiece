package Things;

import Creatures.GodCreature;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Thing extends GodCreature {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(GodCreature.propertyList);
        propertyList.add(Property.THING);
    }
    protected String name;
    public String getName(){
        return name;
    }
}
