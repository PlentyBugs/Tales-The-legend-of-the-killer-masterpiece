package Abilities.Active;

import Abilities.Ability;
import Abilities.AbilityTarget;
import Abilities.Buffs.Buff;
import Abilities.CostType;
import Creatures.LiveCreature;
import Creatures.Player;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class ActiveAbility extends Ability {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ability.propertyList);
        propertyList.add(AbilityProperty.ACTIVE_ABILITY);
    }

    protected AbilityTarget abilityTarget;
    protected Buff buff;
    protected int useCost;

    public void use(Player player){}

    public void use(LiveCreature liveCreature){}

    public AbilityTarget getAbilityTarget() {
        return abilityTarget;
    }

    public void setAbilityTarget(AbilityTarget abilityTarget) {
        this.abilityTarget = abilityTarget;
    }

    public Buff getBuff() {
        try {
            return (Buff) buff.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    public void chargeFee(LiveCreature liveCreature){
        if(costType == CostType.ENERGY){
            //todo change when energy will be here
        } else if(costType == CostType.HEALTH){
            liveCreature.setHp(liveCreature.getHp()*(100-useCost)/100);
        } else if(costType == CostType.MONEY){
            liveCreature.reduceMoney(useCost);
        }
    }

    public int getUseCost() {
        return useCost;
    }
}
