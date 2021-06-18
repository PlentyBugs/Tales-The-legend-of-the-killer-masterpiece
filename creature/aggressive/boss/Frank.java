package creature.aggressive.boss;

import abilities.active.DamageUp;
import abilities.active.DecreaseDamage;
import abilities.enchants.weapon.KornelCurse;
import abilities.enchants.weapon.Vampirism;
import abilities.passive.CriticalStrike;
import abilities.passive.Evasion;
import item.armor.Helmet;
import item.armor.Ring;
import item.armor.Torso;
import item.*;
import item.weapon.chop.Axe;
import item.weapon.sword.Sword;
import item.weapon.WeaponType;
import support.Property;
import support.CreatureProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Frank extends Boss {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Boss.propertyList);
        propertyList.add(CreatureProperty.FRANK);
    }

    public Frank(int x, int y, String name, int lvl, int hp) {

        this.x = x;
        this.y = y;

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        color = new Color(124, 68, 117);
    }

    public Frank(){
        this(0,0,"Франк",1,70);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void countStatsAfterBorn(){
        stats.setStrength(5 + (int)(Math.random()*(lvl*3) + lvl*9));
        stats.setSpeed(5 + (int)(Math.random()*(lvl*4) + lvl));
        stats.setAgility(5 + (int)(Math.random()*(lvl*3) + lvl*4));
        stats.setIntelligence(5 + (int)(Math.random()*lvl + lvl*5));
        stats.setLuck(5 + (int)(Math.random()*(lvl*25) + lvl*6));
        stats.setEloquence(5);
        stats.setBlacksmith(5);
        stats.setTheft(5);
        stats.setAlchemy(5);
        stats.setOneHandedWeapon(5 + (int)(Math.random()*(lvl*3) + lvl));
        stats.setTwoHandedWeapon(5 + (int)(Math.random()*(lvl*17)));
        stats.setPoleWeapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setChoppingWeapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setLongRangeWeapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);


        Sword frankSword = new Sword();
        Torso frankArmorTorso = new Torso();
        Helmet frankArmorHelmet = new Helmet();
        if(lvl < 22){
            frankSword.setMaterial(Material.MYTHRIL);
            frankSword.setRarity(Rarity.MYSTICAL);
            frankSword.setGrade(Grade.CURSE);
            frankSword.setWeaponType(WeaponType.TWO_HANDED);
            frankArmorTorso.setMaterial(Material.MYTHRIL);
            frankArmorTorso.setRarity(Rarity.MYSTICAL);
            frankArmorTorso.setGrade(Grade.CURSE);
            frankArmorHelmet.setMaterial(Material.MYTHRIL);
            frankArmorHelmet.setRarity(Rarity.MYSTICAL);
            frankArmorHelmet.setGrade(Grade.CURSE);
        } else if(lvl < 45){
            frankSword.setMaterial(Material.ADAMANTINE);
            frankSword.setRarity(Rarity.LEGENDARY);
            frankSword.setGrade(Grade.CURSE);
            frankSword.setWeaponType(WeaponType.TWO_HANDED);
            frankArmorTorso.setMaterial(Material.ADAMANTINE);
            frankArmorTorso.setRarity(Rarity.LEGENDARY);
            frankArmorTorso.setGrade(Grade.CURSE);
            frankArmorHelmet.setMaterial(Material.ADAMANTINE);
            frankArmorHelmet.setRarity(Rarity.LEGENDARY);
            frankArmorHelmet.setGrade(Grade.CURSE);
        } else {
            frankSword.setMaterial(Material.DEEP);
            frankSword.setRarity(Rarity.DRAGON);
            frankSword.setGrade(Grade.ARTIFACT);
            frankSword.setWeaponType(WeaponType.TWO_HANDED);
            frankArmorTorso.setMaterial(Material.DEEP);
            frankArmorTorso.setRarity(Rarity.DRAGON);
            frankArmorTorso.setGrade(Grade.ARTIFACT);
            frankArmorHelmet.setMaterial(Material.DEEP);
            frankArmorHelmet.setRarity(Rarity.DRAGON);
            frankArmorHelmet.setGrade(Grade.ARTIFACT);
        }
        frankSword.countProperty();
        frankArmorTorso.countProperty();
        frankArmorHelmet.countProperty();
        addItemToInventory(frankSword);
        addItemToInventory(frankArmorTorso);
        addItemToInventory(frankArmorHelmet);
        equip(frankSword);
        equip(frankArmorTorso);
        equip(frankArmorHelmet);
        frankSword.addEnchant(new Vampirism(4));
        frankSword.addEnchant(new KornelCurse(6));
        frankSword.setName("Меч Франка");
        frankArmorTorso.setName("Броня Франка");
        frankArmorHelmet.setName("Шлем Франка");

        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/7, (new CriticalStrike()).getMaxLevel()))),
                new DamageUp(Math.max(0, Math.min(lvl/14, (new DecreaseDamage()).getMaxLevel()))),
                new DecreaseDamage(Math.max(0, Math.min(lvl/3, (new DecreaseDamage()).getMaxLevel()))),
                new Evasion(Math.max(0, Math.min(lvl/4, (new Evasion()).getMaxLevel()))));

        key = new Key()
                .setUnique(true)
                .setName("Ключ Франка")
                .countProperty();

        dropItems = new Item[]{
                key,
                frankSword
        };

        uniqueDropItems = new Item[]{
                new Sword(),
                new Sword(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.TWO_HANDED),
                new Torso(),
                new Helmet(),
                new Ring(),
                new Axe()
        };
    }

    @Override
    public Frank clone() throws CloneNotSupportedException
    {
        return (Frank) super.clone();
    }

    @Override
    public Frank getClearCopy() {
        return new Frank();
    }
}
