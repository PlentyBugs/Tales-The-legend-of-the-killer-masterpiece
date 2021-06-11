package Items.BlackSmith.Resource;

import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Copper extends Resource {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Resource.propertyList);
    }

    public Copper(){
        hotTreatment = true;
        maxTemperature = 1085;
        color = new Color(184, 33, 0);
    }
}
