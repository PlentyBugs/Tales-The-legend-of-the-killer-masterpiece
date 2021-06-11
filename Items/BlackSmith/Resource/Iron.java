package Items.BlackSmith.Resource;

import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Iron extends Resource {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Resource.propertyList);
    }

    public Iron(){
        hotTreatment = true;
        maxTemperature = 1500;
        color = new Color(116, 116, 116);
    }
}
