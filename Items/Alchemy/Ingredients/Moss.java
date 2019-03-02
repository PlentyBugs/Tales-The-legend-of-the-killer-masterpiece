package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.HealPotion;
import Items.Alchemy.Potions.PoisonPotion;

import java.awt.*;

public class Moss extends Ingredient{

    public Moss(){
        super();
        name = "Мох";
        color = new Color(105, 150, 27);
        usage.add(new PoisonPotion());
        usage.add(new HealPotion());
    }
}
