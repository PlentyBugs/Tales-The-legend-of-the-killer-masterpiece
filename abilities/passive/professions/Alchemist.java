package abilities.passive.professions;

import abilities.AbilityType;
import creature.Player;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Alchemist extends Profession {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Profession.propertyList);
        propertyList.add(AbilityProperty.ALCHEMIST);
    }

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
        if (player.getStats().getAlchemy() >= (level-1)*90 + 5){
            return true;
        }
        return false;
    }
}
