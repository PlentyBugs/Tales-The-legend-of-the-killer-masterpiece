package window.battle;

import abilities.Ability;
import abilities.AbilityType;
import abilities.active.ActiveAbility;
import abilities.CostType;
import creature.LiveCreature;
import creature.Player;
import window.player.UnfocusedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;

public class PlayerAbilityWindow extends JFrame implements Serializable {

    private Player player;
    private LiveCreature enemy;
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints;
    private FightWindow fightWindow;

    public PlayerAbilityWindow(Player player, LiveCreature enemy, FightWindow fightWindow){
        super("Способности");
        this.player = player;
        this.enemy = enemy;
        this.fightWindow = fightWindow;

        drawWindow();
    }

    public void drawWindow(){
        getContentPane().remove(panel);

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for (Ability ability : player.getAbilitiesByType(AbilityType.ACTIVE)){

            JPanel abilityPanel = new JPanel(new GridBagLayout());
            GridBagConstraints abilityConstraints = new GridBagConstraints();

            abilityConstraints.anchor = GridBagConstraints.NORTH;
            abilityConstraints.insets = new Insets(5, 5, 5, 5);
            abilityConstraints.gridx = 0;
            abilityConstraints.gridy = 0;

            JLabel abilityName = new JLabel(ability.getName());
            abilityPanel.add(abilityName, abilityConstraints);
            abilityConstraints.gridx ++;

            if (ability.getAbilityType().contains(AbilityType.BUFF)){
                JLabel abilityCountBuffSteps = new JLabel("Количество ходов: " + Integer.toString(((ActiveAbility)ability).getBuff().getStepCount()-1));
                abilityPanel.add(abilityCountBuffSteps, abilityConstraints);
                abilityConstraints.gridx ++;
            }

            if (ability.getAbilityType().contains(AbilityType.ACTIVE)){
                JLabel abilityCost = new JLabel();
                int cost = ((ActiveAbility)ability).getUseCost();
                if(player.getEquipment().staffEquip()){
                    cost = 0;
                }
                if(ability.getCostType() == CostType.HEALTH){
                    abilityCost = new JLabel("Стоит " + cost + "% ХП");
                } else if(ability.getCostType() == CostType.ENERGY){
                    abilityCost = new JLabel("Стоит " + cost + "% Энергии");
                } else if(ability.getCostType() == CostType.MONEY){
                    abilityCost = new JLabel("Стоит " + cost + " золотых");
                }
                abilityPanel.add(abilityCost, abilityConstraints);
                abilityConstraints.gridx ++;
            }

            JLabel abilityTarget = new JLabel("Цель: " + ((ActiveAbility)ability).getAbilityTarget());
            abilityPanel.add(abilityTarget, abilityConstraints);
            abilityConstraints.gridx ++;

            JButton abilityUseButton = new UnfocusedButton("Использовать");
            abilityPanel.add(abilityUseButton, abilityConstraints);
            abilityConstraints.gridx ++;

            abilityUseButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fightWindow.attackBySpell(player, enemy, ability);
                }
            });
            panel.add(abilityPanel, constraints);
            constraints.gridy ++;
        }

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }
    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
