package item.alchemy.ingredient;

import item.alchemy.potion.alcohol.Beer;
import item.alchemy.potion.PowerPotion;
import support.Property;

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
