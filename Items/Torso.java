package Items;

public class Torso extends Armor {
    public Torso(Material material, Rarity rarity, Grade grade, int protection){
        stockName = "броня";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.protection = protection;
        quality = 100;

        countProperty();
    }

    public Torso(){
        this(Material.LEATHER, Rarity.COMMON, Grade.COMMON, 0);
    }
}
