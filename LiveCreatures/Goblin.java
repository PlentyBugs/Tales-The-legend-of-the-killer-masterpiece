package LiveCreatures;

import Items.Item;
import Items.Potions.HealPotion;
import Items.Potions.PoisonPotion;
import Items.Weapons.Bows.ShortBow;
import Items.Weapons.Swords.Sword;

import java.awt.*;

public class Goblin extends Human {

    public Goblin(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
        this.name = name;

        color = new Color(170,200,10);
        uniqueDropItems = new Item[]{new Sword(), new HealPotion(), new PoisonPotion(), new ShortBow()};
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

    public void countStatsAfterBorn(){
        stats.strength = 5 + (int)(Math.random()*(lvl+2) + lvl*6);
        stats.speed = 5 + (int)(Math.random()*(lvl+2) + lvl*2);
        stats.agility = 5 + (int)(Math.random()*(lvl+2) + lvl*4);
        stats.intelligence = 5;
        stats.luck = 5;
        stats.eloquence = 5;
        stats.blacksmith = 5;
        stats.alchemy = 5;
        stats.one_handed_weapon = 5 + (int)(Math.random()*(lvl+2) + lvl*5);
        stats.two_handed_weapon = 5 + (int)(Math.random()*(lvl+8) + lvl*6);
        stats.pole_weapon = 5;
        stats.chopping_weapon = 5;
        stats.long_range_weapon = 5;
    }

}
