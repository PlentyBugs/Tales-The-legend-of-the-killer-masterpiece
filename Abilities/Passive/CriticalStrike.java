package Abilities.Passive;

import Abilities.Ability;
import Abilities.AbilityType;
import Creatures.Player;

public class CriticalStrike extends Ability {
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
        if (player.getStats().strength >= (level-1)*10 + 5 && player.getStats().agility >= (level-1)*10 + 5){
            return true;
        }
        return false;
    }
}
