package Items.BlackSmith.Resource;

import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Crystal extends Resource {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Resource.propertyList);
    }

    public Crystal(){
        hotTreatment = true;
        maxTemperature = 4700;
        color = new Color(111, 218, 255);
    }
}
