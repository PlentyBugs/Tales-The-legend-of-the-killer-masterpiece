package Items.Alchemy.Ingredients;

import Creatures.StatsEnum;
import Items.Alchemy.Potions.PoisonPotion;
import Items.Alchemy.Potions.StatsUpPotion;

import java.awt.*;

public class HellMushroom extends Ingredient {

    public HellMushroom(){
        super();
        name = "Адский гриб";
        color = new Color(255, 0, 90);
        usage.add(new StatsUpPotion(StatsEnum.STRENGTH));
        usage.add(new PoisonPotion());
    }
}
