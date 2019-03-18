package Creatures.AggressiveNPC;

import Abilities.Active.DamageUp;
import Abilities.Active.DecreaseDamage;
import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Creatures.Human;
import Items.*;
import Items.Alchemy.Potions.HealPotion;
import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Armors.Torso;
import Items.Weapons.Bows.Bow;
import Items.Weapons.Bows.LongBow;
import Items.Weapons.Choppings.Axe;
import Items.Weapons.Staffs.Staff;
import Items.Weapons.Swords.Sword;
import Items.Weapons.WeaponType;

import java.awt.*;

public class Bandit extends Human {

    public Bandit(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        color = Color.RED;

        uniqueDropItems = new Item[]{new Sword(),
                new Sword(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.TWOHANDED),
                new Torso(),
                new Helmet(),
                new HealPotion(),
                new Bow(),
                new Ring(),
                new LongBow(),
                new Axe(),
                new Staff(),
                new Key()
        };
    }

    public Bandit(){
        this(0,0,"Бандит",1,70);
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
        stats.setLuck(5 + (int)(Math.random()*(lvl*4) + lvl*6));
        stats.setEloquence(5);
        stats.setBlacksmith(5);
        stats.setTheft(5);
        stats.setAlchemy(5);
        stats.setOne_handed_weapon(5 + (int)(Math.random()*(lvl*3) + lvl));
        stats.setTwo_handed_weapon(5 + (int)(Math.random()*(lvl*6)));
        stats.setPole_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setChopping_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setLong_range_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);


        Sword banditSword = new Sword();
        Torso banditArmorTorso = new Torso();
        Helmet banditArmorHelmet = new Helmet();
        if(lvl < 10){
            banditSword.setMaterial(Material.COPPER);
            banditSword.setRarity(Rarity.COMMON);
            banditSword.setGrade(Grade.COMMON);
            banditSword.setWeaponType(WeaponType.ONEHANDED);
            banditArmorTorso.setMaterial(Material.COPPER);
            banditArmorTorso.setRarity(Rarity.COMMON);
            banditArmorTorso.setGrade(Grade.COMMON);
            banditArmorHelmet.setMaterial(Material.COPPER);
            banditArmorHelmet.setRarity(Rarity.COMMON);
            banditArmorHelmet.setGrade(Grade.COMMON);
        } else if(lvl < 22){
            banditSword.setMaterial(Material.BRONZE);
            banditSword.setRarity(Rarity.RARE);
            banditSword.setGrade(Grade.MAGIC);
            banditSword.setWeaponType(WeaponType.TWOHANDED);
            banditArmorTorso.setMaterial(Material.BRONZE);
            banditArmorTorso.setRarity(Rarity.RARE);
            banditArmorTorso.setGrade(Grade.MAGIC);
            banditArmorHelmet.setMaterial(Material.BRONZE);
            banditArmorHelmet.setRarity(Rarity.RARE);
            banditArmorHelmet.setGrade(Grade.MAGIC);
        } else if(lvl < 45){
            banditSword.setMaterial(Material.MYTHRIL);
            banditSword.setRarity(Rarity.MYSTICAL);
            banditSword.setGrade(Grade.CURSE);
            banditSword.setWeaponType(WeaponType.TWOHANDED);
            banditArmorTorso.setMaterial(Material.MYTHRIL);
            banditArmorTorso.setRarity(Rarity.MYSTICAL);
            banditArmorTorso.setGrade(Grade.CURSE);
            banditArmorHelmet.setMaterial(Material.MYTHRIL);
            banditArmorHelmet.setRarity(Rarity.MYSTICAL);
            banditArmorHelmet.setGrade(Grade.CURSE);
        } else {
            banditSword.setMaterial(Material.DEEP);
            banditSword.setRarity(Rarity.DRAGON);
            banditSword.setGrade(Grade.ARTIFACT);
            banditSword.setWeaponType(WeaponType.TWOHANDED);
            banditArmorTorso.setMaterial(Material.MYTHRIL);
            banditArmorTorso.setRarity(Rarity.MYSTICAL);
            banditArmorTorso.setGrade(Grade.CURSE);
            banditArmorHelmet.setMaterial(Material.MYTHRIL);
            banditArmorHelmet.setRarity(Rarity.MYSTICAL);
            banditArmorHelmet.setGrade(Grade.CURSE);
        }
        banditSword.countProperty();
        banditArmorTorso.countProperty();
        banditArmorHelmet.countProperty();
        addItemToInventory(banditSword);
        addItemToInventory(banditArmorTorso);
        addItemToInventory(banditArmorHelmet);
        equip(banditSword);
        equip(banditArmorTorso);
        equip(banditArmorHelmet);

        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/15, (new CriticalStrike()).getMaxLevel()))),
                new DamageUp(Math.max(0, Math.min(lvl/20, (new DecreaseDamage()).getMaxLevel()))),
                new DecreaseDamage(Math.max(0, Math.min(lvl/20, (new DecreaseDamage()).getMaxLevel()))),
                new Evasion(Math.max(0, Math.min(lvl/12, (new Evasion()).getMaxLevel()))));
    }

    @Override
    public Bandit clone() throws CloneNotSupportedException
    {
        return (Bandit) super.clone();
    }

    @Override
    public Bandit getClearCopy() {
        return new Bandit();
    }
}
