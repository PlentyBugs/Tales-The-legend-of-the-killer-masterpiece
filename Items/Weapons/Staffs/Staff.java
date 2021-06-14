package Items.Weapons.Staffs;

import Items.Grade;
import Items.Material;
import Items.Rarity;
import Items.Weapons.Weapon;
import Items.Weapons.WeaponType;
import support.ItemProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Staff extends Weapon {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Weapon.propertyList);
        propertyList.add(ItemProperty.STAFF);
    }

    public Staff(Material material, Rarity rarity, Grade grade, int damage, WeaponType weaponType){
        stockName = "посох";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.weaponType.add(weaponType);
        this.weaponType.add(WeaponType.POLE);
        this.damage = damage;
        quality = 100;
    }

    public Staff(){
        this(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.TWO_HANDED);
        name = "посох";
    }

    public Staff countProperty(){
        int addDamage = 0;

        for(WeaponType type : weaponType){
            switch (type){
                case ONE_HANDED: stockName = "Одноручный " + stockName; break;
                case TWO_HANDED: stockName = "Двуручный " + stockName; break;
            }
        }
        switch (material){
            case COPPER: addDamage += 1; break;
            case IRON: addDamage += 3; break;
            case BRONZE: addDamage += 5; break;
            case STEEL: addDamage += 7; break;
            case MYTHRIL: addDamage += 9; break;
            case ADAMANTINE: addDamage += 11; break;
            case ELVENMYTHRIL: addDamage += 13; break;
            case CRYSTAL: addDamage += 15; break;
            case DEEP: addDamage += 17; break;
            case GODSHEART: addDamage += 19; break;
            case ABSOLUTEZERO: addDamage += 21; break;
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
            case MAGIC: addDamage += 3; break;
            case CURSE: addDamage += 5; break;
            case ARTIFACT: addDamage += 7; break;
            case HEROIC: addDamage += 9; break;
            case ABOVE_THE_GODS: addDamage += 11; break;
        }
        switch (rarity){
            case COMMON: addDamage += 1; break;
            case UNCOMMON: addDamage += 3; break;
            case RARE: addDamage += 5; break;
            case MYSTICAL: addDamage += 7; break;
            case LEGENDARY: addDamage += 9; break;
            case DRAGON: addDamage += 11; break;
            case DIVINE: addDamage += 13; break;
        }

        damage += addDamage;
        return this;
    }
}
