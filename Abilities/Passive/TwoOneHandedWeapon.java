package Abilities.Passive;

import Abilities.Ability;
import Abilities.AbilityType;

public class TwoOneHandedWeapon extends Ability {

    public TwoOneHandedWeapon(){
        setAbilityType(AbilityType.PASSIVE);
        level = 1;
        maxLevel = 1;
        name = "По 1h в руку";
    }

}
