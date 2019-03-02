package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.PowerPotion;

import java.awt.*;

public class DrunkenBerry extends Ingredient {

    public DrunkenBerry(){
        super();
        name = "Пьяная ягода";
        color = new Color(255, 131, 13);
        usage.add(new PowerPotion());
    }
}
