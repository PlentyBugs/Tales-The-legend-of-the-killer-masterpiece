package JGame.LiveCreatures;

import JGame.LiveCreatures.Human;

import java.awt.*;

public class Goblin extends Human {

    public Goblin(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        this.hp = hp;
        this.lvl = lvl;
        this.name = name;
        stats.strength = 5 + (int)(Math.random()*(lvl+2));
        stats.speed = 5 + (int)(Math.random()*(lvl+2));
        stats.agility = 5 + (int)(Math.random()*(lvl+2));
        stats.intelligence = 5;
        stats.luck = 5;
        stats.eloquence = 5;
        stats.blacksmith = 5;
        stats.alchemy = 5;
        stats.one_handed_weapon = 5 + (int)(Math.random()*(lvl+2));
        stats.two_handed_weapon = 5;
        stats.pole_weapon = 5;
        stats.chopping_weapon = 5;
        stats.long_range_weapon = 5;

        stats.knowledge = 0;
        stats.energy = 0;

        stats.militarism = 0;
        stats.pacifism = 0;

        color = new Color(170,200,10);
    }

    public Goblin(){
        this(0,0, "Гоблин",1,50);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

}
