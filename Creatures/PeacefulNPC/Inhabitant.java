package Creatures.PeacefulNPC;

import Conversations.Conversation;

import java.awt.*;

public class Inhabitant extends Peaceful {

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
        stats.setEloquence(20);

        conversation = new Conversation();

        if(talkative){
            initializeWindowConv();
        }
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
