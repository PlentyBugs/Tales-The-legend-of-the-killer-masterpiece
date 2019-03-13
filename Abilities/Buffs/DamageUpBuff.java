package Abilities.Buffs;

import Creatures.LiveCreature;
import Creatures.Player;

public class DamageUpBuff extends Buff implements StackableBuff {

    public DamageUpBuff(int power){
        name = "Повышение урона";
        stepCount = 4;
        this.power = power;
    }

    public DamageUpBuff(int power, int stepCount){
        this(power);
        this.stepCount = stepCount+1;
    }

    public void use(Player player){
        if(getStack(player)){
            player.setCurrentDamage(player.getCurrentDamage()*power/100.0);
        }
    }

    public void use(LiveCreature liveCreature){
        if(getStack(liveCreature)){
            liveCreature.setCurrentDamage(liveCreature.getCurrentDamage()*power/100.0);
        }
    }
    @Override
    public boolean getStack(LiveCreature liveCreature) {
        return liveCreature.getCountBuffs(this) < 2;
    }
}
