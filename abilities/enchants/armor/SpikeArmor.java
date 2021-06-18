package abilities.enchants.armor;

import abilities.enchants.Enchant;
import abilities.enchants.EnchantType;
import creature.LiveCreature;
import item.Item;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class SpikeArmor extends ArmorEnchantment {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Enchant.propertyList);
        propertyList.add(AbilityProperty.SPIKE_ARMOR);
    }

    public SpikeArmor() {
        name = "Шипованная броня";
        description = "Возвращает часть урона врагу";
        enchantType = EnchantType.COUNTERATTACK;
        power = 5;
    }

    public SpikeArmor(Item armor) {
        super(armor);
        name = "Шипованная броня";
        description = "Возвращает часть урона врагу";
        enchantType = EnchantType.COUNTERATTACK;
        power = 5;
    }

    @Override
    public void use(LiveCreature liveCreature){
        liveCreature.setHp(Math.max(0, (damage*power)/100.0));
    }

    public int getPower() {
        return power;
    }
}
