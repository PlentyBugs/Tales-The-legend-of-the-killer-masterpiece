package Creatures.AggressiveNPC;

import Abilities.Active.DamageUp;
import Abilities.Active.DecreaseDamage;
import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Creatures.Human;
import Items.Alchemy.Ingredients.BoneDust;
import Items.Alchemy.Potions.HealPotion;
import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Armors.Torso;
import Items.*;
import Items.Weapons.Bows.Bow;
import Items.Weapons.Bows.LongBow;
import Items.Weapons.Choppings.Axe;
import Items.Weapons.Staffs.Staff;
import Items.Weapons.Swords.Sword;
import Items.Weapons.WeaponType;
import support.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Skeleton extends Human {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Human.propertyList);
        propertyList.add(Property.SKELETON);
    }

    public Skeleton(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        color = new Color(155,155,155);

        uniqueDropItems = new Item[]{new Sword(),
                new Sword(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.TWO_HANDED),
                new Torso(),
                new Helmet(),
                new HealPotion(),
                new Bow(),
                new Ring(),
                new LongBow(),
                new Axe(),
                new Staff(),
                new BoneDust(),
                new Key()
        };

        race = "Мертвые";
    }

    public Skeleton(){
        this(0,0,"Скелет",1,70);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void countStatsAfterBorn(){
        stats.setStrength(5);
        stats.setSpeed(5 + (int)(Math.random()*(lvl*35) + lvl*6));
        stats.setAgility(5);
        stats.setIntelligence(0);
        stats.setLuck(5 + (int)(Math.random()*(lvl*14) + lvl*6));
        stats.setEloquence(5);
        stats.setBlacksmith(5);
        stats.setTheft(5);
        stats.setAlchemy(5);
        stats.setOne_handed_weapon(5 + (int)(Math.random()*(lvl*19) + lvl));
        stats.setTwo_handed_weapon(5);
        stats.setPole_weapon(5);
        stats.setChopping_weapon(5 + (int)(Math.random()*(lvl*9) + lvl*3));
        stats.setLong_range_weapon(5 + (int)(Math.random()*(lvl*15) + lvl*3));

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);


        LongBow skeletonBow = new LongBow();
        Torso skeletonArmorTorso = new Torso();
        Helmet skeletonArmorHelmet = new Helmet();
        if(lvl < 10){
            skeletonBow.setMaterial(Material.COPPER);
            skeletonBow.setRarity(Rarity.COMMON);
            skeletonBow.setGrade(Grade.COMMON);
            skeletonBow.setWeaponType(WeaponType.ONE_HANDED);
            skeletonArmorTorso.setMaterial(Material.COPPER);
            skeletonArmorTorso.setRarity(Rarity.COMMON);
            skeletonArmorTorso.setGrade(Grade.COMMON);
            skeletonArmorHelmet.setMaterial(Material.COPPER);
            skeletonArmorHelmet.setRarity(Rarity.COMMON);
            skeletonArmorHelmet.setGrade(Grade.COMMON);
        } else if(lvl < 22){
            skeletonBow.setMaterial(Material.BRONZE);
            skeletonBow.setRarity(Rarity.RARE);
            skeletonBow.setGrade(Grade.MAGIC);
            skeletonBow.setWeaponType(WeaponType.TWO_HANDED);
            skeletonArmorTorso.setMaterial(Material.BRONZE);
            skeletonArmorTorso.setRarity(Rarity.RARE);
            skeletonArmorTorso.setGrade(Grade.MAGIC);
            skeletonArmorHelmet.setMaterial(Material.BRONZE);
            skeletonArmorHelmet.setRarity(Rarity.RARE);
            skeletonArmorHelmet.setGrade(Grade.MAGIC);
        } else if(lvl < 45){
            skeletonBow.setMaterial(Material.MYTHRIL);
            skeletonBow.setRarity(Rarity.MYSTICAL);
            skeletonBow.setGrade(Grade.CURSE);
            skeletonBow.setWeaponType(WeaponType.TWO_HANDED);
            skeletonArmorTorso.setMaterial(Material.MYTHRIL);
            skeletonArmorTorso.setRarity(Rarity.MYSTICAL);
            skeletonArmorTorso.setGrade(Grade.CURSE);
            skeletonArmorHelmet.setMaterial(Material.MYTHRIL);
            skeletonArmorHelmet.setRarity(Rarity.MYSTICAL);
            skeletonArmorHelmet.setGrade(Grade.CURSE);
        } else {
            skeletonBow.setMaterial(Material.DEEP);
            skeletonBow.setRarity(Rarity.DRAGON);
            skeletonBow.setGrade(Grade.ARTIFACT);
            skeletonBow.setWeaponType(WeaponType.TWO_HANDED);
            skeletonArmorTorso.setMaterial(Material.MYTHRIL);
            skeletonArmorTorso.setRarity(Rarity.MYSTICAL);
            skeletonArmorTorso.setGrade(Grade.CURSE);
            skeletonArmorHelmet.setMaterial(Material.MYTHRIL);
            skeletonArmorHelmet.setRarity(Rarity.MYSTICAL);
            skeletonArmorHelmet.setGrade(Grade.CURSE);
        }
        skeletonBow.countProperty();
        skeletonArmorTorso.countProperty();
        skeletonArmorHelmet.countProperty();
        addItemToInventory(skeletonBow);
        addItemToInventory(skeletonArmorTorso);
        addItemToInventory(skeletonArmorHelmet);
        equip(skeletonBow);
        equip(skeletonArmorTorso);
        equip(skeletonArmorHelmet);

        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/7, (new CriticalStrike()).getMaxLevel()))),
                new DamageUp(Math.max(0, Math.min(lvl/30, (new DecreaseDamage()).getMaxLevel()))),
                new Evasion(Math.max(0, Math.min(lvl/6, (new Evasion()).getMaxLevel()))));
    }

    @Override
    public Skeleton clone() throws CloneNotSupportedException
    {
        return (Skeleton) super.clone();
    }

    @Override
    public Skeleton getClearCopy() {
        return new Skeleton();
    }
}
