package Creatures.AggressiveNPC.Boss;

import Abilities.Active.DamageUp;
import Abilities.Active.DecreaseDamage;
import Abilities.Enchants.Armor.HigherPath;
import Abilities.Enchants.Weapon.Vampirism;
import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Armors.Torso;
import Items.*;
import Items.Weapons.Choppings.Axe;
import Items.Weapons.Staffs.Staff;
import Items.Weapons.Swords.Sword;
import Items.Weapons.WeaponType;

import java.awt.*;

public class HigherGhost extends Boss {

    public HigherGhost(int x, int y, String name, int lvl, int hp) {

        this.x = x;
        this.y = y;

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        color = new Color(138, 149, 151);
    }

    public HigherGhost(){
        this(0,0,"Высший Призрак",1,70);
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
        stats.setIntelligence(5 + (int)(Math.random()*lvl + lvl*30));
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


        Sword higherGhostSword = new Sword();
        Staff higherGhostStaff = new Staff();
        higherGhostSword.addEnchant(new Vampirism());
        Torso higherGhostArmorTorso = new Torso();
        higherGhostArmorTorso.addEnchant(new HigherPath());
        Helmet higherGhostArmorHelmet = new Helmet();
        higherGhostArmorHelmet.addEnchant(new HigherPath());
        if(lvl < 22){
            higherGhostSword.setMaterial(Material.MYTHRIL);
            higherGhostSword.setRarity(Rarity.MYSTICAL);
            higherGhostSword.setGrade(Grade.CURSE);
            higherGhostStaff.setMaterial(Material.MYTHRIL);
            higherGhostStaff.setRarity(Rarity.MYSTICAL);
            higherGhostStaff.setGrade(Grade.CURSE);
            higherGhostArmorTorso.setMaterial(Material.MYTHRIL);
            higherGhostArmorTorso.setRarity(Rarity.MYSTICAL);
            higherGhostArmorTorso.setGrade(Grade.CURSE);
            higherGhostArmorHelmet.setMaterial(Material.MYTHRIL);
            higherGhostArmorHelmet.setRarity(Rarity.MYSTICAL);
            higherGhostArmorHelmet.setGrade(Grade.CURSE);
        } else if(lvl < 45){
            higherGhostSword.setMaterial(Material.ADAMANTINE);
            higherGhostSword.setRarity(Rarity.LEGENDARY);
            higherGhostSword.setGrade(Grade.CURSE);
            higherGhostStaff.setMaterial(Material.ADAMANTINE);
            higherGhostStaff.setRarity(Rarity.LEGENDARY);
            higherGhostStaff.setGrade(Grade.CURSE);
            higherGhostArmorTorso.setMaterial(Material.ADAMANTINE);
            higherGhostArmorTorso.setRarity(Rarity.LEGENDARY);
            higherGhostArmorTorso.setGrade(Grade.CURSE);
            higherGhostArmorHelmet.setMaterial(Material.ADAMANTINE);
            higherGhostArmorHelmet.setRarity(Rarity.LEGENDARY);
            higherGhostArmorHelmet.setGrade(Grade.CURSE);
        } else {
            higherGhostSword.setMaterial(Material.DEEP);
            higherGhostSword.setRarity(Rarity.DRAGON);
            higherGhostSword.setGrade(Grade.ARTIFACT);
            higherGhostStaff.setMaterial(Material.DEEP);
            higherGhostStaff.setRarity(Rarity.DRAGON);
            higherGhostStaff.setGrade(Grade.ARTIFACT);
            higherGhostArmorTorso.setMaterial(Material.DEEP);
            higherGhostArmorTorso.setRarity(Rarity.DRAGON);
            higherGhostArmorTorso.setGrade(Grade.ARTIFACT);
            higherGhostArmorHelmet.setMaterial(Material.DEEP);
            higherGhostArmorHelmet.setRarity(Rarity.DRAGON);
            higherGhostArmorHelmet.setGrade(Grade.ARTIFACT);
        }
        higherGhostSword.countProperty();
        higherGhostStaff.countProperty();
        higherGhostStaff.setName("Посох Высшего призрака");
        higherGhostArmorTorso.countProperty();
        higherGhostArmorHelmet.countProperty();
        addItemToInventory(higherGhostSword);
        addItemToInventory(higherGhostArmorTorso);
        addItemToInventory(higherGhostArmorHelmet);
        equip(higherGhostSword);
        equip(higherGhostArmorTorso);
        equip(higherGhostArmorHelmet);
        higherGhostArmorTorso.setName("Броня Высшего Призрака");
        higherGhostArmorHelmet.setName("Шлем Высшего Призрака");

        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/7, (new CriticalStrike()).getMaxLevel()))),
                new DamageUp(Math.max(0, Math.min(lvl/14, (new DecreaseDamage()).getMaxLevel()))),
                new DecreaseDamage(Math.max(0, Math.min(lvl/3, (new DecreaseDamage()).getMaxLevel()))),
                new Evasion(Math.max(0, Math.min(lvl/4, (new Evasion()).getMaxLevel()))));

        key = new Key()
                .setUnique(true)
                .setName("Ключ Призрака")
                .countProperty();

        dropItems = new Item[]{
                key,
                higherGhostStaff
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