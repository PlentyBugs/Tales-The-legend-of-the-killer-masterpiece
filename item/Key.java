package item;

import thing.door.Door;
import support.ItemProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Key extends Item  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Item.propertyList);
        propertyList.add(ItemProperty.KEY);
    }

    private int level;
    private Door door;
    private boolean unique;

    public Key(){
        this(Material.COPPER, Rarity.COMMON, Grade.COMMON);
        name = "Ключ";
    }

    public Key(Material material, Rarity rarity, Grade grade){
        super();
        stockName = "Ключ";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        quality = 100;
        unique = false;
    }

    public Key countProperty(){
        int levelAdd = 0;

        switch (material) {
            case COPPER -> levelAdd += 1;
            case IRON -> levelAdd += 2;
            case BRONZE -> levelAdd += 3;
            case STEEL -> levelAdd += 4;
            case MYTHRIL -> levelAdd += 5;
            case ADAMANTINE -> levelAdd += 6;
            case ELVENMYTHRIL -> levelAdd += 7;
            case CRYSTAL -> levelAdd += 8;
            case DEEP -> levelAdd += 9;
            case GODSHEART -> levelAdd += 10;
            case ABSOLUTEZERO -> levelAdd += 11;
        }
        switch (grade) {
            case COMMON -> levelAdd += 1;
            case MAGIC -> levelAdd += 2;
            case CURSE -> levelAdd += 3;
            case ARTIFACT -> levelAdd += 4;
            case HEROIC -> levelAdd += 5;
            case ABOVE_THE_GODS -> levelAdd += 6;
        }
        switch (rarity) {
            case COMMON -> levelAdd += 1;
            case UNCOMMON -> levelAdd += 2;
            case RARE -> levelAdd += 3;
            case MYSTICAL -> levelAdd += 4;
            case LEGENDARY -> levelAdd += 5;
            case DRAGON -> levelAdd += 6;
            case DIVINE -> levelAdd += 7;
        }

        level += levelAdd;
        return this;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int compareTo(Key o) {
        if(door == o.getDoor()){
            if(level == o.getLevel()){
                return 0;
            }
            if(level >= o.getLevel()){
                return 1;
            }
            if(level <= o.getLevel()){
                return -1;
            }
        }
        return -1;
    }

    public Key setGrade(Grade grade) {
        this.grade = grade;
        return this;
    }

    public Key setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Key setName(String name) {
        this.name = name;
        return this;
    }

    public Key setQuality(double quality) {
        this.quality = quality;
        return this;
    }

    public Key setRarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public boolean getUnique(){
        return unique;
    }

    public Key setUnique(boolean unique){
        this.unique = unique;
        return this;
    }
}
