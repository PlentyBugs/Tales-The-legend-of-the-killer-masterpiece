package Abilities.Buffs;

import LiveCreatures.LiveCreature;
import LiveCreatures.Player;

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

    public void use(Player player){
        player.setCurrentDamage(player.getCurrentDamage()*(100.0/(100.0 + power)));
    }

    public void use(LiveCreature liveCreature){
        liveCreature.setCurrentDamage(liveCreature.getCurrentDamage()*(100.0/(100.0 + power)));
    }
}
