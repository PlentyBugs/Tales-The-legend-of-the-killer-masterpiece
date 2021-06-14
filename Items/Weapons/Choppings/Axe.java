package Items.Weapons.Choppings;

import Abilities.Buffs.Bleeding;
import Creatures.LiveCreature;
import Items.Grade;
import Items.Material;
import Items.Rarity;
import Items.Weapons.Weapon;
import Items.Weapons.WeaponType;
import Windows.BattleWindows.FightWindow;
import support.ItemProperty;
import support.Property;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Axe extends Weapon {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Weapon.propertyList);
        propertyList.add(ItemProperty.AXE);
    }

    @Serial
    private static final long serialVersionUID = 5336683018159383002L;

    public Axe(Material material, Rarity rarity, Grade grade, int damage, WeaponType weaponType){
        stockName = "топор";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.weaponType.add(weaponType);
        this.weaponType.add(WeaponType.CHOPPING);
        this.damage = damage;
        quality = 100;
    }

    public Axe(){
        this(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.ONE_HANDED);
        name = "топор";
    }

    public Axe countProperty(){
        int addDamage = 0;

        for(WeaponType type : weaponType){
            switch (type){
                case ONE_HANDED: stockName = "Одноручный " + stockName; break;
                case TWO_HANDED: stockName = "Двуручный " + stockName; break;
            }
        }
        switch (material){
            case COPPER: addDamage += 1; break;
            case IRON: addDamage += 12; break;
            case BRONZE: addDamage += 24; break;
            case STEEL: addDamage += 34; break;
            case MYTHRIL: addDamage += 48; break;
            case ADAMANTINE: addDamage += 67; break;
            case ELVENMYTHRIL: addDamage += 89; break;
            case CRYSTAL: addDamage += 124; break;
            case DEEP: addDamage += 248; break;
            case GODSHEART: addDamage += 412; break;
            case ABSOLUTEZERO: addDamage += 635; break;
        }
        switch (material){
            case COPPER: name = "Медный " + stockName; break;
            case IRON: name = "Железный " + stockName; break;
            case BRONZE: name = "Бронзовый " + stockName; break;
            case STEEL: name = "Стальной " + stockName; break;
            case MYTHRIL: name = "Мифриловый " + stockName; break;
            case ADAMANTINE: name = "Адамантиновый " + stockName; break;
            case ELVENMYTHRIL: name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из эльфийского мифрила"; break;
            case CRYSTAL: name = "Хрустальный " + stockName; break;
            case DEEP: name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " бездны"; break;
            case GODSHEART: name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из сердца бога"; break;
            case ABSOLUTEZERO: name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " начала и конца"; break;
        }
        switch (grade){
            case COMMON: addDamage += 1; break;
            case MAGIC: addDamage += 34; break;
            case CURSE: addDamage += 58; break;
            case ARTIFACT: addDamage += 136; break;
            case HEROIC: addDamage += 333; break;
            case ABOVE_THE_GODS: addDamage += 645; break;
        }
        switch (rarity){
            case COMMON: addDamage += 1; break;
            case UNCOMMON: addDamage += 12; break;
            case RARE: addDamage += 32; break;
            case MYSTICAL: addDamage += 64; break;
            case LEGENDARY: addDamage += 124; break;
            case DRAGON: addDamage += 341; break;
            case DIVINE: addDamage += 497; break;
        }
        damage += addDamage;
        return this;
    }
    @Override
    public void weaponSkill(LiveCreature enemy, FightWindow fightWindow, LiveCreature attacker){
        cut(enemy, fightWindow);
    }

    public void cut(LiveCreature enemy, FightWindow fightWindow){
        Bleeding bleeding = new Bleeding((int)(getDamage()/5));
        bleeding.setFightWindow(fightWindow);
        enemy.addBuffs(bleeding);
    }
}
