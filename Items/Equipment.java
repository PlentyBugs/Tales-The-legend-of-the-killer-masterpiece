package JGame.Items;

import JGame.Items.Helmet;
import JGame.Items.Item;
import JGame.Items.Torso;
import JGame.Items.Weapon;

public class Equipment {

    private Helmet helmet;
    private Torso torso;
    private Weapon oneHandedWeaponLeft;
    private Weapon oneHandedWeaponRight;
    private Weapon twoHandedWeapon;

    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }

    public void setTorso(Torso torso) {
        this.torso = torso;
    }

    public void setOneHandedWeaponLeft(Weapon oneHandedWeaponLeft) {
        this.oneHandedWeaponLeft = oneHandedWeaponLeft;
    }

    public void setOneHandedWeaponRight(Weapon oneHandedWeaponRight) {
        this.oneHandedWeaponRight = oneHandedWeaponRight;
    }

    public void setTwoHandedWeapon(Weapon twoHandedWeapon) {
        this.twoHandedWeapon = twoHandedWeapon;
    }

    public Helmet getHelmet() {
        return helmet;
    }

    public Torso getTorso() {
        return torso;
    }

    public Weapon getOneHandedWeaponLeft() {
        return oneHandedWeaponLeft;
    }

    public Weapon getOneHandedWeaponRight() {
        return oneHandedWeaponRight;
    }

    public Weapon getTwoHandedWeapon() {
        return twoHandedWeapon;
    }

    public Item[] getListOfEquipment(){
        Item[] itemList = {torso, helmet, oneHandedWeaponLeft, oneHandedWeaponRight, twoHandedWeapon};

        return itemList;
    }

    public int getWeaponDamage(){
        int damage = 0;
        Weapon[] weaponList = {oneHandedWeaponLeft, oneHandedWeaponRight, twoHandedWeapon};

        for(Weapon weapon : weaponList){
            if (weapon != null){
                damage += weapon.damage;
            }
        }
        return damage;
    }
}
