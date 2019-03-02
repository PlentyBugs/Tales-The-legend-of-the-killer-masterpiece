package Abilities.Passive;

import Abilities.Ability;
import Abilities.AbilityType;

public class LittleFool extends Ability {

    public LittleFool(){
        addAbilityType(AbilityType.PASSIVE);
        level = 1;
        maxLevel = 1;
        name = "Дурачок";
    }
}
