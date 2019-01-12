package Things;

import java.awt.*;

public class Corpse extends Thing {
    public Corpse(int x, int y){
        this.x = x;
        this.y = y;
        name = "Труп";
        color = new Color(50,90, 4);
        isStep = true;
    }
}
