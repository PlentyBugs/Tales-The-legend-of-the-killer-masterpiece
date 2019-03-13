package Effects;

import Creatures.LiveCreature;

public class NecrosisEffect extends Effect {
    private static final long serialVersionUID = 3324013978376654430L;

    public NecrosisEffect(){
        this(EffectType.MOMENT);
    }

    public NecrosisEffect(EffectType type){
        this.type = type;
        power = 3;
    }

    public void use(LiveCreature liveCreature){
        if (liveCreature.getHp() - power > 1){
            liveCreature.setHp(liveCreature.getHp() - power);
        } else
            liveCreature.setHp(1);
    }

    @Override
    public void setPowerAlchemy(int alchemyPower){
        power += Math.pow(Math.E, Math.log(alchemyPower)/Math.log(10));
    }

    public Poison getClearCopy(){
        return new Poison();
    }
}
