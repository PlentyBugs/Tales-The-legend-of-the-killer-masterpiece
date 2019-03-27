package Windows.BattleWindows;

import Creatures.GodCreature;

import javax.swing.*;

public class GodCreatureButton extends JButton {
    private GodCreature godCreature;

    public GodCreatureButton(GodCreature godCreature){
        this.godCreature = godCreature;
    }

    public GodCreature getGodCreature() {
        return godCreature;
    }
}
