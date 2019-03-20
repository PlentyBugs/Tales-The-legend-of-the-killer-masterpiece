package Creatures.AggressiveNPC.Boss;

import Abilities.Active.DamageUp;
import Abilities.Active.DecreaseDamage;
import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Armors.Torso;
import Items.*;
import Items.Weapons.Choppings.Axe;
import Items.Weapons.Swords.Sword;
import Items.Weapons.WeaponType;

import java.awt.*;

public class Frank extends Boss {

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
        stats.setOne_handed_weapon(5 + (int)(Math.random()*(lvl*3) + lvl));
        stats.setTwo_handed_weapon(5 + (int)(Math.random()*(lvl*17)));
        stats.setPole_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setChopping_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setLong_range_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));

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
            frankSword.setWeaponType(WeaponType.TWOHANDED);
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
            frankSword.setWeaponType(WeaponType.TWOHANDED);
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
            frankSword.setWeaponType(WeaponType.TWOHANDED);
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

        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/7, (new CriticalStrike()).getMaxLevel()))),
                new DamageUp(Math.max(0, Math.min(lvl/14, (new DecreaseDamage()).getMaxLevel()))),
                new DecreaseDamage(Math.max(0, Math.min(lvl/3, (new DecreaseDamage()).getMaxLevel()))),
                new Evasion(Math.max(0, Math.min(lvl/4, (new Evasion()).getMaxLevel()))));

        key = new Key()
                .setUnique(true)
                .setName("Ключ Франка")
                .countProperty();

        dropItems = new Item[]{
                key
        };

        uniqueDropItems = new Item[]{
                new Sword(),
                new Sword(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.TWOHANDED),
                new Torso(),
                new Helmet(),
                new Ring(),
                new Axe()
        };
    }

    @Override
    public DeadGuardian clone() throws CloneNotSupportedException
    {
        return (DeadGuardian) super.clone();
    }

    @Override
    public DeadGuardian getClearCopy() {
        return new DeadGuardian();
    }
}
