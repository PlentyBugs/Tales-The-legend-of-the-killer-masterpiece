package Windows.PlayerWindows;

import LiveCreatures.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class PlayerInfoWindow extends JFrame {

    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints;
    private Player player;

    public PlayerInfoWindow(Player player){
        super("Информация");

        this.player = player;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        drawInfo();
    }

    private void drawInfo(){

        getContentPane().remove(panel);

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;

        JLabel hp = new JLabel("Жизни: ");
        panel.add(hp, constraints);
        constraints.gridy = 1;
        JLabel money = new JLabel("Деньги: ");
        panel.add(money, constraints);
        constraints.gridy = 2;
        JLabel exp = new JLabel("Опыт: ");
        panel.add(exp, constraints);
        constraints.gridy = 3;
        JLabel needExp = new JLabel("Необходимо опыта: ");
        panel.add(needExp, constraints);
        constraints.gridy = 4;
        JLabel lvl = new JLabel("Уровень: ");
        panel.add(lvl, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        JLabel hpCount = new JLabel(Integer.toString(player.getHp()) + "\\" + Integer.toString(player.getMaxHp()));
        panel.add(hpCount, constraints);
        constraints.gridy = 1;
        JLabel moneyCount = new JLabel(Integer.toString(player.getMoney()));
        panel.add(moneyCount, constraints);
        constraints.gridy = 2;
        JLabel expCount = new JLabel(Integer.toString(player.getExp()));
        panel.add(expCount, constraints);
        constraints.gridy = 3;
        JLabel needExpCount = new JLabel(Integer.toString(player.getNeedExpToNextLvl()));
        panel.add(needExpCount, constraints);
        constraints.gridy = 4;
        JLabel lvlCount = new JLabel(Integer.toString(player.getLvl()));
        panel.add(lvlCount, constraints);

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        drawInfo();
        setVisible(b);
    }
}
