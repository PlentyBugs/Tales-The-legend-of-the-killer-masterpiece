package item.blacksmith.resource;

import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Mythril extends Resource {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Resource.propertyList);
    }

    public Mythril(){
        hotTreatment = true;
        maxTemperature = 2100;
        color = new Color(70, 191, 172);
    }
}
