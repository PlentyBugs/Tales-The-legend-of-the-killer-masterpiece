package Items.Armors;

import Items.Grade;
import Items.Material;
import Items.Rarity;

public class Helmet extends Armor {
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
