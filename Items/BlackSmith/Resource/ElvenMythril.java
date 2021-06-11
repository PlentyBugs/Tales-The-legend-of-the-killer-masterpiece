package Items.BlackSmith.Resource;

import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ElvenMythril extends Resource {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Resource.propertyList);
    }

    public ElvenMythril(){
        hotTreatment = true;
        maxTemperature = 3500;
        color = new Color(15, 187, 105);
    }
}
