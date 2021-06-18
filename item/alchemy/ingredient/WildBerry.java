package item.alchemy.ingredient;

import creature.StatsEnum;
import item.alchemy.potion.StatsUpPotion;
import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WildBerry extends Ingredient {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public WildBerry(){
        super();
        name = "Дикая ягода";
        color = new Color(219, 9, 73);
        usage.add(new StatsUpPotion(StatsEnum.ALCHEMY));
        usage.add(new StatsUpPotion());
    }
}
