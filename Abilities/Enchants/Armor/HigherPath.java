package Abilities.Enchants.Armor;

import Abilities.Enchants.Enchant;
import Abilities.Enchants.EnchantType;
import Creatures.LiveCreature;
import Items.Armors.Armor;

public class HigherPath<T extends Armor> extends Enchant {

    public HigherPath(T item) {
        super(item);
        name = "Путь Высшего";
        description = "Дает шанс избежать урона";
        enchantType = EnchantType.DEFEND;
        power = 1;
    }

    @Override
    public void use(LiveCreature liveCreature){
        double chance = Math.random()*100;
        if(chance < power)
            liveCreature.setHp(liveCreature.getHp() + damage);
    }

    public int getPower() {
        return power;
    }
}
