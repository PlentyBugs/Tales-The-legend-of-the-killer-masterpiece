package Creatures.AggressiveNPC.Boss;

import Abilities.Active.DamageUp;
import Abilities.Active.DecreaseDamage;
import Abilities.Enchants.Weapon.Vampirism;
import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Items.*;
import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Armors.Torso;
import Items.Weapons.Choppings.Axe;
import Items.Weapons.Swords.Sword;
import Items.Weapons.WeaponType;

import java.awt.*;

public class DeadGuardian extends Boss {

    public DeadGuardian(int x, int y, String name, int lvl, int hp) {

        this.x = x;
        this.y = y;

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        color = new Color(34, 54, 87);
    }

    public DeadGuardian(){
        this(0,0,"Мёртвый Страж",1,70);
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


        Sword guardianSword = new Sword();
        Torso guardianArmorTorso = new Torso();
        Helmet guardianArmorHelmet = new Helmet();
        if(lvl < 22){
            guardianSword.setMaterial(Material.MYTHRIL);
            guardianSword.setRarity(Rarity.MYSTICAL);
            guardianSword.setGrade(Grade.CURSE);
            guardianSword.setWeaponType(WeaponType.TWOHANDED);
            guardianArmorTorso.setMaterial(Material.MYTHRIL);
            guardianArmorTorso.setRarity(Rarity.MYSTICAL);
            guardianArmorTorso.setGrade(Grade.CURSE);
            guardianArmorHelmet.setMaterial(Material.MYTHRIL);
            guardianArmorHelmet.setRarity(Rarity.MYSTICAL);
            guardianArmorHelmet.setGrade(Grade.CURSE);
        } else if(lvl < 45){
            guardianSword.setMaterial(Material.ADAMANTINE);
            guardianSword.setRarity(Rarity.LEGENDARY);
            guardianSword.setGrade(Grade.CURSE);
            guardianSword.setWeaponType(WeaponType.TWOHANDED);
            guardianArmorTorso.setMaterial(Material.ADAMANTINE);
            guardianArmorTorso.setRarity(Rarity.LEGENDARY);
            guardianArmorTorso.setGrade(Grade.CURSE);
            guardianArmorHelmet.setMaterial(Material.ADAMANTINE);
            guardianArmorHelmet.setRarity(Rarity.LEGENDARY);
            guardianArmorHelmet.setGrade(Grade.CURSE);
        } else {
            guardianSword.setMaterial(Material.DEEP);
            guardianSword.setRarity(Rarity.DRAGON);
            guardianSword.setGrade(Grade.ARTIFACT);
            guardianSword.setWeaponType(WeaponType.TWOHANDED);
            guardianArmorTorso.setMaterial(Material.DEEP);
            guardianArmorTorso.setRarity(Rarity.DRAGON);
            guardianArmorTorso.setGrade(Grade.ARTIFACT);
            guardianArmorHelmet.setMaterial(Material.DEEP);
            guardianArmorHelmet.setRarity(Rarity.DRAGON);
            guardianArmorHelmet.setGrade(Grade.ARTIFACT);
        }
        guardianSword.countProperty();
        guardianArmorTorso.countProperty();
        guardianArmorHelmet.countProperty();
        addItemToInventory(guardianSword);
        addItemToInventory(guardianArmorTorso);
        addItemToInventory(guardianArmorHelmet);
        equip(guardianSword);
        equip(guardianArmorTorso);
        equip(guardianArmorHelmet);

        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/7, (new CriticalStrike()).getMaxLevel()))),
                new DamageUp(Math.max(0, Math.min(lvl/14, (new DecreaseDamage()).getMaxLevel()))),
                new DecreaseDamage(Math.max(0, Math.min(lvl/3, (new DecreaseDamage()).getMaxLevel()))),
                new Evasion(Math.max(0, Math.min(lvl/4, (new Evasion()).getMaxLevel()))));

        key = new Key()
                .setUnique(true)
                .setName("Ключ Стража")
                .countProperty();

        dropItems = new Item[]{
                key,
                new Sword(Material.MYTHRIL, Rarity.MYSTICAL, Grade.CURSE, 150, WeaponType.TWOHANDED)
                        .addEnchant(new Vampirism())
                        .setName("Меч стража")
                        .countProperty()
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
