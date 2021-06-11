package Abilities.Passive;

import Abilities.Ability;
import Abilities.AbilityType;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class LittleFool extends Ability {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ability.propertyList);
    }

    public LittleFool(){
        addAbilityType(AbilityType.PASSIVE);
        level = 1;
        maxLevel = 1;
        name = "Дурачок";
    }
}
