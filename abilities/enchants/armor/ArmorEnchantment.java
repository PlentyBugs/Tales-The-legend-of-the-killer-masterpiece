package abilities.enchants.armor;

import abilities.enchants.Enchant;
import item.Item;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class ArmorEnchantment extends Enchant {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Enchant.propertyList);
        propertyList.add(AbilityProperty.ARMOR_ENCHANTMENT);
    }

    public ArmorEnchantment(){}

    public ArmorEnchantment(Item item){
        super(item);
    }
}
