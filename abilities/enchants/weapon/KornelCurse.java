package abilities.enchants.weapon;

import abilities.enchants.EnchantType;
import creature.LiveCreature;
import item.Item;
import item.weapon.Weapon;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class KornelCurse extends WeaponEnchantment {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(WeaponEnchantment.propertyList);
        propertyList.add(AbilityProperty.KORNEL_CURSE);
    }

    public KornelCurse() {
        this(3);
    }

    public KornelCurse(int power) {
        name = "Проклятье Корнела";
        description = "Увеличивает урон оружия в 3 раза, но делает его полностью случайным";
        enchantType = EnchantType.SELFUSE;
        this.power = power;
    }

    public KornelCurse(Item weapon) {
        super(weapon);
        name = "Проклятье Корнела";
        description = "Увеличивает урон оружия в 3 раза, но делает его полностью случайным";
        enchantType = EnchantType.SELFUSE;
        power = 3;
    }

    @Override
    public void use(LiveCreature liveCreature){
        ((Weapon)item).setEnchantBonus(power*Math.random());
    }

    public int getPower() {
        return power;
    }
}
