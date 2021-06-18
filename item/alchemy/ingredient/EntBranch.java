package item.alchemy.ingredient;

import creature.StatsEnum;
import item.alchemy.potion.HealPotion;
import item.alchemy.potion.StatsUpPotion;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class EntBranch extends Ingredient {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public EntBranch(){
        super();
        name = "Ветка Энта";
        usage.add(new StatsUpPotion(StatsEnum.ONE_HANDED_WEAPON));
        usage.add(new StatsUpPotion(StatsEnum.ALCHEMY));
        usage.add(new HealPotion());
    }
}