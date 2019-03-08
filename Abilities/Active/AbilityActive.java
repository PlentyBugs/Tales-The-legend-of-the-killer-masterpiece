package Abilities.Active;

import Abilities.Ability;
import Abilities.AbilityTarget;
import Abilities.Buffs.Buff;
import Abilities.CostType;
import Creatures.LiveCreature;
import Creatures.Player;

public class AbilityActive extends Ability {

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
