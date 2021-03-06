package item.weapon.bow;

import item.Grade;
import item.Material;
import item.Rarity;
import item.weapon.WeaponType;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class ShortBow extends Bow {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Bow.propertyList);
    }

    public ShortBow(Material material, Rarity rarity, Grade grade, int damage, WeaponType weaponType){
        stockName = "Короткий лук";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.weaponType.add(weaponType);
        this.damage = damage;
        quality = 100;
    }

    public ShortBow(){
        this(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.LONG_RANGE);
        name = "Короткий лук";
    }

    public ShortBow countProperty(){
        int addDamage = 0;

        stockName = "Короткий лук";

        switch (material){
            case COPPER: addDamage += 1; break;
            case IRON: addDamage += 4; break;
            case BRONZE: addDamage += 12; break;
            case STEEL: addDamage += 18; break;
            case MYTHRIL: addDamage += 27; break;
            case ADAMANTINE: addDamage += 35; break;
            case ELVENMYTHRIL: addDamage += 45; break;
            case CRYSTAL: addDamage += 78; break;
            case DEEP: addDamage += 96; break;
            case GODSHEART: addDamage += 134; break;
            case ABSOLUTEZERO: addDamage += 215; break;
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
            case MAGIC: addDamage += 21; break;
            case CURSE: addDamage += 45; break;
            case ARTIFACT: addDamage += 96; break;
            case HEROIC: addDamage += 278; break;
            case ABOVE_THE_GODS: addDamage += 468; break;
        }
        switch (rarity){
            case COMMON: addDamage += 1; break;
            case UNCOMMON: addDamage += 14; break;
            case RARE: addDamage += 34; break;
            case MYSTICAL: addDamage += 65; break;
            case LEGENDARY: addDamage += 120; break;
            case DRAGON: addDamage += 286; break;
            case DIVINE: addDamage += 468; break;
        }

        for(WeaponType type : weaponType){
            switch (type){
                case ONE_HANDED: addDamage += 0; break;
                case TWO_HANDED: addDamage += addDamage; break;
                case LONG_RANGE: addDamage += 0; break;
                case POLE: addDamage += 0; break;
                case CHOPPING: addDamage += 0; break;
            }
        }

        damage += addDamage;
        return this;
    }
}
