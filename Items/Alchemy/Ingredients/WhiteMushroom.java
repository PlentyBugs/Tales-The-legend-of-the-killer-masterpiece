package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.HealPotion;
import Items.Alchemy.Potions.PowerPotion;
import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WhiteMushroom extends Ingredient {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public WhiteMushroom(){
        super();
        name = "Белый гриб";
        color = new Color(150, 76, 65);
        usage.add(new PowerPotion());
        usage.add(new HealPotion());
    }
}
