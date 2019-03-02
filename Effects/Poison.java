package Effects;

import Creatures.LiveCreature;

public class Poison extends Effect {

    public Poison(EffectType type){
        this.type = type;
        power = 150;
    }

    public void use(LiveCreature liveCreature){
        if (liveCreature.getHp() - power > 1){
            liveCreature.setHp(liveCreature.getHp() - power);
        } else
            liveCreature.setHp(1);
    }

    @Override
    public void setPowerAlchemy(int alchemyPower){
        power += alchemyPower*20;
    }
}
