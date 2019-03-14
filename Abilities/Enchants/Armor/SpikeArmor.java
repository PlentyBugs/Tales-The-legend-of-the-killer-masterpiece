package Abilities.Enchants.Armor;

import Abilities.Enchants.Enchant;
import Abilities.Enchants.EnchantType;
import Creatures.LiveCreature;
import Items.Armors.Armor;

public class SpikeArmor<T extends Armor> extends Enchant {

    public SpikeArmor(T item) {
        super(item);
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
