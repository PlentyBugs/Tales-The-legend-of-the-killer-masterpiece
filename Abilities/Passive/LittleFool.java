package Abilities.Passive;

import Abilities.AbilityType;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class LittleFool extends PassiveAbility {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(PassiveAbility.propertyList);
        propertyList.add(AbilityProperty.LITTLE_FOOL);
    }

    public LittleFool(){
        addAbilityType(AbilityType.PASSIVE);
        level = 1;
        maxLevel = 1;
        name = "Дурачок";
    }
}
