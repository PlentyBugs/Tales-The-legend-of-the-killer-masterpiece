package Things;

import java.awt.*;

public class BrickRoad extends Thing{
    public BrickRoad(){
        name = "Дорога";
        color = new Color(150, 76, 65);
        isStep = true;
    }

    public BrickRoad(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
}
