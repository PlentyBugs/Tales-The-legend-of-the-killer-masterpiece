package Effects;

import Creatures.LiveCreature;

import java.io.Serializable;

public class Effect implements Serializable {
    protected EffectType type;
    protected int power;
    private static final long serialVersionUID = -1522910815967517982L;

    public EffectType getType() {
        return type;
    }

    public void use(LiveCreature liveCreature){}

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public void setPowerAlchemy(int alchemyPower){
        power += alchemyPower;
    }

    public Effect getClearCopy(){
        return new Effect();
    }
}
