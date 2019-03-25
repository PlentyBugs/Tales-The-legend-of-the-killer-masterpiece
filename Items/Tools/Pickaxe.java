package Items.Tools;

import Items.Grade;
import Items.Material;
import Items.Rarity;

public class Pickaxe extends Tool {

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