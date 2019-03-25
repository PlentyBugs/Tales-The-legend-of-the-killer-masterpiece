package Items.Armors;

import Items.Item;

public class Armor extends Item {
    protected int protection;

    public int getProtection() {
        return (int)(protection*selfForgedBonus);
    }

    public Armor countProperty(){
        int addProtection = 0;
        switch (material){
            case LEATHER: addProtection += 1; break;
            case STUDDEDLEATHER: addProtection += 4; break;
            case CHAIN: addProtection += 10; break;
            case COPPER: addProtection += 23; break;
            case IRON: addProtection += 36; break;
            case BRONZE: addProtection += 54; break;
            case STEEL: addProtection += 89; break;
            case MYTHRIL: addProtection += 112; break;
            case ADAMANTINE: addProtection += 164; break;
            case ELVENMYTHRIL: addProtection += 223; break;
            case CRYSTAL: addProtection += 378; break;
            case DEEP: addProtection += 434; break;
            case GODSHEART: addProtection += 579; break;
            case ABSOLUTEZERO: addProtection += 700; break;
        }
        switch (material){
            case LEATHER: name = "Кожаный " + stockName; break;
            case STUDDEDLEATHER: name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из шипованной кожи"; break;
            case CHAIN: name = "Кальчужный " + stockName; break;
            case COPPER: name = "Кожанный " + stockName; break;
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
            case COMMON: addProtection += 1; break;
            case MAGIC: addProtection += 32; break;
            case CURSE: addProtection += 73; break;
            case ARTIFACT: addProtection += 150; break;
            case HEROIC: addProtection += 360; break;
            case ABOVETHEGODS: addProtection += 700; break;
        }
        switch (rarity){
            case COMMON: addProtection += 1; break;
            case UNCOMMON: addProtection += 14; break;
            case RARE: addProtection += 45; break;
            case MYSTICAL: addProtection += 87; break;
            case LEGENDARY: addProtection += 160; break;
            case DRAGON: addProtection += 389; break;
            case DIVINE: addProtection += 587; break;
        }

        protection += addProtection;
        return this;
    }

    @Override
    public void countCost(){
        int addCost = 0;

        switch (material){
            case LEATHER: addCost += 700; break;
            case STUDDEDLEATHER: addCost += 1500; break;
            case CHAIN: addCost += 4500; break;
            case COPPER: addCost += 9800; break;
            case IRON: addCost += 11200; break;
            case BRONZE: addCost += 24000; break;
            case STEEL: addCost += 38000; break;
            case MYTHRIL: addCost += 87000; break;
            case ADAMANTINE: addCost += 145000; break;
            case ELVENMYTHRIL: addCost += 265000; break;
            case CRYSTAL: addCost += 554000; break;
            case DEEP: addCost += 870000; break;
            case GODSHEART: addCost += 1120000; break;
            case ABSOLUTEZERO: addCost += 1345000; break;
        }
        switch (grade){
            case COMMON: addCost += 300; break;
            case MAGIC: addCost += 5420; break;
            case CURSE: addCost += 18450; break;
            case ARTIFACT: addCost += 34500; break;
            case HEROIC: addCost += 78540; break;
            case ABOVETHEGODS: addCost += 1600000; break;
        }
        switch (rarity){
            case COMMON: addCost += 900; break;
            case UNCOMMON: addCost += 4550; break;
            case RARE: addCost += 11500; break;
            case MYSTICAL: addCost += 25000; break;
            case LEGENDARY: addCost += 42000; break;
            case DRAGON: addCost += 156000; break;
            case DIVINE: addCost += 354000; break;
        }

        cost = addCost;
    }
}
