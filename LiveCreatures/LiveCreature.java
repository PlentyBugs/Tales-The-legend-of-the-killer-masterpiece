package JGame.LiveCreatures;

import JGame.Effects.Effect;
import JGame.LiveCreatures.GodCreature;

import java.util.ArrayList;

public abstract class LiveCreature extends GodCreature {
    protected int x;
    protected int y;
    protected int hp;
    protected int lvl;
    protected int money = 0;
    protected ArrayList<Effect> effects = new ArrayList<Effect>();
    protected Stats stats = new Stats();


    public LiveCreature(int x, int y, String name, int lvl, int hp){

        this.name = name;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.lvl = lvl;
    }

    public int getHp() {
        return hp;
    }

    public int getLvl() {
        return lvl;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void addEffect(Effect effect){
        effects.add(effect);
    }

    public Stats getStats() {
        return stats;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void reduceMoney(int money) {
        this.money -= money;
    }
}
