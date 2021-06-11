package Abilities.Buffs;

import Creatures.LiveCreature;
import Windows.BattleWindows.FightWindow;

public class Necrosis extends Buff implements StackableBuff {
    private FightWindow fightWindow;

    public Necrosis(int power){
        name = "Некроз";
        stepCount = 3;
        this.power = power;
    }

    public Necrosis(int power, int stepCount){
        this(power);
        this.stepCount = stepCount+1;
    }

    public void use(LiveCreature liveCreature){
        if(getStack(liveCreature)){
            fightWindow.writeToEnemyStatusConsole(liveCreature.getName() + " подвержен некрозу и теряет " + power*(4-stepCount)*liveCreature.getHp()/100.0 + " хп");
            liveCreature.setHp(liveCreature.getHp() - power*(4-stepCount)*liveCreature.getHp()/100.0);
        }
    }

    public void setFightWindow(FightWindow fightWindow) {
        this.fightWindow = fightWindow;
    }
    @Override
    public boolean getStack(LiveCreature liveCreature) {
        return liveCreature.getCountBuffs(this) < 2;
    }
}
