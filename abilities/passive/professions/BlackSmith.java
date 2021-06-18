package abilities.passive.professions;

import abilities.AbilityType;
import creature.Player;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class BlackSmith extends Profession {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Profession.propertyList);
        propertyList.add(AbilityProperty.BLACKSMITH);
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
