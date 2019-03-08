package Creatures.AggressiveNPC;

import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Item;
import Items.Alchemy.Potions.HealPotion;
import Items.Weapons.Choppings.Axe;
import Items.Weapons.Staffs.Staff;
import Items.Weapons.Swords.Sword;
import Items.Armors.Torso;
import Creatures.Human;

import java.awt.*;

public class Knight extends Human {

    public Knight(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        color = new Color(131, 131, 131);
        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/15, (new CriticalStrike()).getMaxLevel()))), new Evasion(Math.max(0, Math.min(lvl/15, (new Evasion()).getMaxLevel()))));
        uniqueDropItems = new Item[]{new Sword(), new Torso(), new Helmet(), new HealPotion(), new Ring(), new Axe(), new Staff()};
    }

    public Knight(){
        this(0,0,"Рыцарь",1,70);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void countStatsAfterBorn(){
        stats.setStrength(5 + (int)(Math.random()*(lvl+30) + lvl*27));
        stats.setSpeed(5 + (int)(Math.random()*(lvl+14) + lvl));
        stats.setAgility(5 + (int)(Math.random()*(lvl+12) + lvl*12));
        stats.setIntelligence(5 + (int)(Math.random()*lvl + lvl*5));
        stats.setLuck(5 + (int)(Math.random()*(lvl+4) + lvl*6));
        stats.setEloquence(5);
        stats.setBlacksmith(5);
        stats.setTheft(5);
        stats.setAlchemy(5);
        stats.setOne_handed_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*10));
        stats.setTwo_handed_weapon(5 + (int)(Math.random()*(lvl+18)));
        stats.setPole_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setChopping_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setLong_range_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);
    }

    @Override
    public Knight clone() throws CloneNotSupportedException
    {
        return (Knight) super.clone();
    }

    @Override
    public Knight getClearCopy() {
        return new Knight();
    }
}
