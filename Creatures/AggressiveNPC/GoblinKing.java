package Creatures.AggressiveNPC;

import Items.Item;
import Items.Alchemy.Potions.HealPotion;
import Items.Alchemy.Potions.PoisonPotion;
import Items.QuestItems.KingGoblinRing;
import Items.Weapons.Swords.Sword;

import java.awt.*;

public class GoblinKing extends Goblin {
    public GoblinKing(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
        this.name = name;

        color = new Color(123, 150, 12);
        uniqueDropItems = new Item[]{new Sword(), new HealPotion(), new PoisonPotion(), new KingGoblinRing()};
        countStatsAfterBorn();
    }

    public GoblinKing(){
        this(0,0, "Король гоблинов",40,12530);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void countStatsAfterBorn(){
        stats.setStrength(5 + (int)(Math.random()*(lvl+2) + lvl*7));
        stats.setSpeed(5 + (int)(Math.random()*(lvl+2) + lvl*6));
        stats.setAgility(5 + (int)(Math.random()*(lvl+2) + lvl*5));
        stats.setIntelligence(5);
        stats.setLuck(5);
        stats.setEloquence(5);
        stats.setBlacksmith(5);
        stats.setTheft(5);
        stats.setAlchemy(5);
        stats.setOne_handed_weapon(5 + (int)(Math.random()*(lvl+2) + lvl*5));
        stats.setTwo_handed_weapon(5 + (int)(Math.random()*(lvl+8) + lvl*6));
        stats.setPole_weapon(5);
        stats.setChopping_weapon(5);
        stats.setLong_range_weapon(5);

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);
    }

    @Override
    public GoblinKing clone() throws CloneNotSupportedException
    {
        return (GoblinKing) super.clone();
    }

    @Override
    public GoblinKing getClearCopy() {
        return new GoblinKing();
    }
}
