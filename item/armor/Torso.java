package item.armor;

import item.Grade;
import item.Material;
import item.Rarity;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Torso extends Armor {
    protected final static java.util.List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Armor.propertyList);
    }

    public Torso(Material material, Rarity rarity, Grade grade, int protection){
        stockName = "броня";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.protection = protection;
        quality = 100;
    }

    public Torso(){
        this(Material.LEATHER, Rarity.COMMON, Grade.COMMON, 0);
        name = "броня";
    }
}
