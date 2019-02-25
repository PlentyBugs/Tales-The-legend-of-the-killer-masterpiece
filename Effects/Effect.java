package Effects;

import Creatures.LiveCreature;

import java.io.Serializable;

public class Effect implements Serializable {
    protected EffectType type;
    protected int power;

    public EffectType getType() {
        return type;
    }

    public void use(LiveCreature liveCreature){}
}
