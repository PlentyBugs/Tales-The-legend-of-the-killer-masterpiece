package Windows.PlayerWindows;

import Abilities.Ability;
import LiveCreatures.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class UpgradeSkillsWindow extends JFrame {

    private Player player;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints;

    public UpgradeSkillsWindow(Player player){
        super("Прокачка умений");
        this.player = player;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        drawWindow();
    }

    private void drawWindow(){

        getContentPane().remove(panel);

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 0, 10, 0);

        constraints.gridx = 0;
        constraints.gridy = 0;

        for(Ability ability : player.getAbilities()){
            JPanel skill = new JPanel(new GridBagLayout());
            GridBagConstraints skillConstraints = new GridBagConstraints();

            skillConstraints.anchor = GridBagConstraints.WEST;
            skillConstraints.insets = new Insets(0, 10, 0, 0);
            skillConstraints.gridx = 0;
            skillConstraints.gridy = 0;

            JLabel name = new JLabel(ability.getName());
            skill.add(name, skillConstraints);
            skillConstraints.gridx = 1;
            JLabel level = new JLabel("Уровень: " + Integer.toString(ability.getLevel()) + "/" + Integer.toString(ability.getMaxLevel()));
            skill.add(level, skillConstraints);


            if(ability.getLevel() < ability.getMaxLevel()){
                skillConstraints.gridx = 2;
                JButton upgrade = new JButton("Прокачать");

                upgrade.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (player.getLevelpoints() >= ability.getCost()){
                            player.setLevelpoints(player.getLevelpoints() - ability.getCost());
                            ability.setLevel(ability.getLevel()+1);
                            drawWindow();
                        }
                    }
                });

                skill.add(upgrade, skillConstraints);
            }

            panel.add(skill, constraints);
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
        drawWindow();
        setVisible(b);
    }
}
