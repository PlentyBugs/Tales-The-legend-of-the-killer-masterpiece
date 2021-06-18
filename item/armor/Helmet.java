package item.armor;

import item.Grade;
import item.Material;
import item.Rarity;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Helmet extends Armor {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Armor.propertyList);
    }

    public Helmet(Material material, Rarity rarity, Grade grade, int protection){
        stockName = "шлем";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.protection = protection;
        quality = 100;
    }

    public Helmet(){
        this(Material.LEATHER, Rarity.COMMON, Grade.COMMON, 0);
        name = "шлем";
    }
}
