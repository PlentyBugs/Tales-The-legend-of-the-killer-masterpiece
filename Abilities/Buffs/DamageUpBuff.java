package Abilities.Buffs;

import LiveCreatures.LiveCreature;
import LiveCreatures.Player;

public class DamageUpBuff extends Buff {

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
        player.setCurrentDamage(player.getCurrentDamage()*power/100.0);
    }

    public void use(LiveCreature liveCreature){
        liveCreature.setCurrentDamage(liveCreature.getCurrentDamage()*power/100.0);
    }
}
