package Things;

import support.Property;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Tree extends Thing  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(Property.TREE);
    }
    public Tree(){
        name = "Дерево";
        color = new Color(0,255,100);
        isStep = false;
    }

    @Override
    public Tree getClearCopy() {
        return new Tree();
    }
}
