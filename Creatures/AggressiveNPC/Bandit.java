package Creatures.AggressiveNPC;

import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Item;
import Items.Potions.HealPotion;
import Items.Weapons.Bows.Bow;
import Items.Weapons.Bows.LongBow;
import Items.Weapons.Swords.Sword;
import Items.Armors.Torso;
import Creatures.Human;

import java.awt.*;

public class Bandit extends Human {

    public Bandit(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        color = Color.RED;

        uniqueDropItems = new Item[]{new Sword(), new Torso(), new Helmet(), new HealPotion(), new Bow(), new Ring(), new LongBow()};
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

    public void countStatsAfterBorn(){
        stats.strength = 5 + (int)(Math.random()*(lvl+6) + lvl*9);
        stats.speed = 5 + (int)(Math.random()*(lvl+1) + lvl);
        stats.agility = 5 + (int)(Math.random()*(lvl+3) + lvl*4);
        stats.intelligence = 5 + (int)(Math.random()*lvl + lvl*5);
        stats.luck = 5 + (int)(Math.random()*(lvl+4) + lvl*6);
        stats.eloquence = 5;
        stats.blacksmith = 5;
        stats.alchemy = 5;
        stats.one_handed_weapon = 5 + (int)(Math.random()*(lvl+2) + lvl);
        stats.two_handed_weapon = 5 + (int)(Math.random()*(lvl+2));
        stats.pole_weapon = 5 + (int)(Math.random()*(lvl+2) + lvl*3);
        stats.chopping_weapon = 5 + (int)(Math.random()*(lvl+2) + lvl*3);
        stats.long_range_weapon = 5 + (int)(Math.random()*(lvl+2) + lvl*3);

        stats.knowledge = 0;
        stats.energy = 0;

        stats.militarism = 0;
        stats.pacifism = 0;
    }

    @Override
    public Bandit clone() throws CloneNotSupportedException
    {
        return (Bandit) super.clone();
    }

    @Override
    public Bandit getClearCopy() {
        return new Bandit();
    }
}
