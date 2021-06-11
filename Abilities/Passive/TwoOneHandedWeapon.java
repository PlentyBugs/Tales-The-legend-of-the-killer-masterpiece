package Abilities.Passive;

import Abilities.Ability;
import Abilities.AbilityType;
import Abilities.Enchants.Enchant;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class TwoOneHandedWeapon extends Ability {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ability.propertyList);
    }

    public TwoOneHandedWeapon(){
        addAbilityType(AbilityType.PASSIVE);
        level = 1;
        maxLevel = 1;
        name = "Мастер мечник";
    }

}
