package Abilities.Passive;

import Abilities.Ability;
import Abilities.AbilityType;

public class Steal extends Ability {

    public Steal(){
        addAbilityType(AbilityType.PASSIVE);
        level = 1;
        maxLevel = 1;
        name = "Воровство";
    }
}
