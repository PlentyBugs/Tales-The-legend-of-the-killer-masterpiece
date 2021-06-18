package abilities.enchants.weapon;

import abilities.enchants.Enchant;
import item.Item;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class WeaponEnchantment extends Enchant {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Enchant.propertyList);
        propertyList.add(AbilityProperty.WEAPON_ENCHANTMENT);
    }

    public WeaponEnchantment(){}

    public WeaponEnchantment(Item item){
        super(item);
    }
}
