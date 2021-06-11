package Abilities.Auras;

import Abilities.AbilityType;
import Creatures.LiveCreature;
import Creatures.Player;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Vision extends Aura  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Aura.propertyList);
    }

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
        power = level + 4;

        cost = level*2;
    }

    public boolean check(Player player){
        if (player.getStats().getLong_range_weapon() >= (level - 1)*25 + 5){
            return true;
        }
        return false;
    }
}
