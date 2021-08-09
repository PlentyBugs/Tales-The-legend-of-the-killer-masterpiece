package window.player;

import abilities.Ability;
import creature.Player;
import window.menu.AbstractMenu;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class UpgradeSkillsWindow extends AbstractMenu implements Serializable, PlayerPanel {

    private final Player player;
    @Serial
    private static final long serialVersionUID = 4460300534250353120L;

    public UpgradeSkillsWindow(Player player) {
        this.player = player;
        setLayout(new GridBagLayout());
        drawWindow();
    }

    public JPanel drawWindow() {
        removeAll();
        GridBagConstraints constraints = new GridBagConstraints();

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
            JLabel level = new JLabel("Уровень: " + ability.getLevel() + "/" + ability.getMaxLevel());
            skill.add(level, skillConstraints);


            if(ability.getLevel() < ability.getMaxLevel()){
                skillConstraints.gridx = 2;
                JButton upgrade = new UnfocusedButton("Прокачать");

                upgrade.addActionListener(e -> {
                    if (player.getLevelPoints() >= ability.getCost()){
                        int cost = ability.getCost();
                        int lastlevel = ability.getLevel();
                        ability.levelUp(player);
                        if(ability.getLevel()-lastlevel > 0){
                            player.setLevelPoints(player.getLevelPoints() - cost);
                            drawWindow();
                        }
                    }
                });

                skill.add(upgrade, skillConstraints);
            }

            add(skill, constraints);
            constraints.gridy ++;
        }
        return this;
    }
}
