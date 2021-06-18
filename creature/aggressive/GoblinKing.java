package creature.aggressive;

import abilities.passive.CriticalStrike;
import abilities.passive.Evasion;
import item.alchemy.potion.HealPotion;
import item.alchemy.potion.PoisonPotion;
import item.Item;
import item.quest.KingGoblinRing;
import item.weapon.chop.Axe;
import item.weapon.staff.Staff;
import item.weapon.sword.Sword;
import support.Property;
import support.CreatureProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GoblinKing extends Goblin {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Goblin.propertyList);
        propertyList.add(CreatureProperty.GOBLIN_KING);
    }

    public GoblinKing(int x, int y, String name, int lvl, int hp) {
        super(x, y, name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
        this.name = name;

        color = new Color(123, 150, 12);
        addAbility(new CriticalStrike(Math.max(0, Math.min(lvl/15, (new CriticalStrike()).getMaxLevel()))), new Evasion(Math.max(0, Math.min(lvl/15, (new Evasion()).getMaxLevel()))));
        uniqueDropItems = new Item[]{new Sword(), new HealPotion(), new PoisonPotion(), new KingGoblinRing(), new Axe(), new Staff()};
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
        stats.setOneHandedWeapon(5 + (int)(Math.random()*(lvl+2) + lvl*5));
        stats.setTwoHandedWeapon(5 + (int)(Math.random()*(lvl+8) + lvl*6));
        stats.setPoleWeapon(5);
        stats.setChoppingWeapon(5);
        stats.setLongRangeWeapon(5);

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
