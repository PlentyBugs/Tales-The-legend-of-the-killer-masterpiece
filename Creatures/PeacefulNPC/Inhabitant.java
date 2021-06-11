package Creatures.PeacefulNPC;

import Conversations.Conversation;
import support.Property;
import support.GeneralProperty;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Inhabitant extends Peaceful {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Peaceful.propertyList);
        propertyList.add(GeneralProperty.INHABITANT);
    }

    public static final Inhabitant commonInhabitant = new Inhabitant(0,0, "", 0,0);

    public Inhabitant(){
        this(0,0,"Житель",1,100);
    }


    public Inhabitant(int x, int y, String name, int lvl, int hp) {
        super(x, y, "Житель " + name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
        talkative = true;

        this.name = name;
        color = new Color(223, 196, 69);

        stats.setStrength(15);
        stats.setSpeed(2);
        stats.setIntelligence(12);
        stats.setLuck(0);
        stats.setEloquence(70);

        conversation = new Conversation();

        if(talkative){
            initializeWindowConv();
        }
    }

    public static Inhabitant getInstance() {
        return commonInhabitant;
    }

    @Override
    public Inhabitant clone() throws CloneNotSupportedException
    {
        return (Inhabitant) super.clone();
    }

    @Override
    public Inhabitant getClearCopy() {
        return new Inhabitant();
    }
}
