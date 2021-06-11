package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.PowerPotion;
import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrunkenBerry extends Ingredient {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public DrunkenBerry(){
        super();
        name = "Пьяная ягода";
        color = new Color(255, 131, 13);
        usage.add(new PowerPotion());
    }
}
