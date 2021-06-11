package Items.Armors;

import Creatures.StatsEnum;
import Items.Grade;
import Items.Material;
import Items.Rarity;
import Items.StatItem;
import support.Property;
import support.GeneralProperty;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Ring extends Armor implements StatItem  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Armor.propertyList);
        propertyList.add(GeneralProperty.RING);
    }
    private StatsEnum stat;
    private int statPower;
    @Serial
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

    public Ring countProperty(){
        statPower = 0;
        switch (material) {
            case LEATHER -> statPower += 1;
            case STUDDEDLEATHER -> statPower += 3;
            case CHAIN -> statPower += 6;
            case COPPER -> statPower += 11;
            case IRON -> statPower += 16;
            case BRONZE -> statPower += 23;
            case STEEL -> statPower += 36;
            case MYTHRIL -> statPower += 41;
            case ADAMANTINE -> statPower += 56;
            case ELVENMYTHRIL -> statPower += 87;
            case CRYSTAL -> statPower += 122;
            case DEEP -> statPower += 167;
            case GODSHEART -> statPower += 243;
            case ABSOLUTEZERO -> statPower += 312;
        }
        switch (material) {
            case LEATHER -> name = "Кожаное " + stockName;
            case STUDDEDLEATHER -> name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из шипованной кожи";
            case CHAIN -> name = "Кальчужное " + stockName;
            case COPPER -> name = "Кожанное " + stockName;
            case IRON -> name = "Железное " + stockName;
            case BRONZE -> name = "Бронзовое " + stockName;
            case STEEL -> name = "Стальное " + stockName;
            case MYTHRIL -> name = "Мифриловое " + stockName;
            case ADAMANTINE -> name = "Адамантиновое " + stockName;
            case ELVENMYTHRIL -> name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из эльфийского мифрила";
            case CRYSTAL -> name = "Хрустальное " + stockName;
            case DEEP -> name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " бездны";
            case GODSHEART -> name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из сердца бога";
            case ABSOLUTEZERO -> name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " начала и конца";
        }

        switch (grade) {
            case COMMON -> statPower += 1;
            case MAGIC -> statPower += 9;
            case CURSE -> statPower += 24;
            case ARTIFACT -> statPower += 36;
            case HEROIC -> statPower += 62;
            case ABOVETHEGODS -> statPower += 122;
        }
        switch (rarity) {
            case COMMON -> statPower += 1;
            case UNCOMMON -> statPower += 5;
            case RARE -> statPower += 21;
            case MYSTICAL -> statPower += 35;
            case LEGENDARY -> statPower += 56;
            case DRAGON -> statPower += 78;
            case DIVINE -> statPower += 120;
        }
        return this;
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
