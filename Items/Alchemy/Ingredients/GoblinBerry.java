package Items.Alchemy.Ingredients;

import Creatures.StatsEnum;
import Items.Alchemy.Potions.StatsUpPotion;

import java.awt.*;

public class GoblinBerry extends Ingredient {

    public GoblinBerry(){
        super();
        name = "Гоблинская ягода";
        color = new Color(220, 255, 45);
        usage.add(new StatsUpPotion(StatsEnum.AGILITY));
    }
}
