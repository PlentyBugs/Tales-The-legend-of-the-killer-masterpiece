package disease;

import support.Property;
import support.GeneralProperty;
import support.PropertyProvider;

import java.util.ArrayList;
import java.util.List;

public class Disease implements Comparable<Disease>, PropertyProvider {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.add(GeneralProperty.DISEASE);
    }

    protected String name;
    protected int danger;
    protected Disease evolution;

    @Override
    public int compareTo(Disease o) {
        if(o != null)
            return 0;
        return -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDanger() {
        return danger;
    }

    public void evolve(){}
}
