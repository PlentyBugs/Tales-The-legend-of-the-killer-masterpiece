package Abilities.Enchants.Weapon;

import Abilities.Enchants.Enchant;
import Abilities.Enchants.EnchantType;
import Creatures.LiveCreature;
import Items.Weapons.Weapon;

public class Vampirism<T extends Weapon> extends Enchant {

    public Vampirism() {
        this(10);
    }

    public Vampirism(int power) {
        name = "Вампиризм";
        description = "Переводит часть вашего урона в жизни";
        enchantType = EnchantType.SELFUSE;
        this.power = power;
    }

    public Vampirism(T item) {
        super(item);
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
