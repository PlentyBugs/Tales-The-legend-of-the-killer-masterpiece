package abilities.passive;

import abilities.Ability;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class PassiveAbility extends Ability {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ability.propertyList);
        propertyList.add(AbilityProperty.PASSIVE_ABILITY);
    }

}
