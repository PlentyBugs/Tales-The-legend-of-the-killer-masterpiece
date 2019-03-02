package Abilities.Passive.Professions;

import Abilities.Ability;
import Abilities.AbilityType;
import Creatures.Player;

public class Alchemist extends Ability {

    public Alchemist(){
        setLevel(1);
        addAbilityType(AbilityType.PASSIVE);
        name = "Алхимик новичек";
        maxLevel = 3;
    }
    public void setLevel(int level){
        this.level = level;
        if (level == 1){
            name = "Алхимик новичек";
        } else if(level == 2){
            name = "Алхимик";
        } else if(level == 3){
            name = "Старший алхимик";
        } else if(level == 4){
            name = "Архиалхимик";
        }
        cost = level*4;
    }

    public boolean check(Player player){
        if (player.getStats().alchemy >= (level-1)*90 + 5){
            return true;
        }
        return false;
    }
}
