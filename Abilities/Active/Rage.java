package Abilities.Active;

import Abilities.AbilityTarget;
import Abilities.AbilityType;
import Abilities.Buffs.Buff;
import Abilities.Buffs.RageBuff;
import Abilities.CostType;
import Creatures.Player;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Rage extends ActiveAbility {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(ActiveAbility.propertyList);
        propertyList.add(AbilityProperty.RAGE);
    }

    public Rage(){
        setLevel(1);
        addAbilityType(AbilityType.ACTIVE);
        addAbilityType(AbilityType.BUFF);
        costType = CostType.HEALTH;
        name = "Ярость";
        maxLevel = 2;
        abilityTarget = AbilityTarget.PLAYER;
    }

    public Buff getBuff() {
        try {
            if(liveCreature.getCountBuffs(buff) == 0)
                return (Buff) buff.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return buff;
    }

    public Rage(int level) {
        this();
        setLevel(level);
    }

    public void setLevel(int level){
        this.level = level;
        if (level == 1){
            power = 1;
            useCost = 10;
            buff = new RageBuff(power);
        } else if(level == 2){
            power = 2;
            useCost = 15;
            buff = new RageBuff(power);
        }

        cost = level*6;
    }

    public boolean check(Player player){
        if (player.getStats().getSpeed() >= (level-1)*30 + 5){
            return true;
        }
        return false;
    }
}
