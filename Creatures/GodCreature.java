package Creatures;

import Windows.BattleWindows.ChooseEnemyWindow;
import Windows.FieldWindow;

import java.awt.*;
import java.io.Serializable;

public class GodCreature implements Serializable, Cloneable, Copying  {
    protected String name;
    protected Color color;
    protected boolean isStep;
    protected int x;
    protected int y;
    protected boolean isPlayer = false;
    private ChooseEnemyWindow chooseEnemyWindow;
    private boolean isChooseEnemyWindowOpen;

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

    public void setChooseEnemyWindow(Player player, FieldWindow fieldWindow, LiveCreature liveCreature){
        chooseEnemyWindow = new ChooseEnemyWindow(player, fieldWindow, liveCreature);
    }

    public void setChooseEnemyWindowOpen(boolean isChooseEnemyWindowOpen) {
        this.isChooseEnemyWindowOpen = isChooseEnemyWindowOpen;
    }

    public boolean getIsChooseEnemyWindowOpen() {
        return isChooseEnemyWindowOpen;
    }

    public void setChooseEnemyWindowIsVisible(boolean isVisible) {
        chooseEnemyWindow.setIsVisible(isVisible);
    }

    public ChooseEnemyWindow getChooseEnemyWindow() {
        return chooseEnemyWindow;
    }

    @Override
    public GodCreature clone() throws CloneNotSupportedException
    {
        return (GodCreature) super.clone();
    }

    public GodCreature getClearCopy(){
        return new GodCreature();
    }
}
