package creature.aggressive;

import abilities.passive.CriticalStrike;
import abilities.passive.Evasion;
import creature.Human;
import item.alchemy.potion.HealPotion;
import item.armor.Helmet;
import item.armor.Ring;
import item.armor.Torso;
import item.Item;
import item.Key;
import item.weapon.chop.Axe;
import item.weapon.staff.Staff;
import item.weapon.sword.Sword;
import support.Property;
import support.CreatureProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Human implements Aggressive {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Human.propertyList);
        propertyList.add(CreatureProperty.KNIGHT);
    }

    public Knight(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;

        this.name = name;
        color = new Color(131, 131, 131);
        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/15, (new CriticalStrike()).getMaxLevel()))), new Evasion(Math.max(0, Math.min(lvl/15, (new Evasion()).getMaxLevel()))));
        uniqueDropItems = new Item[]{
                new Sword(),
                new Torso(),
                new Helmet(),
                new HealPotion(),
                new Ring(),
                new Axe(),
                new Staff(),
                new Key()
        };
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
        stats.setOneHandedWeapon(5 + (int)(Math.random()*(lvl+2) + lvl*10));
        stats.setTwoHandedWeapon(5 + (int)(Math.random()*(lvl+18)));
        stats.setPoleWeapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setChoppingWeapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));
        stats.setLongRangeWeapon(5 + (int)(Math.random()*(lvl+2) + lvl*3));

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);
        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/15, (new CriticalStrike()).getMaxLevel()))), new Evasion(Math.max(0, Math.min(lvl/15, (new Evasion()).getMaxLevel()))));
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
