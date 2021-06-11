package Quests;

import Creatures.LiveCreature;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class KillQuest extends Quest {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Quest.propertyList);
    }

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

    public KillQuest setEnemyCountToKill(int enemyCountToKill) {
        this.enemyCountToKill = enemyCountToKill;
        return this;
    }

    public KillQuest setEnemyToKill(LiveCreature enemyToKill) {
        this.enemyToKill = enemyToKill;
        return this;
    }

    public int getEnemyCountToKillCurrent() {
        return enemyCountToKillCurrent;
    }

    public KillQuest setEnemyCountToKillCurrent(int enemyCountToKillCurrent) {
        this.enemyCountToKillCurrent = enemyCountToKillCurrent;
        return this;
    }
}
