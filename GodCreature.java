package JGame;

import java.awt.*;

public class GodCreature {
    protected String name;
    protected Color color;
    protected boolean isStep;
    protected int x;
    protected int y;
    protected boolean isPlayer = false;

    public String getName(){
        return name;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public boolean getIsStep(){
        return isStep;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setIsPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    public boolean getIsPlayer(){
        return isPlayer;
    }
}
