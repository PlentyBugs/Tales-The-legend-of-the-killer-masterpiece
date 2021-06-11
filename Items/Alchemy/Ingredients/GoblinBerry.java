package Items.Alchemy.Ingredients;

import Creatures.StatsEnum;
import Items.Alchemy.Potions.StatsUpPotion;
import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GoblinBerry extends Ingredient {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public GoblinBerry(){
        super();
        name = "Гоблинская ягода";
        color = new Color(220, 255, 45);
        usage.add(new StatsUpPotion(StatsEnum.AGILITY));
    }
}
