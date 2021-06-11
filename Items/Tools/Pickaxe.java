package Items.Tools;

import Items.Grade;
import Items.Material;
import Items.Rarity;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Pickaxe extends Tool  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Tool.propertyList);
    }

    public Pickaxe(Material material, Rarity rarity, Grade grade){
        stockName = "Кирка";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        quality = 100;
    }

    public Pickaxe(){
        this(Material.COPPER, Rarity.COMMON, Grade.COMMON);
        name = "Кирка";
    }
}