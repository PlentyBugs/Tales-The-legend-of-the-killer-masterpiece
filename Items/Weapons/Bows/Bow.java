package Items.Weapons.Bows;

import Items.*;
import Items.Weapons.Weapon;
import Items.Weapons.WeaponType;

public class Bow extends Weapon {
    public Bow(Material material, Rarity rarity, Grade grade, int damage, WeaponType weaponType){
        stockName = "Лук";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        this.weaponType.add(weaponType);
        this.damage = damage;
        quality = 100;
    }

    public Bow(){
        this(Material.COPPER, Rarity.COMMON, Grade.COMMON, 0, WeaponType.LONGRANGE);
        name = "Лук";
    }
}
