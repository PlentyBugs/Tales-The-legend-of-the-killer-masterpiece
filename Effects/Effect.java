package Effects;

import LiveCreatures.LiveCreature;

public class Effect {
    protected EffectType type;
    protected int power;

    public EffectType getType() {
        return type;
    }

    public void use(LiveCreature liveCreature){}
}
