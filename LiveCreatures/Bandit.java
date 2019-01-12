package LiveCreatures;

import java.awt.*;

public class Bandit extends Human {

    public Bandit(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        stats.strength = 5 + (int)(Math.random()*(lvl+6));
        stats.speed = 5 + (int)(Math.random()*(lvl+1));
        stats.agility = 5 + (int)(Math.random()*(lvl+3));
        stats.intelligence = 5 + (int)(Math.random()*lvl);
        stats.luck = 5 + (int)(Math.random()*(lvl+4));
        stats.eloquence = 5;
        stats.blacksmith = 5;
        stats.alchemy = 5;
        stats.one_handed_weapon = 5 + (int)(Math.random()*(lvl+2));
        stats.two_handed_weapon = 5 + (int)(Math.random()*(lvl+2));
        stats.pole_weapon = 5 + (int)(Math.random()*(lvl+2));
        stats.chopping_weapon = 5 + (int)(Math.random()*(lvl+2));
        stats.long_range_weapon = 5 + (int)(Math.random()*(lvl+2));

        stats.knowledge = 0;
        stats.energy = 0;

        stats.militarism = 0;
        stats.pacifism = 0;

        color = Color.RED;
    }

    public Bandit(){
        this(0,0,"Бандит",1,70);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

}
