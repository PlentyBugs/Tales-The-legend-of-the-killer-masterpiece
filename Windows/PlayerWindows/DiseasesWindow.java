package Windows.PlayerWindows;

import Creatures.Player;
import Diseases.Danger;
import Diseases.Disease;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.Serializable;

public class DiseasesWindow extends JFrame implements Serializable {

    private Player player;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints;

    public DiseasesWindow(Player player){
        super("Болезни");

        this.player = player;
        drawWindow();
    }

    public void drawWindow(){
        panel.removeAll();
        constraints = new GridBagConstraints();

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
            switch (disease.getDanger()){
                case Danger.SAFELY: diseasePanel.setBackground(new Color(106,255, 65)); break;
                case Danger.TOLERATED: diseasePanel.setBackground(new Color(249,255, 0)); break;
                case Danger.DANGEROUSLY: diseasePanel.setBackground(new Color(255, 123, 0)); break;
                case Danger.DEADLY: diseasePanel.setBackground(new Color(255, 30, 0)); break;
            }

            JLabel name = new JLabel(disease.getName());
            diseasePanel.add(name, diseaseConstraints);
            diseaseConstraints.gridx = 1;

            panel.add(diseasePanel, constraints);
            constraints.gridy ++;
        }
        pack();
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
