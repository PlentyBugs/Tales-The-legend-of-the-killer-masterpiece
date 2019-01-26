package Abilities.Buffs;

import LiveCreatures.LiveCreature;

public class DecreaseDamageBuff extends Buff {

    public DecreaseDamageBuff(int power){
        name = "Уменьшение урона";
        stepCount = 4;
        this.power = power;
    }

    public DecreaseDamageBuff(int power, int stepCount){
        this(power);
        this.stepCount = stepCount+1;
    }

    public void use(LiveCreature liveCreature){
        liveCreature.setCurrentDamage(liveCreature.getCurrentDamage()*(100.0/(100.0 + power)));
    }
}
