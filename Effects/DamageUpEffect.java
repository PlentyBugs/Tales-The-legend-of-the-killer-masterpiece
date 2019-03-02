package Effects;

import Abilities.Buffs.DamageUpBuff;
import Creatures.LiveCreature;

public class DamageUpEffect extends Effect {

    public DamageUpEffect(EffectType type){
        this.type = type;
        power = 110;
    }

    public void use(LiveCreature liveCreature){
        liveCreature.addBuffs(new DamageUpBuff(power));
    }
}
