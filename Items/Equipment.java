package Items;

import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Armors.Torso;
import Items.Weapons.Choppings.Axe;
import Items.Weapons.Staffs.Staff;
import Items.Weapons.Weapon;
import Items.Weapons.WeaponType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Equipment implements Serializable {

    private Helmet helmet;
    private Torso torso;
    private Ring[] rings = new Ring[10];
    private Weapon oneHandedWeaponLeft;
    private Weapon oneHandedWeaponRight;
    private Weapon twoHandedWeapon;
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

    public ArrayList<Item> getListOfEquipment(){
        ArrayList<Item> itemList = new ArrayList<>(Arrays.asList(torso, helmet, rings[0], rings[1], rings[2], rings[3], rings[4], rings[5], rings[6], rings[7], rings[8], rings[9], oneHandedWeaponLeft, oneHandedWeaponRight, twoHandedWeapon));

        return itemList;
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

    public Item[] getArmor(){
        return new Item[]{torso, helmet};
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

    public void unequip(Item item){
        if(item instanceof Helmet){
            helmet = null;
        }
        if(item instanceof Torso){
            torso = null;
        }
        if (item instanceof Ring){
            int k = 0;
            for(int i = 0; i < rings.length-1; i++){
                if(rings[i] != null && rings[i].getId() == item.getId()){
                    k = 1;
                }
                rings[i] = rings[i+k];
            }
            if(k == 0){
                rings[rings.length-1] = (Ring)item;
            }
        }
        if(item instanceof Weapon){
            if(((Weapon) item).getWeaponType().contains(WeaponType.ONEHANDED)){
                if(item == oneHandedWeaponRight){
                    oneHandedWeaponRight = oneHandedWeaponLeft;
                    oneHandedWeaponLeft = null;
                } else {
                    oneHandedWeaponLeft = null;
                }
            } else {
                twoHandedWeapon = null;
            }
        }
    }
}
