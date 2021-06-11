package Creatures;

import Windows.BattleWindows.ChooseEnemyWindow;
import Windows.WindowInterface;
import support.Property;
import support.GeneralProperty;
import support.PropertyProvider;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GodCreature implements Serializable, Cloneable, Copying<GodCreature>, PropertyProvider {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}
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

    public GodCreature setX(int x){
        this.x = x;
        return this;
    }

    public GodCreature setY(int y){
        this.y = y;
        return this;
    }

    public void setIsPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    public boolean getIsPlayer(){
        return isPlayer;
    }

    public void setChooseEnemyWindow(Player player, WindowInterface fieldWindow, LiveCreature liveCreature){
        chooseEnemyWindow = new ChooseEnemyWindow(player, fieldWindow, liveCreature);
        chooseEnemyWindow.requestFocusInWindow();
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
