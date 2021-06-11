package Items.Alchemy.Ingredients;

import Creatures.StatsEnum;
import Items.Alchemy.Potions.NecrosisPotion;
import Items.Alchemy.Potions.PoisonPotion;
import Items.Alchemy.Potions.StatsUpPotion;
import support.Property;
import support.GeneralProperty;

import java.util.ArrayList;
import java.util.List;

public class DeadFlesh extends Ingredient {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public DeadFlesh(){
        super();
        name = "Мёртвая плоть";
        usage.add(new StatsUpPotion(StatsEnum.POLEWEAPON));
        usage.add(new StatsUpPotion(StatsEnum.ALCHEMY));
        usage.add(new PoisonPotion());
        usage.add(new NecrosisPotion());
    }
}