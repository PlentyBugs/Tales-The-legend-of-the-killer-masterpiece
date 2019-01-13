package Items;

public class Helmet extends Armor {
    public Helmet(Material material, Rarity rarity, Grade grade, int protection){
        stockName = "шлем";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.protection = protection;
        quality = 100;

        countProperty();
    }

    public Helmet(){
        this(Material.LEATHER, Rarity.COMMON, Grade.COMMON, 0);
    }
}
