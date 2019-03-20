package Items;

import Things.Door;

public class Key extends Item {

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

        switch (material){
            case COPPER: levelAdd += 1; break;
            case IRON: levelAdd += 2; break;
            case BRONZE: levelAdd += 3; break;
            case STEEL: levelAdd += 4; break;
            case MYTHRIL: levelAdd += 5; break;
            case ADAMANTINE: levelAdd += 6; break;
            case ELVENMYTHRIL: levelAdd += 7; break;
            case CRYSTAL: levelAdd += 8; break;
            case DEEP: levelAdd += 9; break;
            case GODSHEART: levelAdd += 10; break;
            case ABSOLUTEZERO: levelAdd += 11; break;
        }
        switch (grade){
            case COMMON: levelAdd += 1; break;
            case MAGIC: levelAdd += 2; break;
            case CURSE: levelAdd += 3; break;
            case ARTIFACT: levelAdd += 4; break;
            case HEROIC: levelAdd += 5; break;
            case ABOVETHEGODS: levelAdd += 6; break;
        }
        switch (rarity){
            case COMMON: levelAdd += 1; break;
            case UNCOMMON: levelAdd += 2; break;
            case RARE: levelAdd += 3; break;
            case MYSTICAL: levelAdd += 4; break;
            case LEGENDARY: levelAdd += 5; break;
            case DRAGON: levelAdd += 6; break;
            case DIVINE: levelAdd += 7; break;
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

    @Override
    public int compareTo(Object o) {
        if(o.getClass() != getClass() || door != ((Key)o).getDoor()){
            return -1;
        }
        if(door == ((Key)o).getDoor()){
            if(level == ((Key)o).getLevel()){
                return 0;
            }
            if(level >= ((Key)o).getLevel()){
                return 1;
            }
            if(level <= ((Key)o).getLevel()){
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
