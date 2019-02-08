package LiveCreatures.PeacefulNPC;

import Conversations.Conversation;

import java.awt.*;

public class Dealer extends Peaceful {

    public Dealer(int x, int y, String name, int lvl, int hp) {
        super(x, y, "Торговец " + name, lvl, hp);

        maxHp = hp;
        this.hp = maxHp;
        this.lvl = lvl;
        talkative = true;

        this.name = name;
        color = new Color(255, 100, 204);

        stats.strength = 510;
        stats.speed = 120;
        stats.intelligence = 310;
        stats.luck = 210;
        stats.eloquence = 650;

        conversation = new Conversation();

        if(talkative){
            initializeWindowConv();
        }
    }
}
