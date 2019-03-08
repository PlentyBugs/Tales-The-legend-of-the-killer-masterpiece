package Abilities.Active;

import Abilities.AbilityTarget;
import Abilities.AbilityType;
import Abilities.Buffs.DecreaseDamageBuff;
import Creatures.Player;

public class DecreaseDamage extends AbilityActive {

    public DecreaseDamage(){
        setLevel(1);
        addAbilityType(AbilityType.ACTIVE);
        addAbilityType(AbilityType.BUFF);
        name = "Уменьшение урона";
        maxLevel = 5;
        abilityTarget = AbilityTarget.ENEMY;
    }

    public DecreaseDamage(int level) {
        this();
        setLevel(level);
    }

    public void setLevel(int level){
        this.level = level;
        if (level == 1){
            useCost = 2;
            power = 100;
            buff = new DecreaseDamageBuff(power);
        } else if(level == 2){
            power = 150;
            useCost = 3;
            buff = new DecreaseDamageBuff(power);
        } else if(level == 3){
            power = 250;
            useCost = 3;
            buff = new DecreaseDamageBuff(power);
        } else if(level == 4){
            power = 400;
            useCost = 3;
            buff = new DecreaseDamageBuff(power, 2);
        } else if(level == 5){
            power = 900;
            useCost = 5;
            buff = new DecreaseDamageBuff(power, 2);
        }

        cost = level;
    }

    public boolean check(Player player){
        if (player.getStats().getStrength() >= (level-1)*30 + 5){
            return true;
        }
        return false;
    }
}
