package Things;

import java.awt.*;

public class House extends Thing{
    public House(){
        name = "Дом";
        color = new Color(170, 15, 4);
        isStep = false;
    }

    public House(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }
}
