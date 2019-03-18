package Items;

import Things.Door;

public class Key extends Item {

    private int level;
    private Door door;

    public Key(){
        super();
        name = "Ключ";
    }

    public Key countProperty(){
        int levelAdd = 0;

        switch (material){
            case COPPER: levelAdd += 1; break;
            case IRON: levelAdd += 15; break;
            case BRONZE: levelAdd += 31; break;
            case STEEL: levelAdd += 43; break;
            case MYTHRIL: levelAdd += 57; break;
            case ADAMANTINE: levelAdd += 76; break;
            case ELVENMYTHRIL: levelAdd += 92; break;
            case CRYSTAL: levelAdd += 143; break;
            case DEEP: levelAdd += 276; break;
            case GODSHEART: levelAdd += 434; break;
            case ABSOLUTEZERO: levelAdd += 700; break;
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
            case MAGIC: levelAdd += 32; break;
            case CURSE: levelAdd += 73; break;
            case ARTIFACT: levelAdd += 150; break;
            case HEROIC: levelAdd += 360; break;
            case ABOVETHEGODS: levelAdd += 700; break;
        }
        switch (rarity){
            case COMMON: levelAdd += 1; break;
            case UNCOMMON: levelAdd += 14; break;
            case RARE: levelAdd += 45; break;
            case MYSTICAL: levelAdd += 87; break;
            case LEGENDARY: levelAdd += 160; break;
            case DRAGON: levelAdd += 389; break;
            case DIVINE: levelAdd += 587; break;
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
