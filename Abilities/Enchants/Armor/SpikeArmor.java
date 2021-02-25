package Abilities.Enchants.Armor;

import Abilities.Enchants.Enchant;
import Abilities.Enchants.EnchantType;
import Creatures.LiveCreature;
import Items.Item;

public class SpikeArmor extends Enchant {

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
