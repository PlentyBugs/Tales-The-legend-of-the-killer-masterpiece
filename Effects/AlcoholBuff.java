package Effects;

import Abilities.Buffs.DecreaseDamageBuff;
import Creatures.LiveCreature;
import Creatures.Player;
import support.Property;
import support.GeneralProperty;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class AlcoholBuff extends Effect {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Effect.propertyList);
        propertyList.add(GeneralProperty.ALCOHOL_EFFECT);
    }
    @Serial
    private static final long serialVersionUID = -2237990859028001672L;

    public AlcoholBuff(){
        this(EffectType.MOMENT);
    }

    public AlcoholBuff(EffectType type){
        this.type = type;
        power = -4;
    }

    public void use(LiveCreature liveCreature){
        if(liveCreature instanceof Player player){
            player.addExp(power * liveCreature.getLvl() * 12);
            liveCreature.addBuffs(new DecreaseDamageBuff(power));
        } else {
            liveCreature.addLoyaltyById(0, power);
        }
    }

    public void use(LiveCreature liveCreature, long userId){
        if(liveCreature instanceof Player player){
            player.addExp(power * liveCreature.getLvl() * 12);
            liveCreature.addBuffs(new DecreaseDamageBuff(power));
        } else {
            liveCreature.addLoyaltyById(userId, power);
        }
    }

    @Override
    public void setPowerAlchemy(int alchemyPower){
        power += Math.pow(Math.E, Math.log(alchemyPower)/Math.log(7));
    }

    public AlcoholBuff getClearCopy(){
        return new AlcoholBuff();
    }
}
