package Abilities.Buffs;

import Creatures.LiveCreature;
import Creatures.Player;
import Windows.BattleWindows.FightWindow;

public class Bleeding extends Buff implements StackableBuff {
    private FightWindow fightWindow;

    public Bleeding(int power){
        name = "Кровотечение";
        stepCount = 7;
        this.power = power;
    }

    public Bleeding(int power, int stepCount){
        this(power);
        this.stepCount = stepCount+1;
    }

    public void use(Player player){
        if(getStack(player)){
            fightWindow.writeToPlayerConsole(player.getName() + " истекает кровью и теряет " + power*(8-stepCount) + " хп");
            player.setHp(player.getHp() - power*(8-stepCount));
        }
    }

    public void use(LiveCreature liveCreature){
        if(getStack(liveCreature)){
            fightWindow.writeToEnemyStatusConsole(liveCreature.getName() + " истекает кровью и теряет " + power*(8-stepCount) + " хп");
            liveCreature.setHp(liveCreature.getHp() - power*(8-stepCount));
        }
    }

    public void setFightWindow(FightWindow fightWindow) {
        this.fightWindow = fightWindow;
    }
    @Override
    public boolean getStack(LiveCreature liveCreature) {
        return liveCreature.getCountBuffs(this) < 6;
    }
}
