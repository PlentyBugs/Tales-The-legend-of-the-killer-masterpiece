package window.player;

import creature.Player;
import window.menu.AbstractMenu;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class PlayerInfoWindow extends AbstractMenu implements Serializable, PlayerPanel {

    private final Player player;
    @Serial
    private static final long serialVersionUID = -3370586347546836372L;

    public PlayerInfoWindow(Player player){
        this.player = player;
        setLayout(new GridBagLayout());
        drawWindow();
    }

    public JPanel drawWindow() {
        removeAll();
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;

        JLabel hp = new JLabel("Жизни: ");
        add(hp, constraints);
        constraints.gridy = 1;
        JLabel money = new JLabel("Деньги: ");
        add(money, constraints);
        constraints.gridy = 2;
        JLabel exp = new JLabel("Опыт: ");
        add(exp, constraints);
        constraints.gridy = 3;
        JLabel needExp = new JLabel("Необходимо опыта: ");
        add(needExp, constraints);
        constraints.gridy = 4;
        JLabel lvl = new JLabel("Уровень: ");
        add(lvl, constraints);
        constraints.gridy = 5;
        JLabel levelpoints = new JLabel("Очки способностей: ");
        add(levelpoints, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        JLabel hpCount = new JLabel(Math.round(player.getHp() * 100.0) / 100.0 + "\\" + player.getMaxHp());
        add(hpCount, constraints);
        constraints.gridy = 1;
        JLabel moneyCount = new JLabel(Integer.toString(player.getMoney()));
        add(moneyCount, constraints);
        constraints.gridy = 2;
        JLabel expCount = new JLabel(Integer.toString(player.getExp()));
        add(expCount, constraints);
        constraints.gridy = 3;
        JLabel needExpCount = new JLabel(Integer.toString(player.getNeedExpToNextLvl()));
        add(needExpCount, constraints);
        constraints.gridy = 4;
        JLabel lvlCount = new JLabel(Integer.toString(player.getLvl()));
        add(lvlCount, constraints);
        constraints.gridy = 5;
        JLabel lvlPointsCount = new JLabel(Integer.toString(player.getLevelPoints()));
        add(lvlPointsCount, constraints);
        return this;
    }
}
