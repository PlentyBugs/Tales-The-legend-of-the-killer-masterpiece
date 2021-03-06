package abilities.active;

import abilities.AbilityTarget;
import abilities.AbilityType;
import abilities.buffs.DamageUpBuff;
import abilities.CostType;
import creature.Player;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class DamageUp extends ActiveAbility {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(ActiveAbility.propertyList);
        propertyList.add(AbilityProperty.DAMAGE_UP);
    }

    public DamageUp(){
        setLevel(1);
        addAbilityType(AbilityType.ACTIVE);
        addAbilityType(AbilityType.BUFF);
        costType = CostType.HEALTH;
        name = "Увеличение урона";
        maxLevel = 5;
        abilityTarget = AbilityTarget.PLAYER;
    }

    public DamageUp(int level) {
        this();
        setLevel(level);
    }

    public void setLevel(int level){
        this.level = level;
        if (level == 1){
            power = 200;
            useCost = 5;
            buff = new DamageUpBuff(power);
        } else if(level == 2){
            power = 250;
            useCost = 5;
            buff = new DamageUpBuff(power);
        } else if(level == 3){
            power = 350;
            useCost = 5;
            buff = new DamageUpBuff(power);
        } else if(level == 4){
            power = 500;
            useCost = 7;
            buff = new DamageUpBuff(power, 2);
        } else if(level == 5){
            power = 800;
            useCost = 10;
            buff = new DamageUpBuff(power, 2);
        }

        cost = level;
    }

    public boolean check(Player player){
        if (player.getStats().getStrength() >= (level-1)*30 + 5){
            return true;
        }
        return false;
    }
}
