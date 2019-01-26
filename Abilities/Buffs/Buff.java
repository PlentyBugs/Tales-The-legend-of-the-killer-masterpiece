package Abilities.Buffs;

import LiveCreatures.LiveCreature;
import LiveCreatures.Player;

public class Buff {
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
}
