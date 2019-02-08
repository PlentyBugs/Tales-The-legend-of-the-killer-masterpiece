package LiveCreatures.PeacefulNPC;

import Conversations.Conversation;

import java.awt.*;

public class Inhabitant extends Peaceful {

    public Inhabitant(int x, int y, String name, int lvl, int hp) {
        super(x, y, "Житель " + name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
        talkative = true;

        this.name = name;
        color = new Color(223, 196, 69);

        stats.strength = 15;
        stats.speed = 2;
        stats.intelligence = 12;
        stats.luck = 0;
        stats.eloquence = 20;

        conversation = new Conversation();

        if(talkative){
            initializeWindowConv();
        }
    }
}
