package item.tool;

import item.Item;
import support.ItemProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Tool extends Item {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Item.propertyList);
        propertyList.add(ItemProperty.TOOL);
    }
}
