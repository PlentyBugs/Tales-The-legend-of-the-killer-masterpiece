package Things.Doors;

import java.awt.*;

public class CaveDoor extends Door{

    public CaveDoor(){
        this(0,0);
    }

    public CaveDoor(int x, int y){
        this.x = x;
        this.y = y;
        isLocked = false;
        name = "Вход в Пещеру";
        color = new Color(100, 100, 97);
        isStep = true;
    }
}