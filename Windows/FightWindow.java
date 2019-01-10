package JGame.Windows;

import JGame.LiveCreatures.LiveCreature;
import JGame.LiveCreatures.Player;

import javax.swing.*;
import java.awt.*;

public class FightWindow extends JFrame {

    Player player;
    LiveCreature enemy;

    public FightWindow(Player player, LiveCreature enemy){
        super("Бой");

        this.player = player;
        this.enemy = enemy;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(720,480));

        pack();
        setVisible(true);
    }
}
