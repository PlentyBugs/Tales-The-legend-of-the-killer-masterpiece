package Items;

public class Weapon extends Item {
    protected int damage;
    protected WeaponType weaponType;

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public int getDamage() {
        return damage;
    }

    public void countProperty(){
        int addDamage = 0;
        switch (material){
            case COPPER: addDamage += 1; break;
            case IRON: addDamage += 4; break;
            case BRONZE: addDamage += 10; break;
            case STEEL: addDamage += 15; break;
            case MYTHRIL: addDamage += 25; break;
            case ADAMANTINE: addDamage += 50; break;
            case ELVENMYTHRIL: addDamage += 78; break;
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

        damage += addDamage;
    }
}
