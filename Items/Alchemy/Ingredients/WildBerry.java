package Items.Alchemy.Ingredients;

import Creatures.StatsEnum;
import Items.Alchemy.Potions.StatsUpPotion;

import java.awt.*;

public class WildBerry extends Ingredient {

    public WildBerry(){
        super();
        name = "Дикая ягода";
        color = new Color(219, 9, 73);
        usage.add(new StatsUpPotion(StatsEnum.ALCHEMY));
        usage.add(new StatsUpPotion());
    }
}
