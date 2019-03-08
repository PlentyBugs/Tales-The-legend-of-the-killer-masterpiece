package Creatures.AggressiveNPC;

import Abilities.Active.DamageUp;
import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Creatures.Human;
import Items.Alchemy.Potions.HealPotion;
import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Armors.Torso;
import Items.Item;
import Items.Weapons.Bows.Bow;
import Items.Weapons.Bows.LongBow;
import Items.Weapons.Choppings.Axe;
import Items.Weapons.Staffs.Staff;
import Items.Weapons.Swords.Sword;

import java.awt.*;

public class Bandit extends Human {

    public Bandit(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        color = Color.RED;
        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/15, (new CriticalStrike()).getMaxLevel()))), new DamageUp(Math.max(0, Math.min(lvl/20, (new DamageUp()).getMaxLevel()))), new Evasion(Math.max(0, Math.min(lvl/12, (new Evasion()).getMaxLevel()))));

        uniqueDropItems = new Item[]{new Sword(), new Torso(), new Helmet(), new HealPotion(), new Bow(), new Ring(), new LongBow(), new Axe(), new Staff()};
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
        stats.setStrength(5 + (int)(Math.random()*(lvl+6) + lvl*9));
        stats.setSpeed(5 + (int)(Math.random()*(lvl+1) + lvl));
        stats.setAgility(5 + (int)(Math.random()*(lvl+3) + lvl*4));
        stats.setIntelligence(5 + (int)(Math.random()*lvl + lvl*5));
        stats.setLuck(5 + (int)(Math.random()*(lvl+4) + lvl*6));
        stats.setEloquence(5);
        stats.setBlacksmith(5);
        stats.setTheft(5);
        stats.setAlchemy(5);
        stats.setOne_handed_weapon(5 + (int)(Math.random()*(lvl+2) + lvl));
        stats.setTwo_handed_weapon(5 + (int)(Math.random()*(lvl+2)));
        stats.setPole_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setChopping_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setLong_range_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);
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
