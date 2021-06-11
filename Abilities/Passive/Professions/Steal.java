package Abilities.Passive.Professions;

import Abilities.Ability;
import Abilities.AbilityType;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Steal extends Ability {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ability.propertyList);
    }

    public Steal(){
        addAbilityType(AbilityType.PASSIVE);
        level = 1;
        maxLevel = 1;
        name = "Воровство";
    }
}
