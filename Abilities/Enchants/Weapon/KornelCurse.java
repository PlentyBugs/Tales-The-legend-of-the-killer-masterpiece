package Abilities.Enchants.Weapon;

import Abilities.Enchants.Enchant;
import Abilities.Enchants.EnchantType;
import Creatures.LiveCreature;
import Items.Weapons.Weapon;

public class KornelCurse<T extends Weapon> extends Enchant {

    public KornelCurse() {
        name = "Проклятье Корнела";
        description = "Увеличивает урон оружия в 3 раза, но делает его полностью случайным";
        enchantType = EnchantType.SELFUSE;
        power = 3;
    }

    public KornelCurse(T item) {
        super(item);
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
