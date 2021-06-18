package creature.aggressive.boss;

import abilities.active.DamageUp;
import abilities.active.DecreaseDamage;
import abilities.enchants.armor.HigherPath;
import abilities.enchants.weapon.Vampirism;
import abilities.passive.CriticalStrike;
import abilities.passive.Evasion;
import item.armor.Helmet;
import item.armor.Ring;
import item.armor.Torso;
import item.*;
import item.weapon.chop.Axe;
import item.weapon.staff.Staff;
import item.weapon.sword.Sword;
import item.weapon.WeaponType;
import support.Property;
import support.CreatureProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HigherGhost extends Boss {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Boss.propertyList);
        propertyList.add(CreatureProperty.HIGHER_GHOST);
    }

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
        stats.setOneHandedWeapon(5 + (int)(Math.random()*(lvl*3) + lvl));
        stats.setTwoHandedWeapon(5 + (int)(Math.random()*(lvl*17)));
        stats.setPoleWeapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setChoppingWeapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setLongRangeWeapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));

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
                new Sword(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.TWO_HANDED),
                new Torso(),
                new Helmet(),
                new Ring(),
                new Axe()
        };
    }

    @Override
    public HigherGhost clone() throws CloneNotSupportedException
    {
        return (HigherGhost) super.clone();
    }

    @Override
    public HigherGhost getClearCopy() {
        return new HigherGhost();
    }
}
