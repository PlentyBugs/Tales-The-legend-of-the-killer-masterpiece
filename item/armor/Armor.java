package item.armor;

import item.Item;
import support.ItemProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Armor extends Item {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Item.propertyList);
        propertyList.add(ItemProperty.ARMOR);
    }
    protected int protection;

    public int getProtection() {
        return (int)(protection*selfForgedBonus);
    }

    public Armor countProperty(){
        int addProtection = 0;
        switch (material) {
            case LEATHER -> addProtection += 1;
            case STUDDEDLEATHER -> addProtection += 4;
            case CHAIN -> addProtection += 10;
            case COPPER -> addProtection += 23;
            case IRON -> addProtection += 36;
            case BRONZE -> addProtection += 54;
            case STEEL -> addProtection += 89;
            case MYTHRIL -> addProtection += 112;
            case ADAMANTINE -> addProtection += 164;
            case ELVENMYTHRIL -> addProtection += 223;
            case CRYSTAL -> addProtection += 378;
            case DEEP -> addProtection += 434;
            case GODSHEART -> addProtection += 579;
            case ABSOLUTEZERO -> addProtection += 700;
        }
        switch (material) {
            case LEATHER -> name = "Кожаный " + stockName;
            case STUDDEDLEATHER -> name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из шипованной кожи";
            case CHAIN -> name = "Кальчужный " + stockName;
            case COPPER -> name = "Кожанный " + stockName;
            case IRON -> name = "Железный " + stockName;
            case BRONZE -> name = "Бронзовый " + stockName;
            case STEEL -> name = "Стальной " + stockName;
            case MYTHRIL -> name = "Мифриловый " + stockName;
            case ADAMANTINE -> name = "Адамантиновый " + stockName;
            case ELVENMYTHRIL -> name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из эльфийского мифрила";
            case CRYSTAL -> name = "Хрустальный " + stockName;
            case DEEP -> name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " бездны";
            case GODSHEART -> name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из сердца бога";
            case ABSOLUTEZERO -> name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " начала и конца";
        }

        switch (grade) {
            case COMMON -> addProtection += 1;
            case MAGIC -> addProtection += 32;
            case CURSE -> addProtection += 73;
            case ARTIFACT -> addProtection += 150;
            case HEROIC -> addProtection += 360;
            case ABOVE_THE_GODS -> addProtection += 700;
        }
        switch (rarity) {
            case COMMON -> addProtection += 1;
            case UNCOMMON -> addProtection += 14;
            case RARE -> addProtection += 45;
            case MYSTICAL -> addProtection += 87;
            case LEGENDARY -> addProtection += 160;
            case DRAGON -> addProtection += 389;
            case DIVINE -> addProtection += 587;
        }

        protection += addProtection;
        return this;
    }

    @Override
    public void countCost(){
        int addCost = 0;

        switch (material) {
            case LEATHER -> addCost += 700;
            case STUDDEDLEATHER -> addCost += 1500;
            case CHAIN -> addCost += 4500;
            case COPPER -> addCost += 9800;
            case IRON -> addCost += 11200;
            case BRONZE -> addCost += 24000;
            case STEEL -> addCost += 38000;
            case MYTHRIL -> addCost += 87000;
            case ADAMANTINE -> addCost += 145000;
            case ELVENMYTHRIL -> addCost += 265000;
            case CRYSTAL -> addCost += 554000;
            case DEEP -> addCost += 870000;
            case GODSHEART -> addCost += 1120000;
            case ABSOLUTEZERO -> addCost += 1345000;
        }
        switch (grade) {
            case COMMON -> addCost += 300;
            case MAGIC -> addCost += 5420;
            case CURSE -> addCost += 18450;
            case ARTIFACT -> addCost += 34500;
            case HEROIC -> addCost += 78540;
            case ABOVE_THE_GODS -> addCost += 1600000;
        }
        switch (rarity) {
            case COMMON -> addCost += 900;
            case UNCOMMON -> addCost += 4550;
            case RARE -> addCost += 11500;
            case MYSTICAL -> addCost += 25000;
            case LEGENDARY -> addCost += 42000;
            case DRAGON -> addCost += 156000;
            case DIVINE -> addCost += 354000;
        }

        cost = addCost;
    }

    @Override
    public String getItemProperty() {
        return Integer.toString(protection);
    }
}
