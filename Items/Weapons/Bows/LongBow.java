package Items.Weapons.Bows;

import Items.Grade;
import Items.Material;
import Items.Rarity;
import Items.Weapons.WeaponType;

public class LongBow extends Bow {

    public LongBow(Material material, Rarity rarity, Grade grade, int damage, WeaponType weaponType){
        stockName = "Длинный лук";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.weaponType.add(weaponType);
        this.damage = damage;
        quality = 100;
    }

    public LongBow(){
        this(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.LONGRANGE);
        stockName = "Длинный лук";
    }

    public LongBow countProperty(){
        int addDamage = 0;


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
            case MAGIC: addDamage += 36; break;
            case CURSE: addDamage += 68; break;
            case ARTIFACT: addDamage += 149; break;
            case HEROIC: addDamage += 397; break;
            case ABOVETHEGODS: addDamage += 687; break;
        }
        switch (rarity){
            case COMMON: addDamage += 1; break;
            case UNCOMMON: addDamage += 25; break;
            case RARE: addDamage += 57; break;
            case MYSTICAL: addDamage += 98; break;
            case LEGENDARY: addDamage += 178; break;
            case DRAGON: addDamage += 394; break;
            case DIVINE: addDamage += 750; break;
        }

        for(WeaponType type : weaponType){
            switch (type){
                case ONEHANDED: addDamage += 0; break;
                case TWOHANDED: addDamage += addDamage; break;
                case LONGRANGE: addDamage += 0; break;
                case POLE: addDamage += 0; break;
                case CHOPPING: addDamage += 0; break;
            }
        }

        damage += addDamage;
        return this;
    }
}
