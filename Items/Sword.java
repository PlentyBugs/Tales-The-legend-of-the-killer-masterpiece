package Items;

public class Sword extends Weapon {

    public Sword(Material material, Rarity rarity, Grade grade, int damage, WeaponType weaponType){
        stockName = "меч";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.weaponType = weaponType;
        this.damage = damage;
        quality = 100;

        countProperty();
    }

    public Sword(){
        this(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.ONEHANDED);
    }
}
