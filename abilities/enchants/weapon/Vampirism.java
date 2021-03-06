package abilities.enchants.weapon;

import abilities.enchants.EnchantType;
import creature.LiveCreature;
import item.Item;
import item.weapon.Weapon;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Vampirism extends WeaponEnchantment {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(WeaponEnchantment.propertyList);
        propertyList.add(AbilityProperty.VAMPIRISM);
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
