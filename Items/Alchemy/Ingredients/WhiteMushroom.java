package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.HealPotion;
import Items.Alchemy.Potions.PowerPotion;

import java.awt.*;

public class WhiteMushroom extends Ingredient {

    public WhiteMushroom(){
        super();
        name = "Белый гриб";
        color = new Color(150, 76, 65);
        usage.add(new PowerPotion());
        usage.add(new HealPotion());
    }
}
