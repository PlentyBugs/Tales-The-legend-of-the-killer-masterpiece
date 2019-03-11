package Items.Alchemy.Ingredients;

import Items.Alchemy.Potions.PoisonPotion;
import Items.Alchemy.Potions.Alcohol.Wine;

import java.awt.*;

public class Grapes extends Ingredient {

    public Grapes(){
        super();
        name = "Виноград";
        color = new Color(183, 0, 255);
        usage.add(new Wine());
        usage.add(new Wine());
        usage.add(new PoisonPotion());
    }
}
