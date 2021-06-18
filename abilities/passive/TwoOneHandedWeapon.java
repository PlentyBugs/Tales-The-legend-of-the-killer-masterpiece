package abilities.passive;

import abilities.AbilityType;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class TwoOneHandedWeapon extends PassiveAbility {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(PassiveAbility.propertyList);
        propertyList.add(AbilityProperty.TWO_ONE_HANDED_WEAPONS);
    }

    public TwoOneHandedWeapon(){
        addAbilityType(AbilityType.PASSIVE);
        level = 1;
        maxLevel = 1;
        name = "Мастер мечник";
    }

}
