package Items.Weapons.Swords;

import Items.*;
import Items.Weapons.Weapon;
import Items.Weapons.WeaponType;

public class Sword extends Weapon {

    public Sword(Material material, Rarity rarity, Grade grade, int damage, WeaponType weaponType){
        stockName = "меч";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.weaponType.add(weaponType);
        this.damage = damage;
        quality = 100;
    }

    public Sword(){
        this(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.ONEHANDED);
        name = "меч";
    }
}
