package Abilities.Enchants.Armor;

import Abilities.Enchants.Enchant;
import Abilities.Enchants.EnchantType;
import Creatures.LiveCreature;
import Items.Item;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class HigherPath extends Enchant {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Enchant.propertyList);
    }

    public HigherPath() {
        name = "Путь Высшего";
        description = "Дает шанс избежать урона";
        enchantType = EnchantType.DEFEND;
        power = 1;
    }

    public HigherPath(Item armor) {
        super(armor);
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
