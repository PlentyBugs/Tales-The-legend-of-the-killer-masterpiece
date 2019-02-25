package Windows.PlayerWindows;

import Abilities.Ability;
import Creatures.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

public class UpgradeSkillsWindow extends JFrame implements Serializable {

    private Player player;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints;
    private static final long serialVersionUID = 4460300534250353120L;

    public UpgradeSkillsWindow(Player player){
        super("Прокачка умений");
        this.player = player;setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {}

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {
                player.setAbilityWindowOpen(true);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                player.setAbilityWindowOpen(false);
            }
        });

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
                            int lastlevel = ability.getLevel();
                            ability.levelUp(player);
                            if(ability.getLevel()-lastlevel > 0){
                                player.setLevelpoints(player.getLevelpoints() - ability.getCost());
                                drawWindow();
                            }
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
