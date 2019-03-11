package Items.Alchemy.Ingredients;

import Creatures.StatsEnum;
import Items.Alchemy.Potions.HealPotion;
import Items.Alchemy.Potions.StatsUpPotion;

public class EntBranch extends Ingredient {

    public EntBranch(){
        super();
        name = "Ветка Энта";
        usage.add(new StatsUpPotion(StatsEnum.ONEHANDEDWEAPON));
        usage.add(new StatsUpPotion(StatsEnum.ALCHEMY));
        usage.add(new HealPotion());
    }
}