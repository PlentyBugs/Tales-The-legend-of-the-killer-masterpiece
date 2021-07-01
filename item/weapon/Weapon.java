package item.weapon;

import creature.LiveCreature;
import item.Item;
import window.battle.FightWindow;
import support.ItemProperty;
import support.Property;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class Weapon extends Item {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Item.propertyList);
        propertyList.add(ItemProperty.WEAPON);
    }
    @Serial
    private static final long serialVersionUID = -704955292525306190L;

    protected int damage;
    protected int bonusDamage = 1;
    protected double enchantBonus = 1;
    protected ArrayList<WeaponType> weaponType = new ArrayList<>();

    public ArrayList<WeaponType> getWeaponType() {
        return weaponType;
    }

    public Weapon setWeaponType(WeaponType weaponType) {
        this.weaponType.add(weaponType);
        return this;
    }

    public double getDamage() {
        return damage*bonusDamage*enchantBonus*selfForgedBonus;
    }

    public Weapon countProperty(){
        int addDamage = 0;

        for(WeaponType type : weaponType){
            switch (type) {
                case ONE_HANDED -> stockName = "Одноручный " + stockName;
                case TWO_HANDED -> stockName = "Двуручный " + stockName;
            }
        }
        switch (material) {
            case COPPER -> addDamage += 1;
            case IRON -> addDamage += 15;
            case BRONZE -> addDamage += 31;
            case STEEL -> addDamage += 43;
            case MYTHRIL -> addDamage += 57;
            case ADAMANTINE -> addDamage += 76;
            case ELVENMYTHRIL -> addDamage += 92;
            case CRYSTAL -> addDamage += 143;
            case DEEP -> addDamage += 276;
            case GODSHEART -> addDamage += 434;
            case ABSOLUTEZERO -> addDamage += 700;
        }
        switch (material) {
            case COPPER -> name = "Медный " + stockName;
            case IRON -> name = "Железный " + stockName;
            case BRONZE -> name = "Бронзовый " + stockName;
            case STEEL -> name = "Стальной " + stockName;
            case MYTHRIL -> name = "Мифриловый " + stockName;
            case ADAMANTINE -> name = "Адамантиновый " + stockName;
            case ELVENMYTHRIL -> name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из эльфийского мифрила";
            case CRYSTAL -> name = "Хрустальный " + stockName;
            case DEEP -> name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " бездны";
            case GODSHEART -> name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из сердца бога";
            case ABSOLUTEZERO -> name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " начала и конца";
        }
        switch (grade) {
            case COMMON -> addDamage += 1;
            case MAGIC -> addDamage += 32;
            case CURSE -> addDamage += 73;
            case ARTIFACT -> addDamage += 150;
            case HEROIC -> addDamage += 360;
            case ABOVE_THE_GODS -> addDamage += 700;
        }
        switch (rarity) {
            case COMMON -> addDamage += 1;
            case UNCOMMON -> addDamage += 14;
            case RARE -> addDamage += 45;
            case MYSTICAL -> addDamage += 87;
            case LEGENDARY -> addDamage += 160;
            case DRAGON -> addDamage += 389;
            case DIVINE -> addDamage += 587;
        }

        for(WeaponType type : weaponType){
            switch (type) {
                case ONE_HANDED, LONG_RANGE, POLE, CHOPPING -> addDamage += 0;
                case TWO_HANDED -> addDamage += addDamage;
            }
        }

        damage += addDamage;
        return this;
    }

    public void weaponSkill(LiveCreature enemy, FightWindow fightWindow, LiveCreature attacker){

    }

    public void addBonusDamage(double bonusDamage){
        this.bonusDamage += bonusDamage;
    }

    public void setBonusDamage(int bonusDamage) {
        this.bonusDamage = bonusDamage;
    }

    public int getBonusDamage() {
        return bonusDamage;
    }

    public void setEnchantBonus(double enchantBonus) {
        this.enchantBonus = enchantBonus;
    }

    public int getClearDamage(){
        return damage;
    }

    @Override
    public void countCost(){
        int addCost = 0;

        switch (material) {
            case LEATHER -> addCost += 700;
            case STUDDEDLEATHER -> addCost += 1500;
            case CHAIN -> addCost += 4500;
            case COPPER -> addCost += 9800;
            case IRON -> addCost += 11200;
            case BRONZE -> addCost += 24000;
            case STEEL -> addCost += 38000;
            case MYTHRIL -> addCost += 87000;
            case ADAMANTINE -> addCost += 145000;
            case ELVENMYTHRIL -> addCost += 265000;
            case CRYSTAL -> addCost += 554000;
            case DEEP -> addCost += 870000;
            case GODSHEART -> addCost += 1120000;
            case ABSOLUTEZERO -> addCost += 1345000;
        }
        switch (grade) {
            case COMMON -> addCost += 300;
            case MAGIC -> addCost += 5420;
            case CURSE -> addCost += 18450;
            case ARTIFACT -> addCost += 34500;
            case HEROIC -> addCost += 78540;
            case ABOVE_THE_GODS -> addCost += 1600000;
        }
        switch (rarity) {
            case COMMON -> addCost += 900;
            case UNCOMMON -> addCost += 4550;
            case RARE -> addCost += 11500;
            case MYSTICAL -> addCost += 25000;
            case LEGENDARY -> addCost += 42000;
            case DRAGON -> addCost += 156000;
            case DIVINE -> addCost += 354000;
        }

        cost = addCost;
    }

    @Override
    public String getItemProperty() {
        return Integer.toString(damage);
    }
}
