package item;

import item.armor.Armor;
import item.armor.Helmet;
import item.armor.Ring;
import item.armor.Torso;
import item.weapon.chop.Axe;
import item.weapon.staff.Staff;
import item.weapon.Weapon;
import item.weapon.WeaponType;
import support.Property;
import support.GeneralProperty;
import support.PropertyProvider;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Equipment implements Serializable, PropertyProvider {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.add(GeneralProperty.EQUIPMENT);
    }

    private Helmet helmet;
    private Torso torso;
    private Ring[] rings = new Ring[10];
    private Weapon oneHandedWeaponLeft;
    private Weapon oneHandedWeaponRight;
    private Weapon twoHandedWeapon;
    @Serial
    private static final long serialVersionUID = -1882217856036727082L;

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
            System.arraycopy(rings, 0, ringsCopy, 1, 9);
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

    public List<Item> getListOfEquipment(){

        return new ArrayList<>(Arrays.asList(torso, helmet, rings[0], rings[1], rings[2], rings[3], rings[4], rings[5], rings[6], rings[7], rings[8], rings[9], oneHandedWeaponLeft, oneHandedWeaponRight, twoHandedWeapon));
    }

    public double getWeaponDamage(){
        double damage = 0;
        Weapon[] weaponList = {oneHandedWeaponLeft, oneHandedWeaponRight, twoHandedWeapon};

        for(Weapon weapon : weaponList){
            if (weapon != null){
                damage += weapon.getDamage();
            }
        }
        return damage;
    }

    public Weapon[] getWeaponList(){
        return new Weapon[]{oneHandedWeaponLeft, oneHandedWeaponRight, twoHandedWeapon};
    }

    public Armor[] getArmor(){
        return new Armor[]{torso, helmet};
    }

    public Ring[] getRings(){
        return new Ring[]{rings[0], rings[1], rings[2], rings[3], rings[4], rings[5], rings[6], rings[7], rings[8], rings[9]};
    }

    public boolean staffEquip(){
        for(Weapon weapon : new Weapon[]{oneHandedWeaponLeft, oneHandedWeaponRight, twoHandedWeapon}){
            if(weapon instanceof Staff){
                return true;
            }
        }
        return false;
    }

    public boolean choppEquip(){
        for(Weapon weapon : new Weapon[]{oneHandedWeaponLeft, oneHandedWeaponRight, twoHandedWeapon}){
            if(weapon instanceof Axe){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Weapon> getStaffs(){
        ArrayList<Weapon> staffs = new ArrayList<>();
        for(Weapon weapon : new Weapon[]{oneHandedWeaponLeft, oneHandedWeaponRight, twoHandedWeapon}){
            if(weapon instanceof Staff){
                staffs.add(weapon);
            }
        }
        return staffs;
    }

    public ArrayList<Weapon> getChopps(){
        ArrayList<Weapon> chopps = new ArrayList<>();
        for(Weapon weapon : new Weapon[]{oneHandedWeaponLeft, oneHandedWeaponRight, twoHandedWeapon}){
            if(weapon instanceof Axe){
                chopps.add(weapon);
            }
        }
        return chopps;
    }

    public void unequip(Item item) {
        if(item instanceof Helmet) {
            helmet = null;
        }
        if(item instanceof Torso) {
            torso = null;
        }
        if (item instanceof Ring ring) {
            int k = 0;
            for(int i = 0; i < rings.length-1; i++){
                if(rings[i] != null && rings[i].getId() == item.getId()){
                    k = 1;
                }
                rings[i] = rings[i+k];
            }
            if(k == 0) {
                rings[rings.length-1] = ring;
            }
        }
        if(item instanceof Weapon weapon) {
            if(weapon.getWeaponType().contains(WeaponType.ONE_HANDED)) {
                if(item == oneHandedWeaponRight) {
                    oneHandedWeaponRight = oneHandedWeaponLeft;
                }
                oneHandedWeaponLeft = null;
            } else {
                twoHandedWeapon = null;
            }
        }
    }
}
