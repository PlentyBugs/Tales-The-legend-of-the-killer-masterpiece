package JGame.LiveCreatures;

import JGame.LiveCreatures.GodCreature;

public abstract class LiveCreature extends GodCreature {
    protected int x;
    protected int y;
    protected int hp;
    protected int lvl;

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
}
