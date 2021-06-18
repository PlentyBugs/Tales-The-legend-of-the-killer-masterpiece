package window.player;

import creature.Player;
import quest.CollectItemQuest;
import quest.KillQuest;
import quest.Quest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class QuestsWindow extends JFrame {

    private final Player player;
    private JPanel panel = new JPanel();

    public QuestsWindow(Player player){
        super("Квесты");
        int width = 520;
        int height = 240;
        setMinimumSize(new Dimension(width, height));

        this.player = player;

        drawWindow();
    }

    public void drawWindow(){
        panel.removeAll();
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for (Quest quest : player.getQuests()){
            if(!quest.getVisible()){
                continue;
            }

            JPanel questPanel = new JPanel(new GridBagLayout());
            GridBagConstraints questConstraints = new GridBagConstraints();

            questConstraints.anchor = GridBagConstraints.NORTH;
            questConstraints.insets = new Insets(0, 20, 0, 0);
            questConstraints.gridx = 0;
            questConstraints.gridy = 0;

            JLabel title = new JLabel(quest.getTitle());
            questPanel.add(title, questConstraints);
            questConstraints.gridx ++;
            JLabel goldReward = new JLabel("Деньги: " + quest.getGoldReward());
            questPanel.add(goldReward, questConstraints);
            questConstraints.gridx ++;
            JLabel expReward = new JLabel("Опыт: " + quest.getExpReward());
            questPanel.add(expReward, questConstraints);
            questConstraints.gridx ++;
            if(quest instanceof KillQuest killQuest) {
                JLabel questGoal = new JLabel("Цель: " + killQuest.getEnemyCountToKillCurrent() + "/" + killQuest.getEnemyCountToKill());
                questPanel.add(questGoal, questConstraints);
                questConstraints.gridx ++;
            }
            if(quest instanceof CollectItemQuest collectItemQuest) {
                JLabel questGoal = new JLabel("Цель: " + collectItemQuest.getItemCountCurrent() + "/" + collectItemQuest.getItemCount());
                questPanel.add(questGoal, questConstraints);
                questConstraints.gridx ++;
            }
            JLabel questEmployer = new JLabel("Квестодатель: " + quest.getEmployerName());
            questPanel.add(questEmployer, questConstraints);

            panel.add(questPanel, constraints);

            constraints.gridy ++;
        }
        pack();
        if(player != null && player.getWindowInterface() != null) player.getWindowInterface().drawMap();
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        drawWindow();
    }

    public JPanel getPanel() {
        return panel;
    }
}
