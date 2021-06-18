package item.blacksmith.resource;

import item.Item;
import support.Property;
import support.ResourceProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Resource extends Item {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Item.propertyList);
        propertyList.add(ResourceProperty.RESOURCE);
    }

    protected int maxTemperature;
    protected int temperature = 0;
    protected boolean hotTreatment;
    protected Color color;

    public Resource(){
        name = "Resource";
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
