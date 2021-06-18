package item.blacksmith.resource;

import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Adamantine extends Resource {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Resource.propertyList);
    }

    public Adamantine(){
        hotTreatment = true;
        maxTemperature = 2700;
        color = new Color(37, 37, 37);
    }
}
