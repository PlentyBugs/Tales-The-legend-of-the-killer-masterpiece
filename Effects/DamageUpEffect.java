package Effects;

import Abilities.Buffs.DamageUpBuff;
import Creatures.LiveCreature;

public class DamageUpEffect extends Effect {

    public DamageUpEffect(){
        this(EffectType.MOMENT);
    }

    public DamageUpEffect(EffectType type){
        this.type = type;
        power = 110;
    }

    public void use(LiveCreature liveCreature){
        liveCreature.addBuffs(new DamageUpBuff(power));
    }

    public DamageUpEffect getClearCopy(){
        return new DamageUpEffect();
    }

    public void setPowerAlchemy(int alchemyPower){
        power += Math.pow(Math.E, Math.log(alchemyPower)/Math.log(3.1));
    }
}
