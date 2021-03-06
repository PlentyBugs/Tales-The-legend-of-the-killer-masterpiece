package item.alchemy.ingredient;

import effect.EffectType;
import effect.Heal;
import item.alchemy.potion.HealPotion;
import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GreenHerb extends Ingredient {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ingredient.propertyList);
    }

    public GreenHerb(){
        super();
        name = "Зеленая трава";
        color = new Color(114, 255, 155);
        effect = new Heal(EffectType.MOMENT);
        usage.add(new HealPotion());
    }
}
