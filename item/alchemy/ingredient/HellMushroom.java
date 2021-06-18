package item.alchemy.ingredient;

import creature.StatsEnum;
import item.alchemy.potion.StatsUpPotion;
import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HellMushroom extends Ingredient {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public HellMushroom(){
        super();
        name = "Адский гриб";
        color = new Color(255, 0, 90);
        usage.add(new StatsUpPotion(StatsEnum.STRENGTH));
    }
}
