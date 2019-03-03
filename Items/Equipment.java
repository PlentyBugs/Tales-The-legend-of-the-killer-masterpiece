package Items;

import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Armors.Torso;
import Items.Weapons.Weapon;

import java.io.Serializable;

public class Equipment implements Serializable {

    private Helmet helmet;
    private Torso torso;
    private Ring[] rings = new Ring[10];
    private Weapon oneHandedWeaponLeft;
    private Weapon oneHandedWeaponRight;
    private Weapon twoHandedWeapon;

    public void setRings(Ring ring){
        boolean unique = true;
        for(Ring ring1 : rings){
            if(ring1 != null && ring1.getId() == ring.getId()){
                unique = false;
                break;
            }
        }
        if(unique){
            Ring[] ringsCopy = new Ring[10];
            for (int s = 1; s < 10; s++){
                ringsCopy[s] = rings[s-1];
            }
            ringsCopy[0] = ring;

            rings = ringsCopy;
        }
    }

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
        Item[] itemList = {torso, helmet, rings[0], rings[1], rings[2], rings[3], rings[4], rings[5], rings[6], rings[7], rings[8], rings[9], oneHandedWeaponLeft, oneHandedWeaponRight, twoHandedWeapon};

        return itemList;
    }

    public int getWeaponDamage(){
        int damage = 0;
        Weapon[] weaponList = {oneHandedWeaponLeft, oneHandedWeaponRight, twoHandedWeapon};

        for(Weapon weapon : weaponList){
            if (weapon != null){
                damage += weapon.getDamage();
            }
        }
        return damage;
    }

    public Weapon[] getWeaponList(){
        Weapon[] weaponList = {oneHandedWeaponLeft, oneHandedWeaponRight, twoHandedWeapon};
        return weaponList;
    }

    public Item[] getArmor(){
        Item[] armor = {torso, helmet, rings[0], rings[1], rings[2], rings[3], rings[4], rings[5], rings[6], rings[7], rings[8], rings[9]};
        return armor;
    }
}
