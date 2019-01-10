package JGame.Windows;

import JGame.LiveCreatures.LiveCreature;

import javax.swing.*;
import java.util.ArrayList;

public class ChooseEnemyWindow extends JFrame {

    private ArrayList<LiveCreature> liveCreatures = new ArrayList<LiveCreature>();

    public ChooseEnemyWindow(LiveCreature ... liveCreatures){
        addEnemy(liveCreatures);
    }

    public void addEnemy(LiveCreature ... liveCreatures){
        for (LiveCreature liveCreature : liveCreatures){
            this.liveCreatures.add(liveCreature);
        }
    }
}
