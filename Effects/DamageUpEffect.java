package Effects;

import Abilities.Buffs.DamageUpBuff;
import Creatures.LiveCreature;
import support.Property;
import support.GeneralProperty;

import java.util.ArrayList;
import java.util.List;

public class DamageUpEffect extends Effect {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Effect.propertyList);
        propertyList.add(GeneralProperty.DAMAGE_UP_EFFECT);
    }

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
