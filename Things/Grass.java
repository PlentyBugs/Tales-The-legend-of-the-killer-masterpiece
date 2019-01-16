package Things;

import java.awt.*;

public class Grass extends Thing {
    public Grass(){
        name = "Трава";
        color = Color.GREEN;
        isStep = true;
    }

    public Grass(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
}
