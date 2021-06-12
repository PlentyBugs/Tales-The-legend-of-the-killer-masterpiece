package Items.BlackSmith.Resource;

import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Steel extends Resource {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Resource.propertyList);
    }

    public Steel(){
        hotTreatment = true;
        maxTemperature = 1400;
    }
}
