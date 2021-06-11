package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.PoisonPotion;
import Items.Alchemy.Potions.Alcohol.Wine;
import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Grapes extends Ingredient {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public Grapes(){
        super();
        name = "Виноград";
        color = new Color(183, 0, 255);
        usage.add(new Wine());
        usage.add(new Wine());
        usage.add(new PoisonPotion());
    }
}
