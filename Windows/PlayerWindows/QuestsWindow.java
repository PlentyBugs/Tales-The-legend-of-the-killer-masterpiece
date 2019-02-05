package Windows.PlayerWindows;

import LiveCreatures.Player;
import Quests.Quest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class QuestsWindow extends JFrame {

    private Player player;
    private JPanel panel = new JPanel();
    private GridBagConstraints constraints;
    private int width = 480;
    private int height = 240;

    public QuestsWindow(Player player){
        super("Квесты");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(width, height));

        this.player = player;

        drawWindow();
    }

    private void drawWindow(){
        getContentPane().remove(panel);

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for (Quest quest : player.getQuests()){

            JPanel questPanel = new JPanel(new GridBagLayout());
            GridBagConstraints questConstraints = new GridBagConstraints();

            questConstraints.anchor = GridBagConstraints.NORTH;
            questConstraints.insets = new Insets(0, 20, 0, 0);
            questConstraints.gridx = 0;
            questConstraints.gridy = 0;

            JLabel title = new JLabel(quest.getTitle());
            questPanel.add(title, questConstraints);
            questConstraints.gridx ++;
            JLabel goldReward = new JLabel("Деньги: " + Integer.toString(quest.getGoldReward()));
            questPanel.add(goldReward, questConstraints);
            questConstraints.gridx ++;
            JLabel expReward = new JLabel("Опыт: " + Integer.toString(quest.getExpReward()));
            questPanel.add(expReward, questConstraints);
            questConstraints.gridx ++;

            panel.add(questPanel, constraints);

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
        setVisible(b);
        if(b){
            drawWindow();
        }
    }
}
