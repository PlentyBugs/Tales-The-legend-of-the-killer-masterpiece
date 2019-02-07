package Items.Armors;

import Items.Grade;
import Items.Material;
import Items.Rarity;

public class Ring extends Armor {
    public Ring(Material material, Rarity rarity, Grade grade, int protection){
        stockName = "кольцо";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.protection = protection;
        quality = 100;
    }

    public Ring(){
        this(Material.IRON, Rarity.COMMON, Grade.COMMON, 0);
        name = "кольцо";
    }

    public void countProperty(){
        int addProtection = 0;
        switch (material){
            case LEATHER: addProtection += 1; break;
            case STUDDEDLEATHER: addProtection += 4; break;
            case CHAIN: addProtection += 7; break;
            case COPPER: addProtection += 15; break;
            case IRON: addProtection += 23; break;
            case BRONZE: addProtection += 35; break;
            case STEEL: addProtection += 47; break;
            case MYTHRIL: addProtection += 56; break;
            case ADAMANTINE: addProtection += 86; break;
            case ELVENMYTHRIL: addProtection += 153; break;
            case CRYSTAL: addProtection += 196; break;
            case DEEP: addProtection += 215; break;
            case GODSHEART: addProtection += 321; break;
            case ABSOLUTEZERO: addProtection += 422; break;
        }
        switch (material){
            case LEATHER: name = "Кожаное " + stockName; break;
            case STUDDEDLEATHER: name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из шипованной кожи"; break;
            case CHAIN: name = "Кальчужное " + stockName; break;
            case COPPER: name = "Кожанное " + stockName; break;
            case IRON: name = "Железное " + stockName; break;
            case BRONZE: name = "Бронзовое " + stockName; break;
            case STEEL: name = "Стальное " + stockName; break;
            case MYTHRIL: name = "Мифриловое " + stockName; break;
            case ADAMANTINE: name = "Адамантиновое " + stockName; break;
            case ELVENMYTHRIL: name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из эльфийского мифрила"; break;
            case CRYSTAL: name = "Хрустальное " + stockName; break;
            case DEEP: name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " бездны"; break;
            case GODSHEART: name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из сердца бога"; break;
            case ABSOLUTEZERO: name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " начала и конца"; break;
        }

        switch (grade){
            case COMMON: addProtection += 1; break;
            case MAGIC: addProtection += 12; break;
            case CURSE: addProtection += 34; break;
            case ARTIFACT: addProtection += 68; break;
            case HEROIC: addProtection += 103; break;
            case ABOVETHEGODS: addProtection += 203; break;
        }
        switch (rarity){
            case COMMON: addProtection += 1; break;
            case UNCOMMON: addProtection += 5; break;
            case RARE: addProtection += 12; break;
            case MYSTICAL: addProtection += 35; break;
            case LEGENDARY: addProtection += 56; break;
            case DRAGON: addProtection += 78; break;
            case DIVINE: addProtection += 120; break;
        }

        protection += addProtection;
    }
}
