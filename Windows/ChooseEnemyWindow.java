package JGame.Windows;

import JGame.LiveCreatures.LiveCreature;
import JGame.LiveCreatures.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ChooseEnemyWindow extends JFrame {

    private int width = 480;
    private int heigt = 360;

    private ArrayList<LiveCreature> liveCreatures = new ArrayList<LiveCreature>();
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints;

    Player player;

    public ChooseEnemyWindow(Player player, LiveCreature ... liveCreatures){
        super("Выберите противника");

        this.player = player;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addEnemy(liveCreatures);

        drawEnemyWindow();
    }

    public void addEnemy(LiveCreature ... liveCreatures){
        for (LiveCreature liveCreature : liveCreatures){
            this.liveCreatures.add(liveCreature);
        }
    }

    private void drawEnemyWindow(){
        getContentPane().remove(panel);

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for(LiveCreature liveCreature : liveCreatures){

            JPanel liveCreaturePanel = new JPanel();
            liveCreaturePanel.setPreferredSize(new Dimension(width, 40));
            GridBagConstraints liveCreatureConstraints = new GridBagConstraints();
            liveCreatureConstraints.anchor = GridBagConstraints.WEST;
            liveCreatureConstraints.insets = new Insets(20, 30, 20, 10);
            liveCreatureConstraints.gridx = 0;
            liveCreatureConstraints.gridy = 0;

            JLabel enemyName = new JLabel("Имя: " + liveCreature.getName());
            liveCreatureConstraints.gridx = 1;
            JLabel enemyHp = new JLabel("Жизни: " + Integer.toString(liveCreature.getHp()));
            liveCreatureConstraints.gridx = 2;
            JLabel enemyLvl = new JLabel("Уровень: " + Integer.toString(liveCreature.getLvl()));
            liveCreatureConstraints.gridx = 3;
            JButton fight = new JButton("В Бой");

            fight.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    FightWindow fightWindow = new FightWindow(player, liveCreature);
                    close();
                }
            });

            liveCreaturePanel.add(enemyName, liveCreatureConstraints);
            liveCreaturePanel.add(enemyHp, liveCreatureConstraints);
            liveCreaturePanel.add(enemyLvl, liveCreatureConstraints);
            liveCreaturePanel.add(fight, liveCreatureConstraints);

            panel.add(liveCreaturePanel, constraints);

            constraints.gridy ++;
        }

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        drawEnemyWindow();
        setVisible(b);
    }
}
