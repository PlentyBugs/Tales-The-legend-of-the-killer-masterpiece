package window.player;

import creature.Player;
import creature.StatsEnum;
import window.menu.AbstractMenu;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class UpStatsWindow extends AbstractMenu implements Serializable, PlayerPanel {

    private final Player player;
    @Serial
    private static final long serialVersionUID = -6544494898042825420L;

    public UpStatsWindow(Player player){
        this.player = player;
        setLayout(new GridBagLayout());

        drawWindow();
    }

    public JPanel drawWindow(){
        removeAll();
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 0, 10, 0);

        constraints.gridx = 0;
        constraints.gridy = 0;

        JLabel upPointsLeftLabel = new JLabel("Очков осталось: ");
        JLabel strengthLabel = new JLabel("Сила");
        JLabel speedLabel = new JLabel("Скорость");
        JLabel agilityLabel = new JLabel("Ловкость");
        JLabel intelligenceLabel = new JLabel("Разум");
        JLabel luckLabel = new JLabel("Удача");
        JLabel eloquenceLabel = new JLabel("Красноречие");
        JLabel blacksmithLabel = new JLabel("Кузнечное дело");
        JLabel theftLabel = new JLabel("Воровство");
        JLabel alchemyLabel = new JLabel("Алхимия");
        JLabel oneHandedWeaponLabel = new JLabel("Одноручное оружие");
        JLabel twoHandedWeaponLabel = new JLabel("Двуручное оружие");
        JLabel poleWeaponLabel = new JLabel("Древковое оружие");
        JLabel choppingWeaponLabel = new JLabel("Рубящее оружие");
        JLabel longRangeWeaponLabel = new JLabel("Дальнобойное оружие");
        JLabel upPointsLeft = new JLabel(Integer.toString(player.getUpPointCount()));

        JLabel strengthLabelCount = new JLabel("   " + player.getStats().getStrength() + "   ");
        JLabel speedLabelCount = new JLabel("   " + player.getStats().getSpeed() + "   ");
        JLabel agilityLabelCount = new JLabel("   " + player.getStats().getAgility() + "   ");
        JLabel intelligenceLabelCount = new JLabel("   " + player.getStats().getIntelligence() + "   ");
        JLabel luckLabelCount = new JLabel("   " + player.getStats().getLuck() + "   ");
        JLabel eloquenceLabelCount = new JLabel("   " + player.getStats().getEloquence() + "   ");
        JLabel blacksmithLabelCount = new JLabel("   " + player.getStats().getBlacksmith() + "   ");
        JLabel theftLabelCount = new JLabel("   " + player.getStats().getTheft() + "   ");
        JLabel alchemyLabelCount = new JLabel("   " + player.getStats().getAlchemy() + "   ");
        JLabel oneHandedWeaponLabelCount = new JLabel("   " + player.getStats().getOneHandedWeapon() + "   ");
        JLabel twoHandedWeaponLabelCount = new JLabel("   " + player.getStats().getTwoHandedWeapon() + "   ");
        JLabel poleWeaponLabelCount = new JLabel("   " + player.getStats().getPoleWeapon() + "   ");
        JLabel choppingWeaponLabelCount = new JLabel("   " + player.getStats().getChoppingWeapon() + "   ");
        JLabel longRangeWeaponLabelCount = new JLabel("   " + player.getStats().getLongRangeWeapon() + "   ");

        add(upPointsLeftLabel, constraints);
        constraints.gridy ++;
        add(strengthLabel, constraints);
        constraints.gridy ++;
        add(speedLabel, constraints);
        constraints.gridy ++;
        add(agilityLabel, constraints);
        constraints.gridy ++;
        add(intelligenceLabel, constraints);
        constraints.gridy ++;
        add(luckLabel, constraints);
        constraints.gridy ++;
        add(eloquenceLabel, constraints);
        constraints.gridy ++;
        add(blacksmithLabel, constraints);
        constraints.gridy ++;
        add(theftLabel, constraints);
        constraints.gridy ++;
        add(alchemyLabel, constraints);
        constraints.gridy ++;
        add(oneHandedWeaponLabel, constraints);
        constraints.gridy ++;
        add(twoHandedWeaponLabel, constraints);
        constraints.gridy ++;
        add(poleWeaponLabel, constraints);
        constraints.gridy ++;
        add(choppingWeaponLabel, constraints);
        constraints.gridy ++;
        add(longRangeWeaponLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(strengthLabelCount, constraints);
        constraints.gridy ++;
        add(speedLabelCount, constraints);
        constraints.gridy ++;
        add(agilityLabelCount, constraints);
        constraints.gridy ++;
        add(intelligenceLabelCount, constraints);
        constraints.gridy ++;
        add(luckLabelCount, constraints);
        constraints.gridy ++;
        add(eloquenceLabelCount, constraints);
        constraints.gridy ++;
        add(blacksmithLabelCount, constraints);
        constraints.gridy ++;
        add(theftLabelCount, constraints);
        constraints.gridy ++;
        add(alchemyLabelCount, constraints);
        constraints.gridy ++;
        add(oneHandedWeaponLabelCount, constraints);
        constraints.gridy ++;
        add(twoHandedWeaponLabelCount, constraints);
        constraints.gridy ++;
        add(poleWeaponLabelCount, constraints);
        constraints.gridy ++;
        add(choppingWeaponLabelCount, constraints);
        constraints.gridy ++;
        add(longRangeWeaponLabelCount, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        add(upPointsLeft, constraints);
        if (player.getUpPointCount() > 0){

            JButton strengthButton = new UnfocusedButton("+");
            JButton speedButton = new UnfocusedButton("+");
            JButton agilityButton = new UnfocusedButton("+");
            JButton intelligenceButton = new UnfocusedButton("+");
            JButton luckButton = new UnfocusedButton("+");
            JButton eloquenceButton = new UnfocusedButton("+");
            JButton blacksmithButton = new UnfocusedButton("+");
            JButton theftButton = new UnfocusedButton("+");
            JButton alchemyButton = new UnfocusedButton("+");
            JButton oneHandedWeaponButton = new UnfocusedButton("+");
            JButton twoHandedWeaponButton = new UnfocusedButton("+");
            JButton poleWeaponButton = new UnfocusedButton("+");
            JButton choppingWeaponButton = new UnfocusedButton("+");
            JButton longRangeWeaponButton = new UnfocusedButton("+");

            strengthButton.addActionListener(e -> {
                if (player.getUpPointCount() > 0) {
                    player.getStats().upStat(StatsEnum.STRENGTH);
                    player.setUpPointCount(player.getUpPointCount() - 1);
                    upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                    strengthLabelCount.setText("   " + player.getStats().getStrength() + "   ");
                }
            });
            speedButton.addActionListener(e -> {
                if (player.getUpPointCount() > 0){
                    player.getStats().upStat(StatsEnum.SPEED);
                    player.setUpPointCount(player.getUpPointCount()-1);
                    upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                    speedLabelCount.setText("   " + player.getStats().getSpeed() + "   ");
                }
            });
            agilityButton.addActionListener(e -> {
                if (player.getUpPointCount() > 0){
                    player.getStats().upStat(StatsEnum.AGILITY);
                    player.setUpPointCount(player.getUpPointCount()-1);
                    upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                    agilityLabelCount.setText("   " + player.getStats().getAgility() + "   ");
                }
            });
            intelligenceButton.addActionListener(e -> {
                if (player.getUpPointCount() > 0){
                    player.getStats().upStat(StatsEnum.INTELLIGENCE);
                    player.setUpPointCount(player.getUpPointCount()-1);
                    upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                    intelligenceLabelCount.setText("   " + player.getStats().getIntelligence() + "   ");
                }
            });
            luckButton.addActionListener(e -> {
                if (player.getUpPointCount() > 0){
                    player.getStats().upStat(StatsEnum.LUCK);
                    player.setUpPointCount(player.getUpPointCount()-1);
                    upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                    luckLabelCount.setText("   " + player.getStats().getLuck() + "   ");
                }
            });
            eloquenceButton.addActionListener(e -> {
                if (player.getUpPointCount() > 0){
                    player.getStats().upStat(StatsEnum.ELOQUENCE);
                    player.setUpPointCount(player.getUpPointCount()-1);
                    upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                    eloquenceLabelCount.setText("   " + player.getStats().getEloquence() + "   ");
                }
            });
            blacksmithButton.addActionListener(e -> {
                if (player.getUpPointCount() > 0){
                    player.getStats().upStat(StatsEnum.BLACKSMITH);
                    player.setUpPointCount(player.getUpPointCount()-1);
                    upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                    blacksmithLabelCount.setText("   " + player.getStats().getBlacksmith() + "   ");
                }
            });
            theftButton.addActionListener(e -> {
                if (player.getUpPointCount() > 0){
                    player.getStats().upStat(StatsEnum.THEFT);
                    player.setUpPointCount(player.getUpPointCount()-1);
                    upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                    blacksmithLabelCount.setText("   " + player.getStats().getTheft() + "   ");
                }
            });
            alchemyButton.addActionListener(e -> {
                if (player.getUpPointCount() > 0){
                    player.getStats().upStat(StatsEnum.ALCHEMY);
                    player.setUpPointCount(player.getUpPointCount()-1);
                    upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                    alchemyLabelCount.setText("   " + player.getStats().getAlchemy() + "   ");
                }
            });
            oneHandedWeaponButton.addActionListener(e -> {
                if (player.getUpPointCount() > 0){
                    player.getStats().upStat(StatsEnum.ONE_HANDED_WEAPON);
                    player.setUpPointCount(player.getUpPointCount()-1);
                    upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                    oneHandedWeaponLabelCount.setText("   " + player.getStats().getOneHandedWeapon() + "   ");
                }
            });
            twoHandedWeaponButton.addActionListener(e -> {
                if (player.getUpPointCount() > 0){
                    player.getStats().upStat(StatsEnum.TWO_HANDED_WEAPON);
                    player.setUpPointCount(player.getUpPointCount()-1);
                    upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                    twoHandedWeaponLabelCount.setText("   " + player.getStats().getTwoHandedWeapon() + "   ");
                }
            });
            poleWeaponButton.addActionListener(e -> {
                if (player.getUpPointCount() > 0){
                    player.getStats().upStat(StatsEnum.POLE_WEAPON);
                    player.setUpPointCount(player.getUpPointCount()-1);
                    upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                    poleWeaponLabelCount.setText("   " + player.getStats().getPoleWeapon() + "   ");
                }
            });
            choppingWeaponButton.addActionListener(e -> {
                if (player.getUpPointCount() > 0){
                    player.getStats().upStat(StatsEnum.CHOPPING_WEAPON);
                    player.setUpPointCount(player.getUpPointCount()-1);
                    upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                    choppingWeaponLabelCount.setText("   " + player.getStats().getChoppingWeapon() + "   ");
                }
            });
            longRangeWeaponButton.addActionListener(e -> {
                if (player.getUpPointCount() > 0){
                    player.getStats().upStat(StatsEnum.LONG_RANGE_WEAPON);
                    player.setUpPointCount(player.getUpPointCount()-1);
                    upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                    longRangeWeaponLabelCount.setText("   " + player.getStats().getLongRangeWeapon() + "   ");
                }
            });

            constraints.gridy = 1;
            add(strengthButton, constraints);
            constraints.gridy ++;
            add(speedButton, constraints);
            constraints.gridy ++;
            add(agilityButton, constraints);
            constraints.gridy ++;
            add(intelligenceButton, constraints);
            constraints.gridy ++;
            add(luckButton, constraints);
            constraints.gridy ++;
            add(eloquenceButton, constraints);
            constraints.gridy ++;
            add(blacksmithButton, constraints);
            constraints.gridy ++;
            add(theftButton, constraints);
            constraints.gridy ++;
            add(alchemyButton, constraints);
            constraints.gridy ++;
            add(oneHandedWeaponButton, constraints);
            constraints.gridy ++;
            add(twoHandedWeaponButton, constraints);
            constraints.gridy ++;
            add(poleWeaponButton, constraints);
            constraints.gridy ++;
            add(choppingWeaponButton, constraints);
            constraints.gridy ++;
            add(longRangeWeaponButton, constraints);
        }
        return this;
    }
}
