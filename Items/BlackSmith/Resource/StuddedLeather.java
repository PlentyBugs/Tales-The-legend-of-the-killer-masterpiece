package Items.BlackSmith.Resource;

import support.Property;
import support.GeneralProperty;

import java.util.ArrayList;
import java.util.List;

public class StuddedLeather extends Resource {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Resource.propertyList);
    }

    public StuddedLeather(){
        hotTreatment = true;
        maxTemperature = 0;
    }
}
