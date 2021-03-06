package abilities.active;

import abilities.AbilityTarget;
import abilities.AbilityType;
import abilities.buffs.DecreaseDamageBuff;
import creature.Player;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class DecreaseDamage extends ActiveAbility {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(ActiveAbility.propertyList);
        propertyList.add(AbilityProperty.DECREASE_DAMAGE);
    }

    public DecreaseDamage(){
        setLevel(1);
        addAbilityType(AbilityType.ACTIVE);
        addAbilityType(AbilityType.BUFF);
        name = "Уменьшение урона";
        maxLevel = 5;
        abilityTarget = AbilityTarget.ENEMY;
    }

    public DecreaseDamage(int level) {
        this();
        setLevel(level);
    }

    public void setLevel(int level){
        this.level = level;
        if (level == 1){
            useCost = 2;
            power = 100;
            buff = new DecreaseDamageBuff(power);
        } else if(level == 2){
            power = 150;
            useCost = 3;
            buff = new DecreaseDamageBuff(power);
        } else if(level == 3){
            power = 250;
            useCost = 3;
            buff = new DecreaseDamageBuff(power);
        } else if(level == 4){
            power = 400;
            useCost = 3;
            buff = new DecreaseDamageBuff(power, 2);
        } else if(level == 5){
            power = 900;
            useCost = 5;
            buff = new DecreaseDamageBuff(power, 2);
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
