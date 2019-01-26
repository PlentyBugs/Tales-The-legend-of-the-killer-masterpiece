package Abilities.Auras;

import Abilities.AbilityType;
import LiveCreatures.LiveCreature;
import LiveCreatures.Player;

public class Vision extends Aura {
    public Vision(){
        setLevel(1);
        addAbilityType(AbilityType.AURA);
        name = "Зрение";
        maxLevel = 4;
    }

    public Vision(int level) {
        this();
        setLevel(level);
    }

    public void use(LiveCreature liveCreature){
        if (liveCreature.getIsPlayer()){
            ((Player)liveCreature).setVision(power);
        }
    }

    public void setLevel(int level){
        this.level = level;
        if (level == 1){
            power = 3;
        } else if(level == 2){
            power = 4;
        } else if(level == 3){
            power = 5;
        } else if(level == 4){
            power = 6;
        }

        cost = level*2;
    }

    public boolean check(Player player){
        if (player.getStats().long_range_weapon >= (level - 1)*25 + 5){
            return true;
        }
        return false;
    }
}
