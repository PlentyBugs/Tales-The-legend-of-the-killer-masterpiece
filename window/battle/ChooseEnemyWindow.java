package window.battle;

import creature.LiveCreature;
import creature.Player;
import window.MultiWindow;
import window.Screen;
import window.WindowInterface;
import window.player.UnfocusedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class ChooseEnemyWindow extends JPanel implements Serializable, KeyListener {

    private final ArrayList<LiveCreature> liveCreatures = new ArrayList<>();
    private final MultiWindow multiWindow;

    private final Player player;
    private final WindowInterface field;

    public ChooseEnemyWindow(Player player, WindowInterface field, MultiWindow multiWindow, LiveCreature ... liveCreatures) {
        field.addKeyListener(this);

        this.player = player;
        this.field = field;
        this.multiWindow = multiWindow;

        addEnemy(liveCreatures);

        drawEnemyWindow();

        multiWindow.newChooseEnemy(this);
    }

    public void addEnemy(LiveCreature ... liveCreatures){
        Collections.addAll(this.liveCreatures, liveCreatures);
    }

    private void drawEnemyWindow() {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for(LiveCreature liveCreature : liveCreatures){

            JPanel liveCreaturePanel = new JPanel();
            int width = 480;
            liveCreaturePanel.setPreferredSize(new Dimension(width, 40));
            GridBagConstraints liveCreatureConstraints = new GridBagConstraints();
            liveCreatureConstraints.anchor = GridBagConstraints.WEST;
            liveCreatureConstraints.insets = new Insets(20, 30, 20, 10);
            liveCreatureConstraints.gridx = 0;
            liveCreatureConstraints.gridy = 0;

            JLabel enemyName = new JLabel("Имя: " + liveCreature.getName());
            liveCreatureConstraints.gridx = 1;
            JLabel enemyHp = new JLabel("Жизни: " + liveCreature.getHp());
            liveCreatureConstraints.gridx = 2;
            JLabel enemyLvl = new JLabel("Уровень: " + liveCreature.getLvl());
            liveCreatureConstraints.gridx = 3;
            JButton fight = new UnfocusedButton("В Бой");

            ActionListener actionListener = (ActionListener & Serializable) e -> {
                FightWindow fightWindow = new FightWindow(player, liveCreature, field, multiWindow);
                multiWindow.newFight(fightWindow);
                close(Screen.FIGHT);
                player.getWindowInterface().getNpcController().setWaiting(true);
                field.removeKeyListener(this);
            };

            fight.addActionListener(actionListener);

            liveCreaturePanel.add(enemyName, liveCreatureConstraints);
            liveCreaturePanel.add(enemyHp, liveCreatureConstraints);
            liveCreaturePanel.add(enemyLvl, liveCreatureConstraints);
            liveCreaturePanel.add(fight, liveCreatureConstraints);

            panel.add(liveCreaturePanel, constraints);

            constraints.gridy ++;
        }

        add(panel);
        setVisible(true);
    }

    private void close(Screen screen) {
        multiWindow.removeWindow(this);
        multiWindow.switchScreen(screen);
    }

    public void setIsVisible(boolean b) {
        drawEnemyWindow();
        setVisible(b);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F){
            FightStart fightStart = (FightStart & Serializable) () -> {
                player.getWindowInterface().getNpcController().setWaiting(true);
                close(Screen.FIGHT);
            };
            fightStart.fight();
        } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            close(Screen.GAME);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
