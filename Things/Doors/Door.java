package Things.Doors;

import Items.Key;
import Locations.Generation;
import Locations.Map;
import Things.Thing;

import java.awt.*;

public class Door extends Thing {

    protected boolean isLocked;
    protected Key key;
    protected Map in;
    protected Map out;
    protected Generation generation;

    public Door(){
        name = "Дверь";
        color = new Color(142, 38, 0);
        isStep = false;
        isLocked = false;
    }

    public Door(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    @Override
    public Door getClearCopy() {
        return new Door();
    }

    public boolean getIsLocked(){
        return isLocked;
    }

    public Door setIsLocked(boolean isLocked){
        this.isLocked = isLocked;
        return this;
    }

    public Key getKey() {
        return key;
    }

    public Door setKey(Key key) {
        this.key = key;
        return this;
    }

    public Map getIn() {
        return in;
    }

    public Map getOut() {
        return out;
    }

    public Door setIn(Map in) {
        this.in = in;
        return this;
    }

    public Door setOut(Map out) {
        this.out = out;
        return this;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public Map generate(){
        return generation.generate();
    }
}
