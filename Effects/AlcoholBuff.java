package Effects;

import Abilities.Buffs.DecreaseDamageBuff;
import Creatures.LiveCreature;
import Creatures.Player;

public class AlcoholBuff extends Effect {
    private static final long serialVersionUID = -2237990859028001672L;

    public AlcoholBuff(){
        this(EffectType.MOMENT);
    }

    public AlcoholBuff(EffectType type){
        this.type = type;
        power = -4;
    }

    public void use(LiveCreature liveCreature){
        if(liveCreature instanceof Player){
            ((Player) liveCreature).addExp(power*liveCreature.getLvl()*12);
            liveCreature.addBuffs(new DecreaseDamageBuff(power));
        } else {
            liveCreature.addLoyalityByClassName(Player.class.toString(), power);
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
