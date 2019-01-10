package JGame;

import JGame.LiveCreatures.LiveCreature;

import java.util.ArrayList;

public class Battle {
    ArrayList Enemies = new ArrayList();
    ArrayList Friends = new ArrayList();
    public void addFoe(LiveCreature... liveCreatures){
        for (LiveCreature liveCreature : liveCreatures){
            Enemies.add(liveCreature);
        }
    }

    public void addFriend(LiveCreature ... liveCreatures){
        for (LiveCreature liveCreature : liveCreatures){
            Friends.add(liveCreature);
        }
    }

    public void battle(){

    }
}
