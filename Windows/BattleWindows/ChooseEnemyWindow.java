package Windows.BattleWindows;

import Creatures.LiveCreature;
import Creatures.Player;
import Windows.FieldWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;

public class ChooseEnemyWindow extends JFrame implements Serializable, KeyListener{

    private int width = 480;
    private int height = 360;

    private ArrayList<LiveCreature> liveCreatures = new ArrayList<LiveCreature>();
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints;
    private LiveCreature liveCreature;

    private ActionListener actionListener;

    private Player player;
    private FieldWindow field;

    public ChooseEnemyWindow(Player player, FieldWindow field, LiveCreature ... liveCreatures){
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
        for (LiveCreature liveCreature : liveCreatures){
            this.liveCreatures.add(liveCreature);
        }
    }

    private void drawEnemyWindow(){
        getContentPane().remove(panel);

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for(LiveCreature liveCreature : liveCreatures){

            JPanel liveCreaturePanel = new JPanel();
            liveCreaturePanel.setPreferredSize(new Dimension(width, 40));
            GridBagConstraints liveCreatureConstraints = new GridBagConstraints();
            liveCreatureConstraints.anchor = GridBagConstraints.WEST;
            liveCreatureConstraints.insets = new Insets(20, 30, 20, 10);
            liveCreatureConstraints.gridx = 0;
            liveCreatureConstraints.gridy = 0;

            JLabel enemyName = new JLabel("Имя: " + liveCreature.getName());
            liveCreatureConstraints.gridx = 1;
            JLabel enemyHp = new JLabel("Жизни: " + Double.toString(liveCreature.getHp()));
            liveCreatureConstraints.gridx = 2;
            JLabel enemyLvl = new JLabel("Уровень: " + Integer.toString(liveCreature.getLvl()));
            liveCreatureConstraints.gridx = 3;
            JButton fight = new JButton("В Бой");

            this.liveCreature = liveCreature;
            actionListener = e -> {
                new FightWindow(player, liveCreature, field);
                player.getFieldWindow().getNpcController().setWaiting(true);
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
            FightStart fightStart = () -> {
                FightWindow fightWindow = new FightWindow(player, liveCreature, field);
                player.getFieldWindow().getNpcController().setWaiting(true);
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
