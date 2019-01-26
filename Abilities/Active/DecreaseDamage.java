package Abilities.Active;

import Abilities.AbilityTarget;
import Abilities.AbilityType;
import Abilities.Buffs.DecreaseDamageBuff;
import LiveCreatures.Player;

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
            power = 100;
            buff = new DecreaseDamageBuff(power);
        } else if(level == 2){
            power = 150;
            buff = new DecreaseDamageBuff(power);
        } else if(level == 3){
            power = 250;
            buff = new DecreaseDamageBuff(power);
        } else if(level == 4){
            power = 400;
            buff = new DecreaseDamageBuff(power, 2);
        } else if(level == 5){
            power = 900;
            buff = new DecreaseDamageBuff(power, 2);
        }

        cost = level;
    }

    public boolean check(Player player){
        if (player.getStats().strength >= (level-1)*30 + 5){
            return true;
        }
        return false;
    }
}
