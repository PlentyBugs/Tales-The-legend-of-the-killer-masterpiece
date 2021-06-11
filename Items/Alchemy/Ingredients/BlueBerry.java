package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.HealPotion;
import support.Property;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class BlueBerry extends Ingredient {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public BlueBerry(){
        super();
        name = "Черника";
        color = new Color(97, 135, 255);
        usage.add(new HealPotion());
    }
}
