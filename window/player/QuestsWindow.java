package window.player;

import creature.Player;
import quest.CollectItemQuest;
import quest.KillQuest;
import quest.Quest;
import window.menu.AbstractMenu;

import javax.swing.*;
import java.awt.*;

public class QuestsWindow extends AbstractMenu implements PlayerPanel {

    private final Player player;

    public QuestsWindow(Player player) {
        this.player = player;
        setLayout(new GridBagLayout());
        drawWindow();
    }

    public JPanel drawWindow() {
        removeAll();
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

            add(questPanel, constraints);

            constraints.gridy ++;
        }
        return this;
    }
}
