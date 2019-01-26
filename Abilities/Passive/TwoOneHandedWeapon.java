package Abilities.Passive;

import Abilities.Ability;
import Abilities.AbilityType;

public class TwoOneHandedWeapon extends Ability {

    public TwoOneHandedWeapon(){
        addAbilityType(AbilityType.PASSIVE);
        level = 1;
        maxLevel = 1;
        name = "Мастер мечник";
    }

}
