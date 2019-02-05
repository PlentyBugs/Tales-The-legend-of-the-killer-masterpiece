package Effects;

import LiveCreatures.LiveCreature;

public class Heal extends Effect {

    public Heal(EffectType type){
        this.type = type;
        power = 150;
    }

    public void use(LiveCreature liveCreature){
        if (liveCreature.getHp() + power <= liveCreature.getMaxHp()){
            liveCreature.setHp(liveCreature.getHp() + power);
        } else {
            liveCreature.setHp(liveCreature.getMaxHp());
        }
    }
}