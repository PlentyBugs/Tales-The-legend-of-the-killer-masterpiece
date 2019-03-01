package Things.ChestLike;

import java.awt.*;

public class Corpse extends Chest {
    public Corpse(){
        this(0,0);
    }
    public Corpse(int x, int y){
        this.x = x;
        this.y = y;
        name = "Труп";
        color = new Color(50,90, 4);
        isStep = true;
    }
    @Override
    public Corpse getClearCopy() {
        return new Corpse();
    }
}
