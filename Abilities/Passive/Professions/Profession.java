package Abilities.Passive.Professions;

import Abilities.Passive.PassiveAbility;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Profession extends PassiveAbility {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(PassiveAbility.propertyList);
        propertyList.add(AbilityProperty.PROFESSION);
    }
}
