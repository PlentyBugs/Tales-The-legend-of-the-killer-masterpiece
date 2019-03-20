package Items;

import Things.Door;

public class Key extends Item {

    private int level;
    private Door door;

    public Key(){
        this(Material.COPPER, Rarity.COMMON, Grade.COMMON);
        name = "Ключ";
    }

    public Key(Material material, Rarity rarity, Grade grade){
        stockName = "Ключ";
        this.material = material;
        this.rarity = rarity;
        this.grade = grade;
        quality = 100;
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
        switch (material){
            case COPPER: name = "Медный " + stockName; break;
            case IRON: name = "Железный " + stockName; break;
            case BRONZE: name = "Бронзовый " + stockName; break;
            case STEEL: name = "Стальной " + stockName; break;
            case MYTHRIL: name = "Мифриловый " + stockName; break;
            case ADAMANTINE: name = "Адамантиновый " + stockName; break;
            case ELVENMYTHRIL: name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из эльфийского мифрила"; break;
            case CRYSTAL: name = "Хрустальный " + stockName; break;
            case DEEP: name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " бездны"; break;
            case GODSHEART: name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " из сердца бога"; break;
            case ABSOLUTEZERO: name = stockName.substring(0, 1).toUpperCase() + stockName.substring(1) + " начала и конца"; break;
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
}
