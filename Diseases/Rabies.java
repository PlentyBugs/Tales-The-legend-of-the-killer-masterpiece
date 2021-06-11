package Diseases;

import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Rabies extends Disease  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Disease.propertyList);
        propertyList.add(Property.RABIES);
    }

    public Rabies(){
        name = "Бешенство";
        danger = Danger.DEADLY;
    }

    @Override
    public int compareTo(Disease o) {
        if(o != null)
            return 0;
        return -1;
    }
}
