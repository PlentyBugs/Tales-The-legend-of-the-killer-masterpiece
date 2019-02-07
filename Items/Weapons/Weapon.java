package Items.Weapons;

import Items.Item;

import java.util.ArrayList;

public class Weapon extends Item {
    protected int damage;
    protected ArrayList<WeaponType> weaponType = new ArrayList<>();

    public ArrayList<WeaponType> getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType.add(weaponType);;
    }

    public int getDamage() {
        return damage;
    }

    public void countProperty(){
        int addDamage = 0;

        for(WeaponType type : weaponType){
            switch (type){
                case ONEHANDED: stockName = "Одноручный " + stockName; break;
                case TWOHANDED: stockName = "Двуручный " + stockName; break;
            }
        }
        switch (material){
            case COPPER: addDamage += 1; break;
            case IRON: addDamage += 15; break;
            case BRONZE: addDamage += 31; break;
            case STEEL: addDamage += 43; break;
            case MYTHRIL: addDamage += 57; break;
            case ADAMANTINE: addDamage += 76; break;
            case ELVENMYTHRIL: addDamage += 92; break;
            case CRYSTAL: addDamage += 143; break;
            case DEEP: addDamage += 276; break;
            case GODSHEART: addDamage += 434; break;
            case ABSOLUTEZERO: addDamage += 700; break;
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
            case MAGIC: addDamage += 32; break;
            case CURSE: addDamage += 73; break;
            case ARTIFACT: addDamage += 150; break;
            case HEROIC: addDamage += 360; break;
            case ABOVETHEGODS: addDamage += 700; break;
        }
        switch (rarity){
            case COMMON: addDamage += 1; break;
            case UNCOMMON: addDamage += 14; break;
            case RARE: addDamage += 45; break;
            case MYSTICAL: addDamage += 87; break;
            case LEGENDARY: addDamage += 160; break;
            case DRAGON: addDamage += 389; break;
            case DIVINE: addDamage += 587; break;
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
    }
}
