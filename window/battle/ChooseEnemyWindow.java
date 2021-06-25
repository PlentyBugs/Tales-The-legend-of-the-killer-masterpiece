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

public class ChooseEnemyWindow extends JPanel implements Serializable, KeyListener {

    private final LiveCreature creature;
    private final MultiWindow multiWindow;

    private final Player player;
    private final WindowInterface field;

    public ChooseEnemyWindow(Player player, WindowInterface field, MultiWindow multiWindow, LiveCreature liveCreature) {
        field.getKeyControl(this);

        this.player = player;
        this.field = field;
        this.multiWindow = multiWindow;
        this.creature = liveCreature;

        drawEnemyWindow();

        field.setIsVisible(false);
        multiWindow.newChooseEnemy(this);
        multiWindow.switchScreen(Screen.ENEMY);
    }

    private void drawEnemyWindow() {

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        JPanel liveCreaturePanel = new JPanel();
        int width = 480;
        liveCreaturePanel.setPreferredSize(new Dimension(width, 40));
        GridBagConstraints liveCreatureConstraints = new GridBagConstraints();
        liveCreatureConstraints.anchor = GridBagConstraints.WEST;
        liveCreatureConstraints.insets = new Insets(20, 30, 20, 10);
        liveCreatureConstraints.gridx = 0;
        liveCreatureConstraints.gridy = 0;

        JLabel enemyName = new JLabel("Имя: " + creature.getName());
        liveCreatureConstraints.gridx = 1;
        JLabel enemyHp = new JLabel("Жизни: " + creature.getHp());
        liveCreatureConstraints.gridx = 2;
        JLabel enemyLvl = new JLabel("Уровень: " + creature.getLvl());
        liveCreatureConstraints.gridx = 3;
        JButton fight = new UnfocusedButton("В Бой");

        ActionListener actionListener = (ActionListener & Serializable) e -> fight(creature);

        fight.addActionListener(actionListener);

        liveCreaturePanel.add(enemyName, liveCreatureConstraints);
        liveCreaturePanel.add(enemyHp, liveCreatureConstraints);
        liveCreaturePanel.add(enemyLvl, liveCreatureConstraints);
        liveCreaturePanel.add(fight, liveCreatureConstraints);

        panel.add(liveCreaturePanel, constraints);

        constraints.gridy ++;

        add(panel);
        setVisible(true);
    }

    private void close(Screen screen) {
        multiWindow.removeCMW(this);
        field.returnKeyControl();
        multiWindow.switchScreen(screen);
    }

    public void setIsVisible(boolean b) {
        if (b) {
            drawEnemyWindow();
        }
        setVisible(b);
    }

    private void fight(LiveCreature creature) {
        FightWindow fightWindow = new FightWindow(player, creature, field, multiWindow);
        multiWindow.newFight(fightWindow);
        close(Screen.FIGHT);
        player.getWindowInterface().getNpcController().setWaiting(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F){
            FightStart fightStart = (FightStart & Serializable) () -> fight(creature);
            fightStart.fight();
        } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            close(Screen.GAME);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
