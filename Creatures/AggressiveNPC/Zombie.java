package Creatures.AggressiveNPC;

import Abilities.Active.DamageUp;
import Abilities.Active.DecreaseDamage;
import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Creatures.Human;
import Items.Alchemy.Ingredients.DeadFlesh;
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
import support.GeneralProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Zombie extends Human {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Human.propertyList);
        propertyList.add(GeneralProperty.ZOMBIE);
    }

    public Zombie(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        color = new Color(33, 99, 70);

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
                new DeadFlesh(),
                new Key()
        };

        race = "Мертвые";
    }

    public Zombie(){
        this(0,0,"Зомби",1,70);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void countStatsAfterBorn(){
        stats.setStrength(5 + (int)(Math.random()*(lvl*15) + lvl*9));
        stats.setSpeed(5 + (int)(Math.random()*(lvl*4) + lvl));
        stats.setAgility(5 + (int)(Math.random()*(lvl*15)));
        stats.setIntelligence(0);
        stats.setLuck(5 + (int)(Math.random()*(lvl*14) + lvl*6));
        stats.setEloquence(5);
        stats.setBlacksmith(5);
        stats.setTheft(5);
        stats.setAlchemy(5);
        stats.setOneHandedWeapon(5 + (int)(Math.random()*(lvl*9) + lvl));
        stats.setTwoHandedWeapon(5);
        stats.setPoleWeapon(5);
        stats.setChoppingWeapon(5 + (int)(Math.random()*(lvl*9) + lvl*3));
        stats.setLongRangeWeapon(5 + (int)(Math.random()*(lvl*9) + lvl*3));

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);


        Torso skeletonArmorTorso = new Torso();
        Helmet skeletonArmorHelmet = new Helmet();
        if(lvl < 10){
            skeletonArmorTorso.setMaterial(Material.COPPER);
            skeletonArmorTorso.setRarity(Rarity.COMMON);
            skeletonArmorTorso.setGrade(Grade.COMMON);
            skeletonArmorHelmet.setMaterial(Material.COPPER);
            skeletonArmorHelmet.setRarity(Rarity.COMMON);
            skeletonArmorHelmet.setGrade(Grade.COMMON);
        } else if(lvl < 22){
            skeletonArmorTorso.setMaterial(Material.BRONZE);
            skeletonArmorTorso.setRarity(Rarity.RARE);
            skeletonArmorTorso.setGrade(Grade.MAGIC);
            skeletonArmorHelmet.setMaterial(Material.BRONZE);
            skeletonArmorHelmet.setRarity(Rarity.RARE);
            skeletonArmorHelmet.setGrade(Grade.MAGIC);
        } else if(lvl < 45){
            skeletonArmorTorso.setMaterial(Material.MYTHRIL);
            skeletonArmorTorso.setRarity(Rarity.MYSTICAL);
            skeletonArmorTorso.setGrade(Grade.CURSE);
            skeletonArmorHelmet.setMaterial(Material.MYTHRIL);
            skeletonArmorHelmet.setRarity(Rarity.MYSTICAL);
            skeletonArmorHelmet.setGrade(Grade.CURSE);
        } else {
            skeletonArmorTorso.setMaterial(Material.MYTHRIL);
            skeletonArmorTorso.setRarity(Rarity.MYSTICAL);
            skeletonArmorTorso.setGrade(Grade.CURSE);
            skeletonArmorHelmet.setMaterial(Material.MYTHRIL);
            skeletonArmorHelmet.setRarity(Rarity.MYSTICAL);
            skeletonArmorHelmet.setGrade(Grade.CURSE);
        }
        skeletonArmorTorso.countProperty();
        skeletonArmorHelmet.countProperty();
        addItemToInventory(skeletonArmorTorso);
        addItemToInventory(skeletonArmorHelmet);
        equip(skeletonArmorTorso);
        equip(skeletonArmorHelmet);

        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/17, (new CriticalStrike()).getMaxLevel()))),
                new DamageUp(Math.max(0, Math.min(lvl/5, (new DecreaseDamage()).getMaxLevel()))),
                new Evasion(Math.max(0, Math.min(lvl/35, (new Evasion()).getMaxLevel()))));
    }

    @Override
    public Zombie clone() throws CloneNotSupportedException
    {
        return (Zombie) super.clone();
    }

    @Override
    public Zombie getClearCopy() {
        return new Zombie();
    }
}
