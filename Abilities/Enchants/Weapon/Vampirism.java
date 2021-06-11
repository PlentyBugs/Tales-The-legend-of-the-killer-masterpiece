package Abilities.Enchants.Weapon;

import Abilities.Ability;
import Abilities.Enchants.Enchant;
import Abilities.Enchants.EnchantType;
import Creatures.LiveCreature;
import Items.Item;
import Items.Weapons.Weapon;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Vampirism extends Enchant {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Enchant.propertyList);
    }

    public Vampirism() {
        this(10);
    }

    public Vampirism(int power) {
        name = "Вампиризм";
        description = "Переводит часть вашего урона в жизни";
        enchantType = EnchantType.SELFUSE;
        this.power = power;
    }

    public Vampirism(Item weapon) {
        super(weapon);
        name = "Вампиризм";
        description = "Переводит часть вашего урона в жизни";
        enchantType = EnchantType.SELFUSE;
        power = 10;
    }

    @Override
    public void use(LiveCreature liveCreature){
        if(liveCreature.getHp() + (((Weapon)item).getDamage()*power)/100.0 > liveCreature.getMaxHp())
            liveCreature.setHp(liveCreature.getMaxHp());
        else
            liveCreature.setHp(liveCreature.getHp() + (((Weapon)item).getDamage()*power)/100.0);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power){
        this.power = power;
    }
}
