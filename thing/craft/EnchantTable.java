package thing.craft;

import creature.Player;
import support.GeneralProperty;
import support.Property;
import thing.Thing;
import window.MultiWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EnchantTable extends Thing implements CraftTable {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Thing.propertyList);
        propertyList.add(GeneralProperty.ENCHANT_TABLE);
    }

    public EnchantTable(){
        name = "Стол зачарования";
        color = new Color(58, 228, 255);
        isStep = false;
    }

    @Override
    public void setPlayer(Player player) {

    }

    @Override
    public void drawWindow(MultiWindow multiWindow) {

    }
}
