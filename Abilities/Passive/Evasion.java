package Abilities.Passive;


import Abilities.Ability;
import Abilities.AbilityType;

public class Evasion extends Ability {
    public Evasion(){
        setLevel(1);
        setAbilityType(AbilityType.PASSIVE);
    }

    public Evasion(int level){
        this();
        setLevel(level);
    }

    public void setLevel(int level){
        power = 0;
        this.level = level;
        if (level == 1){
            chance = 15;
        } else if(level == 2){
            chance = 20;
        } else if(level == 3){
            chance = 25;
        } else if(level == 4){
            chance = 35;
        } else if(level == 5){
            chance = 45;
        }
    }
}
