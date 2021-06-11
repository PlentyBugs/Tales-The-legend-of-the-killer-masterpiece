package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.Alcohol.Beer;
import Items.Alchemy.Potions.PowerPotion;
import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hop  extends Ingredient {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public Hop(){
        super();
        name = "Хмель";
        color = new Color(211, 255, 55);
        usage.add(new Beer());
        usage.add(new Beer());
        usage.add(new PowerPotion());
    }
}
