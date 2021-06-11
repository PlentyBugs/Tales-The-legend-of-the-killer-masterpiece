package Abilities.Passive;


import Abilities.Ability;
import Abilities.AbilityType;
import Creatures.Player;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Evasion extends Ability {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ability.propertyList);
    }

    public Evasion(){
        setLevel(1);
        addAbilityType(AbilityType.PASSIVE);
        name = "Уклонение";
        maxLevel = 5;
    }

    public Evasion(int level){
        this();
        setLevel(level);
    }

    public void setLevel(int level){
        power = 0;
        this.level = level;
        if (level == 1){
            chance = 15;
        } else if(level == 2){
            chance = 20;
        } else if(level == 3){
            chance = 25;
        } else if(level == 4){
            chance = 35;
        } else if(level == 5){
            chance = 45;
        }

        cost = level;
    }

    public boolean check(Player player){
        System.out.println(player.getStats().getSpeed());
        System.out.println(level);
        System.out.println(player.getStats().getAgility());
        if (player.getStats().getSpeed() >= (level - 1)*10 + 5 && player.getStats().getAgility() >= (level - 1)*10 + 5){
            return true;
        }
        return false;
    }
}
