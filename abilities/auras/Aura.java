package abilities.auras;

import abilities.Ability;
import creature.LiveCreature;
import support.AbilityProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class Aura extends Ability  {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Ability.propertyList);
        propertyList.add(AbilityProperty.AURA);
    }

    public void use(LiveCreature liveCreature){

    }
}
