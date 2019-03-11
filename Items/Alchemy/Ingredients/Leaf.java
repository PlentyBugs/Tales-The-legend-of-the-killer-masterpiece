package Items.Alchemy.Ingredients;

import Creatures.StatsEnum;
import Items.Alchemy.Potions.PoisonPotion;
import Items.Alchemy.Potions.StatsUpPotion;

public class Leaf extends Ingredient {

    public Leaf(){
        super();
        name = "Лист";
        usage.add(new StatsUpPotion(StatsEnum.POLEWEAPON));
        usage.add(new StatsUpPotion(StatsEnum.ALCHEMY));
        usage.add(new PoisonPotion());
    }
}