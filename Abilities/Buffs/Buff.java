package Abilities.Buffs;

import LiveCreatures.LiveCreature;
import LiveCreatures.Player;

import java.io.Serializable;

public class Buff implements Cloneable, Serializable {
    protected int stepCount;
    protected String name;
    protected int power;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void tick(){
        stepCount --;
    }

    public void use(Player player){}

    public void use(LiveCreature liveCreature){}

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}
