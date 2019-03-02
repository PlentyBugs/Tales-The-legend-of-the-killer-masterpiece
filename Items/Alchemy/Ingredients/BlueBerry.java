package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.HealPotion;

import java.awt.*;

public class BlueBerry extends Ingredient {

    public BlueBerry(){
        super();
        name = "Черника";
        color = new Color(97, 135, 255);
        usage.add(new HealPotion());
    }
}
