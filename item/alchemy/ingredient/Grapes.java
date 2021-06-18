package item.alchemy.ingredient;

import item.alchemy.potion.PoisonPotion;
import item.alchemy.potion.alcohol.Wine;
import support.Property;

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
