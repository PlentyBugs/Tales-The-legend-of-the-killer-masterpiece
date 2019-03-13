package Abilities.Buffs;

import Creatures.LiveCreature;
import Creatures.Player;

public class RageBuff extends Buff {

    public RageBuff(int power){
        name = "Ярость";
        stepCount = 4;
        this.power = power;
    }

    public RageBuff(int power, int stepCount){
        this(power);
        this.stepCount = stepCount+1;
    }

    public void use(Player player){
        //todo change later
        player.addBuffs(new DecreaseDamageBuff(60, stepCount));
    }

    public void use(LiveCreature liveCreature){
        //todo change later
        liveCreature.addBuffs(new DecreaseDamageBuff(60, stepCount));
    }
}
