package Abilities.Enchants.Weapon;

import Abilities.Enchants.Enchant;
import Abilities.Enchants.EnchantType;
import Creatures.LiveCreature;
import Items.Item;
import Items.Weapons.Weapon;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class KornelCurse extends Enchant {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Enchant.propertyList);
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
