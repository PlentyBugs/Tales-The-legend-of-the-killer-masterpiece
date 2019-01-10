package JGame;

public class Weapon extends Item {
    protected int damage;
    protected WeaponType weaponType;

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public int getDamage() {
        return damage;
    }
}
