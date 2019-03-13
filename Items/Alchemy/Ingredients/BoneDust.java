package Items.Alchemy.Ingredients;

import Creatures.StatsEnum;
import Items.Alchemy.Potions.NecrosisPotion;
import Items.Alchemy.Potions.PoisonPotion;
import Items.Alchemy.Potions.StatsUpPotion;

public class BoneDust extends Ingredient {

    public BoneDust(){
        super();
        name = "Костная пыль";
        usage.add(new StatsUpPotion(StatsEnum.POLEWEAPON));
        usage.add(new StatsUpPotion(StatsEnum.ALCHEMY));
        usage.add(new PoisonPotion());
        usage.add(new NecrosisPotion());
    }
}