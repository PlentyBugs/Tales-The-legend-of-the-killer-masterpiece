package Things;

import java.awt.*;

public class Stone extends Thing {
    public Stone(){
        name = "Камень";
        color = Color.LIGHT_GRAY;
        isStep = true;
    }
    @Override
    public Stone getClearCopy() {
        return new Stone();
    }
}
