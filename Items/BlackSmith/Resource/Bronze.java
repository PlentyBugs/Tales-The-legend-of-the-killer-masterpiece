package Items.BlackSmith.Resource;

import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bronze extends Resource {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Resource.propertyList);
    }

    public Bronze(){
        hotTreatment = true;
        maxTemperature = 1200;
        color = new Color(127, 14, 0);
    }
}
