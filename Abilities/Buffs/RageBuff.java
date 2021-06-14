package Abilities.Buffs;

import Creatures.LiveCreature;
import Creatures.Player;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class RageBuff extends Buff {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Buff.propertyList);
        propertyList.add(AbilityProperty.RAGE_BUFF);
    }

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
