package window.player;

import creature.Player;
import disease.Danger;
import disease.Disease;
import window.menu.AbstractMenu;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class DiseasesWindow extends AbstractMenu implements Serializable, PlayerPanel {

    private final Player player;

    public DiseasesWindow(Player player) {
        this.player = player;
        setLayout(new GridBagLayout());
        drawWindow();
    }

    public JPanel drawWindow(){
        removeAll();
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 0, 10, 0);

        constraints.gridx = 0;
        constraints.gridy = 0;

        for(Disease disease : player.getDiseases()){
            JPanel diseasePanel = new JPanel(new GridBagLayout());
            GridBagConstraints diseaseConstraints = new GridBagConstraints();

            diseaseConstraints.anchor = GridBagConstraints.WEST;
            diseaseConstraints.insets = new Insets(0, 10, 0, 0);
            diseaseConstraints.gridx = 0;
            diseaseConstraints.gridy = 0;
            switch (disease.getDanger()) {
                case Danger.SAFELY -> diseasePanel.setBackground(new Color(106, 255, 65));
                case Danger.TOLERATED -> diseasePanel.setBackground(new Color(249, 255, 0));
                case Danger.DANGEROUSLY -> diseasePanel.setBackground(new Color(255, 123, 0));
                case Danger.DEADLY -> diseasePanel.setBackground(new Color(255, 30, 0));
            }

            JLabel name = new JLabel(disease.getName());
            diseasePanel.add(name, diseaseConstraints);
            diseaseConstraints.gridx = 1;

            add(diseasePanel, constraints);
            constraints.gridy ++;
        }
        return this;
    }
}
