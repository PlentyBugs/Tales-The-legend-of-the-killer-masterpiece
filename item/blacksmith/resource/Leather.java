package item.blacksmith.resource;

import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Leather extends Resource {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Resource.propertyList);
    }

    public Leather(){
        hotTreatment = true;
        maxTemperature = 0;
    }
}
