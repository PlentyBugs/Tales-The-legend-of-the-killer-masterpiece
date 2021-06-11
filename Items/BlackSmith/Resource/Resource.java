package Items.BlackSmith.Resource;

import Items.Item;
import support.Property;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Resource extends Item {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Item.propertyList);
        propertyList.add(Property.RESOURCE);
    }

    protected int maxTemperature;
    protected int temperature = 0;
    protected boolean hotTreatment;
    protected Color color;

    public Resource(){
        name = getClass().getSimpleName();
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public boolean getTreatment(){
        return hotTreatment;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int compareTo(Resource o) {
        if(o.getClass() != getClass()){
            return -1;
        }
        if(maxTemperature == o.getMaxTemperature() && hotTreatment == o.getTreatment()){
            return 0;
        }
        if(maxTemperature > o.getMaxTemperature())
            return 1;
        else
            return -1;
    }

    public Color getColor() {
        return color;
    }
}
