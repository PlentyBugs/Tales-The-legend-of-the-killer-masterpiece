package Creatures.AggressiveNPC.Animals;

import Abilities.Active.DamageUp;
import Abilities.Active.DecreaseDamage;
import Abilities.Passive.CriticalStrike;
import Abilities.Passive.Evasion;
import Creatures.LiveCreature;
import Diseases.Rabies;
import Items.Alchemy.Potions.HealPotion;
import Items.Armors.Ring;
import Items.BlackSmith.Resource.Leather;
import Items.Item;
import Items.Key;

import java.awt.*;

public class Wolf extends Animal {

    public Wolf(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        color = new Color(197, 139, 255);
        diseases.add(new Rabies());

        uniqueDropItems = new Item[]{
                new HealPotion(),
                new Ring(),
                new Key(),
                new Leather()
        };
    }

    public Wolf(){
        this(0,0,"Волк",1,70);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void countStatsAfterBorn(){
        stats.setStrength(5 + (int)(Math.random()*(lvl*3) + lvl*9));
        stats.setSpeed(5 + (int)(Math.random()*(lvl*20) + lvl*5));
        stats.setAgility(5 + (int)(Math.random()*(lvl*16) + lvl*6));
        stats.setIntelligence(5 + (int)(Math.random()*lvl + lvl*2));
        stats.setLuck(5 + (int)(Math.random()*(lvl*3) + lvl*2));
        stats.setEloquence(0);
        stats.setBlacksmith(0);
        stats.setTheft(0);
        stats.setAlchemy(0);
        stats.setOne_handed_weapon(0);
        stats.setTwo_handed_weapon(0);
        stats.setPole_weapon(0);
        stats.setChopping_weapon(0);
        stats.setLong_range_weapon(0);

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);

        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/2, (new CriticalStrike()).getMaxLevel()))),
                new DamageUp(Math.max(0, Math.min(lvl/30, (new DamageUp()).getMaxLevel()))),
                new DecreaseDamage(Math.max(0, Math.min(lvl/30, (new DecreaseDamage()).getMaxLevel()))),
                new Evasion(Math.max(0, Math.min(lvl/4, (new Evasion()).getMaxLevel()))));
    }

    @Override
    public Wolf clone() throws CloneNotSupportedException
    {
        return (Wolf) super.clone();
    }

    @Override
    public Wolf getClearCopy() {
        return new Wolf();
    }

    public void useRacePower(LiveCreature enemy){
        if(Math.random()*10 < 15.0)
            enemy.addDisease(diseases.get((int)(diseases.size()*Math.random())));
    }
}
