package item.alchemy.ingredient;

import item.alchemy.potion.PoisonPotion;
import item.alchemy.potion.PowerPotion;
import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RedHerb extends Ingredient {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public RedHerb(){
        super();
        name = "Красная трава";
        color = new Color(255, 90, 113);
        usage.add(new PoisonPotion());
        usage.add(new PowerPotion());
    }
}
