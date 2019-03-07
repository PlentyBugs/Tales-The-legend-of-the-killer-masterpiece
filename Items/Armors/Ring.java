package Items.Armors;

import Creatures.StatsEnum;
import Items.Grade;
import Items.Material;
import Items.Rarity;
import Items.StatItem;

public class Ring extends Armor implements StatItem {
    private StatsEnum stat;
    private int statPower;
    private static final long serialVersionUID = 4946412995122385646L;

    public Ring(Material material, Rarity rarity, Grade grade, StatsEnum statsEnum){
        stockName = "кольцо";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.stat = statsEnum;
        quality = 100;
        this.protection = 0;
    }

    public Ring(){
        this(Material.IRON, Rarity.COMMON, Grade.COMMON, StatsEnum.STRENGTH);
        name = "кольцо";
    }

    public void countProperty(){
        statPower = 0;
        switch (material){
            case LEATHER: statPower += 1; break;
            case STUDDEDLEATHER: statPower += 3; break;
            case CHAIN: statPower += 6; break;
            case COPPER: statPower += 11; break;
            case IRON: statPower += 16; break;
            case BRONZE: statPower += 23; break;
            case STEEL: statPower += 36; break;
            case MYTHRIL: statPower += 41; break;
            case ADAMANTINE: statPower += 56; break;
            case ELVENMYTHRIL: statPower += 87; break;
            case CRYSTAL: statPower += 122; break;
            case DEEP: statPower += 167; break;
            case GODSHEART: statPower += 243; break;
            case ABSOLUTEZERO: statPower += 312; break;
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
            case COMMON: statPower += 1; break;
            case MAGIC: statPower += 9; break;
            case CURSE: statPower += 24; break;
            case ARTIFACT: statPower += 36; break;
            case HEROIC: statPower += 62; break;
            case ABOVETHEGODS: statPower += 122; break;
        }
        switch (rarity){
            case COMMON: statPower += 1; break;
            case UNCOMMON: statPower += 5; break;
            case RARE: statPower += 21; break;
            case MYSTICAL: statPower += 35; break;
            case LEGENDARY: statPower += 56; break;
            case DRAGON: statPower += 78; break;
            case DIVINE: statPower += 120; break;
        }
    }

    @Override
    public StatsEnum getStat() {
        return stat;
    }

    public void setStat(StatsEnum stat) {
        this.stat = stat;
    }

    @Override
    public int getStatPower(){
        countProperty();
        return statPower;
    }
}
