package Creatures;

public class Human extends LiveCreature {
    protected String name;
    protected String location = "Пустота";

    protected int upPointCount = 0;
    protected double hp = 100;

    protected String race = "Человек";

    public Human(){
        this(0,0,"Человек",1,100);
    }

    public Human(int x, int y, String name, int lvl, int hp){

        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        this.x = x;
        this.y = y;
        isStep = false;
    }

    public void addUpPoints(int count){
        upPointCount += count;
    }

    public int getUpPointCount() {
        return upPointCount;
    }

    public Stats getStats() {
        return stats;
    }

    public void setUpPointCount(int upPointCount) {
        this.upPointCount = upPointCount;
    }

    public void setName(String name){
        this.name = name;
    }

    public void countStatsAfterBorn(){
        stats.strength = 5;
        stats.speed = 5;
        stats.agility = 5;
        stats.intelligence = 5;
        stats.luck = 5;
        stats.eloquence = 5;
        stats.blacksmith = 5;
        stats.alchemy = 5;
        stats.one_handed_weapon = 5;
        stats.two_handed_weapon = 5;
        stats.pole_weapon = 5;
        stats.chopping_weapon = 5;
        stats.long_range_weapon = 5;

        stats.knowledge = 0;
        stats.energy = 0;

        stats.militarism = 0;
        stats.pacifism = 0;
    }

    @Override
    public Human clone() throws CloneNotSupportedException
    {
        return (Human) super.clone();
    }

    @Override
    public Human getClearCopy() {
        return new Human();
    }
}
