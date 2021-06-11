package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.HealPotion;
import Items.Alchemy.Potions.PoisonPotion;
import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Moss extends Ingredient {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public Moss(){
        super();
        name = "Мох";
        color = new Color(105, 150, 27);
        usage.add(new PoisonPotion());
        usage.add(new HealPotion());
    }
}
