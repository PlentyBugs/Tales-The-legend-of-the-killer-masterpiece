package thing.chest;

import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Corpse extends Chest {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Chest.propertyList);
    }

    public Corpse(){
        this(0,0);
    }
    public Corpse(int x, int y){
        this.x = x;
        this.y = y;
        name = "Труп";
        color = new Color(50,90, 4);
        isStep = true;
    }
    @Override
    public Corpse getClearCopy() {
        return new Corpse();
    }
}
