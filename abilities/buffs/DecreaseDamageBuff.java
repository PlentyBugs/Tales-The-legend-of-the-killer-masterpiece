package abilities.buffs;

import creature.LiveCreature;
import creature.Player;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class DecreaseDamageBuff extends Buff implements StackableBuff {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Buff.propertyList);
        propertyList.add(AbilityProperty.DECREASE_DAMAGE_BUFF);
    }

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
        if(getStack(player)){
            player.setCurrentDamage(player.getCurrentDamage()*(100.0/(100.0 + power)));
        }
    }

    public void use(LiveCreature liveCreature){
        if(getStack(liveCreature)){
            liveCreature.setCurrentDamage(liveCreature.getCurrentDamage()*(100.0/(100.0 + power)));
        }
    }
    @Override
    public boolean getStack(LiveCreature liveCreature) {
        return liveCreature.getCountBuffs(this) < 2;
    }
}
