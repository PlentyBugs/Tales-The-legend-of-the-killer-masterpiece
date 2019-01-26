package Windows.BattleWindows;

import Abilities.Ability;
import Abilities.AbilityTarget;
import Abilities.AbilityType;
import Abilities.Active.AbilityActive;
import LiveCreatures.LiveCreature;
import LiveCreatures.Player;

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
                JLabel abilityCountBuffSteps = new JLabel("Количество ходов: " + Integer.toString(((AbilityActive)ability).getBuff().getStepCount()-1));
                abilityPanel.add(abilityCountBuffSteps, abilityConstraints);
                abilityConstraints.gridx ++;
            }

            JButton abilityUseButton = new JButton("Использовать");
            abilityPanel.add(abilityUseButton, abilityConstraints);
            abilityConstraints.gridx ++;

            abilityUseButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fightWindow.writeToPlayerConsole(player.getName() + " использовал " + ability.getName());
                    if (ability.getAbilityType().contains(AbilityType.BUFF)){
                        if (((AbilityActive)ability).getAbilityTarget() == AbilityTarget.PLAYER){
                            fightWindow.writeToPlayerConsole("На " + player.getName() + " наложен эффект " + ((AbilityActive) ability).getBuff().getName());
                            player.addBuffs(((AbilityActive) ability).getBuff());
                        } else if (((AbilityActive)ability).getAbilityTarget() == AbilityTarget.ENEMY){
                            fightWindow.writeToPlayerConsole("На " + enemy.getName() + " наложен эффект " + ((AbilityActive) ability).getBuff().getName());
                            enemy.addBuffs(((AbilityActive) ability).getBuff());
                        }
                    }
                    if (((AbilityActive)ability).getAbilityTarget() == AbilityTarget.ENEMY){
                        ((AbilityActive)ability).use(enemy);
                    }
                    fightWindow.enemyTurn();
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
