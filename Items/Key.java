package Items;

import Things.Door;

public class Key extends Item {

    private int level;
    private Door door;

    public Key(){
        super();
        name = "Ключ";
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
        if(o.getClass() != getClass()){
            return 1;
        }
        if(level == ((Key)o).getLevel() && door == ((Key)o).getDoor()){
            return 0;
        }
        return -1;
    }
}
