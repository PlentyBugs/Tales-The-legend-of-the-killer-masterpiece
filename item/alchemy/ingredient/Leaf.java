package item.alchemy.ingredient;

import creature.StatsEnum;
import item.alchemy.potion.PoisonPotion;
import item.alchemy.potion.StatsUpPotion;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Leaf extends Ingredient {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public Leaf(){
        super();
        name = "Лист";
        usage.add(new StatsUpPotion(StatsEnum.POLE_WEAPON));
        usage.add(new StatsUpPotion(StatsEnum.ALCHEMY));
        usage.add(new PoisonPotion());
    }
}