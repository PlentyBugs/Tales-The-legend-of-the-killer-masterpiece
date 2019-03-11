package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.Alcohol.Beer;
import Items.Alchemy.Potions.PowerPotion;

import java.awt.*;

public class Hop  extends Ingredient {

    public Hop(){
        super();
        name = "Хмель";
        color = new Color(211, 255, 55);
        usage.add(new Beer());
        usage.add(new Beer());
        usage.add(new PowerPotion());
    }
}
