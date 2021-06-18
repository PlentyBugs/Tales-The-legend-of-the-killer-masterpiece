package abilities.passive;

import abilities.Ability;
import abilities.AbilityType;
import creature.Player;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class CriticalStrike extends PassiveAbility {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ability.propertyList);
        propertyList.add(AbilityProperty.CRITICAL_STRIKE);
    }

    public CriticalStrike(){
        setLevel(1);
        addAbilityType(AbilityType.PASSIVE);
        name = "Критический удар";
        maxLevel = 5;
    }

    public CriticalStrike(int level) {
        this();
        setLevel(level);
    }

    public void setLevel(int level){
        this.level = level;
        if (level == 1){
            power = 115;
            chance = 15;
        } else if(level == 2){
            power = 130;
            chance = 20;
        } else if(level == 3){
            power = 150;
            chance = 25;
        } else if(level == 4){
            power = 200;
            chance = 20;
        } else if(level == 5){
            power = 300;
            chance = 20;
        }

        cost = level;
    }

    public boolean check(Player player){
        if (player.getStats().getStrength() >= (level-1)*10 + 5 && player.getStats().getAgility() >= (level-1)*10 + 5){
            return true;
        }
        return false;
    }
}
