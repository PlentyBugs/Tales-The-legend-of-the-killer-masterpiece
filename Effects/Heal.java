package Effects;

import Creatures.LiveCreature;

public class Heal extends Effect {

    public Heal(EffectType type){
        this.type = type;
        power = 70;
    }

    public void use(LiveCreature liveCreature){
        if (liveCreature.getHp() + power <= liveCreature.getMaxHp()){
            liveCreature.setHp(liveCreature.getHp() + power);
        } else {
            liveCreature.setHp(liveCreature.getMaxHp());
        }
    }

    @Override
    public void setPowerAlchemy(int alchemyPower){
        power += alchemyPower*30;
    }
}
