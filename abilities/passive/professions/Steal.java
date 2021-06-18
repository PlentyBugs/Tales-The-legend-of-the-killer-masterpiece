package abilities.passive.professions;

import abilities.Ability;
import abilities.AbilityType;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Steal extends Ability {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Profession.propertyList);
        propertyList.add(AbilityProperty.STEAL);
    }

    public Steal(){
        addAbilityType(AbilityType.PASSIVE);
        level = 1;
        maxLevel = 1;
        name = "Воровство";
    }
}
