package Abilities.Buffs;

import Creatures.LiveCreature;
import Creatures.Player;
import Windows.BattleWindows.FightWindow;

public class Bleeding extends Buff {
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
        fightWindow.writeToPlayerConsole(player.getName() + " истекает кровью и теряет " + power*(7-stepCount) + " хп");
        player.setHp(player.getHp() - power*(7-stepCount));
    }

    public void use(LiveCreature liveCreature){
        fightWindow.writeToEnemyStatusConsole(liveCreature.getName() + " истекает кровью и теряет " + power*(7-stepCount) + " хп");
        liveCreature.setHp(liveCreature.getHp() - power*(7-stepCount));
    }

    public void setFightWindow(FightWindow fightWindow) {
        this.fightWindow = fightWindow;
    }
}
