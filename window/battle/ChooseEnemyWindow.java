package window.battle;

import creature.LiveCreature;
import creature.Player;
import window.MultiWindow;
import window.player.UnfocusedButton;
import window.Screen;
import window.WindowInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class ChooseEnemyWindow extends JFrame implements Serializable, KeyListener{

    private final ArrayList<LiveCreature> liveCreatures = new ArrayList<>();
    private JPanel panel = new JPanel(new GridBagLayout());

    private final Player player;
    private final WindowInterface field;

    public ChooseEnemyWindow(Player player, WindowInterface field, LiveCreature ... liveCreatures){
        super("Выберите противника");
        field.addKeyListener(this);
        setAlwaysOnTop(true);

        this.player = player;
        this.field = field;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addEnemy(liveCreatures);

        drawEnemyWindow();
    }

    public void addEnemy(LiveCreature ... liveCreatures){
        Collections.addAll(this.liveCreatures, liveCreatures);
    }

    private void drawEnemyWindow(){
        getContentPane().remove(panel);

        panel = new JPanel(new GridBagLayout());
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
                MultiWindow multiWindow = field.getMultiWindow();
                FightWindow fightWindow = new FightWindow(player, liveCreature, field, multiWindow);
                multiWindow.newFight(fightWindow);
                multiWindow.switchScreen(Screen.FIGHT);
                player.getWindowInterface().getNpcController().setWaiting(true);
                field.removeKeyListener(this);
                close();
            };

            fight.addActionListener(actionListener);

            liveCreaturePanel.add(enemyName, liveCreatureConstraints);
            liveCreaturePanel.add(enemyHp, liveCreatureConstraints);
            liveCreaturePanel.add(enemyLvl, liveCreatureConstraints);
            liveCreaturePanel.add(fight, liveCreatureConstraints);

            panel.add(liveCreaturePanel, constraints);

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
                close();
            };
            fightStart.fight();
        } else if(e.getKeyCode() == KeyEvent.VK_C){
            close();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
