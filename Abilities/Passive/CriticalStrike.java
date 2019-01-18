package Abilities.Passive;

import Abilities.Ability;
import Abilities.AbilityType;

public class CriticalStrike extends Ability {
    public CriticalStrike(){
        setLevel(1);
        setAbilityType(AbilityType.PASSIVE);
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
}
