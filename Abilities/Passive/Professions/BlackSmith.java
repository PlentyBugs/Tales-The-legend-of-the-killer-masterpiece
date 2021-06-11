package Abilities.Passive.Professions;

import Abilities.Ability;
import Abilities.AbilityType;
import Creatures.Player;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class BlackSmith extends Ability {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ability.propertyList);
    }

    public BlackSmith(){
        setLevel(1);
        addAbilityType(AbilityType.PASSIVE);
        name = "Кузнец новичек";
        maxLevel = 3;
    }
    public void setLevel(int level){
        this.level = level;
        if (level == 1){
            name = "Кузнец новичек";
        } else if(level == 2){
            name = "Кузнец";
        } else if(level == 3){
            name = "Старший Кузнец";
        } else if(level == 4){
            name = "Высший Кузнец";
        }
        cost = level*4;
    }

    public boolean check(Player player){
        if (player.getStats().getBlacksmith() >= (level-1)*90 + 5){
            return true;
        }
        return false;
    }
}
