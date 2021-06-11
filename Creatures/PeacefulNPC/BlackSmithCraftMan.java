package Creatures.PeacefulNPC;

import Conversations.Conversation;
import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BlackSmithCraftMan extends Peaceful {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Peaceful.propertyList);
        propertyList.add(GeneralProperty.BLACKSMITH_CRAFT_MAN);
    }

    public BlackSmithCraftMan(){
        this(0,0,"Кузнец",1,100);
    }

    public BlackSmithCraftMan(int x, int y, String name, int lvl, int hp) {
        super(x, y, "Кузнец " + name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
        talkative = true;

        this.name = name;
        color = new Color(182, 108, 55);

        stats.setStrength(2150);
        stats.setSpeed(3);
        stats.setAgility(5);
        stats.setIntelligence(310);
        stats.setLuck(5);
        stats.setEloquence(150);
        stats.setBlacksmith(450);
        stats.setTheft(5);
        stats.setAlchemy(5);
        stats.setOne_handed_weapon(5);
        stats.setTwo_handed_weapon(5);
        stats.setPole_weapon(5);
        stats.setChopping_weapon(5);
        stats.setLong_range_weapon(5);

        stats.setKnowledge(0);
        stats.setEnergy(0);

        stats.setMilitarism(0);
        stats.setPacifism(0);

        conversation = new Conversation();

        if(talkative){
            initializeWindowConv();
        }
    }

    @Override
    public Dealer clone() throws CloneNotSupportedException
    {
        return (Dealer) super.clone();
    }

    @Override
    public Dealer getClearCopy() {
        return new Dealer();
    }
}
