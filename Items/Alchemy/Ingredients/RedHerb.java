package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.PoisonPotion;
import Items.Alchemy.Potions.PowerPotion;

import java.awt.*;

public class RedHerb extends Ingredient {

    public RedHerb(){
        super();
        name = "Красная трава";
        color = new Color(255, 90, 113);
        usage.add(new PoisonPotion());
        usage.add(new PowerPotion());
    }
}
