package Quests;

import Creatures.LiveCreature;

public class KillQuest extends Quest {
    private LiveCreature enemyToKill;
    private int enemyCountToKill;
    private int enemyCountToKillCurrent;

    public KillQuest(){
        super();
        enemyCountToKillCurrent = 0;
    }

    public boolean check(){
        if (enemyCountToKillCurrent >= enemyCountToKill){
            return true;
        }
        return false;
    }

    public LiveCreature getEnemyToKill() {
        return enemyToKill;
    }

    public int getEnemyCountToKill() {
        return enemyCountToKill;
    }

    public void setEnemyCountToKill(int enemyCountToKill) {
        this.enemyCountToKill = enemyCountToKill;
    }

    public void setEnemyToKill(LiveCreature enemyToKill) {
        this.enemyToKill = enemyToKill;
    }

    public int getEnemyCountToKillCurrent() {
        return enemyCountToKillCurrent;
    }

    public void setEnemyCountToKillCurrent(int enemyCountToKillCurrent) {
        this.enemyCountToKillCurrent = enemyCountToKillCurrent;
    }
}
