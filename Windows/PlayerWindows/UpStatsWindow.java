package Windows.PlayerWindows;

import LiveCreatures.Player;
import LiveCreatures.StatsEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

public class UpStatsWindow extends JFrame implements Serializable {

    private Player player;

    private JPanel panel = new JPanel(new GridBagLayout());
    private static final long serialVersionUID = -6544494898042825420L;

    public UpStatsWindow(Player player){
        super("Прокачка");
        this.player = player;setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {}

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {
                player.setUpStatsOpen(true);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                player.setUpStatsOpen(false);
            }
        });
        setPreferredSize(new Dimension(220,680));
        getContentPane().add(panel);

        setLayout(new FlowLayout());

        drawInventory();
    }
    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        drawInventory();
        setVisible(b);
    }

    private void drawInventory(){

        getContentPane().remove(panel);

        panel = new JPanel(new GridBagLayout());
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

        JLabel strengthLabelCount = new JLabel("   " + Integer.toString(player.getStats().strength) + "   ");
        JLabel speedLabelCount = new JLabel("   " + Integer.toString(player.getStats().speed) + "   ");
        JLabel agilityLabelCount = new JLabel("   " + Integer.toString(player.getStats().agility) + "   ");
        JLabel intelligenceLabelCount = new JLabel("   " + Integer.toString(player.getStats().intelligence) + "   ");
        JLabel luckLabelCount = new JLabel("   " + Integer.toString(player.getStats().luck) + "   ");
        JLabel eloquenceLabelCount = new JLabel("   " + Integer.toString(player.getStats().eloquence) + "   ");
        JLabel blacksmithLabelCount = new JLabel("   " + Integer.toString(player.getStats().blacksmith) + "   ");
        JLabel theftLabelCount = new JLabel("   " + Integer.toString(player.getStats().theft) + "   ");
        JLabel alchemyLabelCount = new JLabel("   " + Integer.toString(player.getStats().alchemy) + "   ");
        JLabel oneHandedWeaponLabelCount = new JLabel("   " + Integer.toString(player.getStats().one_handed_weapon) + "   ");
        JLabel twoHandedWeaponLabelCount = new JLabel("   " + Integer.toString(player.getStats().two_handed_weapon) + "   ");
        JLabel poleWeaponLabelCount = new JLabel("   " + Integer.toString(player.getStats().pole_weapon) + "   ");
        JLabel choppingWeaponLabelCount = new JLabel("   " + Integer.toString(player.getStats().chopping_weapon) + "   ");
        JLabel longRangeWeaponLabelCount = new JLabel("   " + Integer.toString(player.getStats().long_range_weapon) + "   ");

        panel.add(upPointsLeftLabel, constraints);
        constraints.gridy ++;
        panel.add(strengthLabel, constraints);
        constraints.gridy ++;
        panel.add(speedLabel, constraints);
        constraints.gridy ++;
        panel.add(agilityLabel, constraints);
        constraints.gridy ++;
        panel.add(intelligenceLabel, constraints);
        constraints.gridy ++;
        panel.add(luckLabel, constraints);
        constraints.gridy ++;
        panel.add(eloquenceLabel, constraints);
        constraints.gridy ++;
        panel.add(blacksmithLabel, constraints);
        constraints.gridy ++;
        panel.add(theftLabel, constraints);
        constraints.gridy ++;
        panel.add(alchemyLabel, constraints);
        constraints.gridy ++;
        panel.add(oneHandedWeaponLabel, constraints);
        constraints.gridy ++;
        panel.add(twoHandedWeaponLabel, constraints);
        constraints.gridy ++;
        panel.add(poleWeaponLabel, constraints);
        constraints.gridy ++;
        panel.add(choppingWeaponLabel, constraints);
        constraints.gridy ++;
        panel.add(longRangeWeaponLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(strengthLabelCount, constraints);
        constraints.gridy ++;
        panel.add(speedLabelCount, constraints);
        constraints.gridy ++;
        panel.add(agilityLabelCount, constraints);
        constraints.gridy ++;
        panel.add(intelligenceLabelCount, constraints);
        constraints.gridy ++;
        panel.add(luckLabelCount, constraints);
        constraints.gridy ++;
        panel.add(eloquenceLabelCount, constraints);
        constraints.gridy ++;
        panel.add(blacksmithLabelCount, constraints);
        constraints.gridy ++;
        panel.add(theftLabelCount, constraints);
        constraints.gridy ++;
        panel.add(alchemyLabelCount, constraints);
        constraints.gridy ++;
        panel.add(oneHandedWeaponLabelCount, constraints);
        constraints.gridy ++;
        panel.add(twoHandedWeaponLabelCount, constraints);
        constraints.gridy ++;
        panel.add(poleWeaponLabelCount, constraints);
        constraints.gridy ++;
        panel.add(choppingWeaponLabelCount, constraints);
        constraints.gridy ++;
        panel.add(longRangeWeaponLabelCount, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        panel.add(upPointsLeft, constraints);
        if (player.getUpPointCount() > 0){

            JButton strengthButton = new JButton("+");
            JButton speedButton = new JButton("+");
            JButton agilityButton = new JButton("+");
            JButton intelligenceButton = new JButton("+");
            JButton luckButton = new JButton("+");
            JButton eloquenceButton = new JButton("+");
            JButton blacksmithButton = new JButton("+");
            JButton theftButton = new JButton("+");
            JButton alchemyButton = new JButton("+");
            JButton oneHandedWeaponButton = new JButton("+");
            JButton twoHandedWeaponButton = new JButton("+");
            JButton poleWeaponButton = new JButton("+");
            JButton choppingWeaponButton = new JButton("+");
            JButton longRangeWeaponButton = new JButton("+");

            strengthButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player.getUpPointCount() > 0) {
                        player.getStats().upStat(StatsEnum.STRENGTH);
                        player.setUpPointCount(player.getUpPointCount() - 1);
                        upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                        strengthLabelCount.setText("   " + Integer.toString(player.getStats().strength) + "   ");
                    }
                }
            });
            speedButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player.getUpPointCount() > 0){
                        player.getStats().upStat(StatsEnum.SPEED);
                        player.setUpPointCount(player.getUpPointCount()-1);
                        upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                        speedLabelCount.setText("   " + Integer.toString(player.getStats().speed) + "   ");
                    }
                }
            });
            agilityButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player.getUpPointCount() > 0){
                        player.getStats().upStat(StatsEnum.AGILITY);
                        player.setUpPointCount(player.getUpPointCount()-1);
                        upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                        agilityLabelCount.setText("   " + Integer.toString(player.getStats().agility) + "   ");
                    }
                }
            });
            intelligenceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player.getUpPointCount() > 0){
                        player.getStats().upStat(StatsEnum.INTELLIGENCE);
                        player.setUpPointCount(player.getUpPointCount()-1);
                        upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                        intelligenceLabelCount.setText("   " + Integer.toString(player.getStats().intelligence) + "   ");
                    }
                }
            });
            luckButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player.getUpPointCount() > 0){
                        player.getStats().upStat(StatsEnum.LUCK);
                        player.setUpPointCount(player.getUpPointCount()-1);
                        upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                        luckLabelCount.setText("   " + Integer.toString(player.getStats().luck) + "   ");
                    }
                }
            });
            eloquenceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player.getUpPointCount() > 0){
                        player.getStats().upStat(StatsEnum.ELOQUENCE);
                        player.setUpPointCount(player.getUpPointCount()-1);
                        upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                        eloquenceLabelCount.setText("   " + Integer.toString(player.getStats().eloquence) + "   ");
                    }
                }
            });
            blacksmithButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player.getUpPointCount() > 0){
                        player.getStats().upStat(StatsEnum.BLACKSMITH);
                        player.setUpPointCount(player.getUpPointCount()-1);
                        upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                        blacksmithLabelCount.setText("   " + Integer.toString(player.getStats().blacksmith) + "   ");
                    }
                }
            });
            theftButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player.getUpPointCount() > 0){
                        player.getStats().upStat(StatsEnum.THEFT);
                        player.setUpPointCount(player.getUpPointCount()-1);
                        upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                        blacksmithLabelCount.setText("   " + Integer.toString(player.getStats().theft) + "   ");
                    }
                }
            });
            alchemyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player.getUpPointCount() > 0){
                        player.getStats().upStat(StatsEnum.ALCHEMY);
                        player.setUpPointCount(player.getUpPointCount()-1);
                        upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                        alchemyLabelCount.setText("   " + Integer.toString(player.getStats().alchemy) + "   ");
                    }
                }
            });
            oneHandedWeaponButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player.getUpPointCount() > 0){
                        player.getStats().upStat(StatsEnum.ONEHANDEDWEAPON);
                        player.setUpPointCount(player.getUpPointCount()-1);
                        upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                        oneHandedWeaponLabelCount.setText("   " + Integer.toString(player.getStats().one_handed_weapon) + "   ");
                    }
                }
            });
            twoHandedWeaponButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player.getUpPointCount() > 0){
                        player.getStats().upStat(StatsEnum.TWOHANDEDWEAPON);
                        player.setUpPointCount(player.getUpPointCount()-1);
                        upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                        twoHandedWeaponLabelCount.setText("   " + Integer.toString(player.getStats().two_handed_weapon) + "   ");
                    }
                }
            });
            poleWeaponButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player.getUpPointCount() > 0){
                        player.getStats().upStat(StatsEnum.POLEWEAPON);
                        player.setUpPointCount(player.getUpPointCount()-1);
                        upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                        poleWeaponLabelCount.setText("   " + Integer.toString(player.getStats().pole_weapon) + "   ");
                    }
                }
            });
            choppingWeaponButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player.getUpPointCount() > 0){
                        player.getStats().upStat(StatsEnum.CHOPPINGWEAPON);
                        player.setUpPointCount(player.getUpPointCount()-1);
                        upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                        choppingWeaponLabelCount.setText("   " + Integer.toString(player.getStats().chopping_weapon) + "   ");
                    }
                }
            });
            longRangeWeaponButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (player.getUpPointCount() > 0){
                        player.getStats().upStat(StatsEnum.LONGRANGEWEAPON);
                        player.setUpPointCount(player.getUpPointCount()-1);
                        upPointsLeft.setText(Integer.toString(player.getUpPointCount()));
                        longRangeWeaponLabelCount.setText("   " + Integer.toString(player.getStats().long_range_weapon) + "   ");
                    }
                }
            });

            constraints.gridy = 1;
            panel.add(strengthButton, constraints);
            constraints.gridy ++;
            panel.add(speedButton, constraints);
            constraints.gridy ++;
            panel.add(agilityButton, constraints);
            constraints.gridy ++;
            panel.add(intelligenceButton, constraints);
            constraints.gridy ++;
            panel.add(luckButton, constraints);
            constraints.gridy ++;
            panel.add(eloquenceButton, constraints);
            constraints.gridy ++;
            panel.add(blacksmithButton, constraints);
            constraints.gridy ++;
            panel.add(theftButton, constraints);
            constraints.gridy ++;
            panel.add(alchemyButton, constraints);
            constraints.gridy ++;
            panel.add(oneHandedWeaponButton, constraints);
            constraints.gridy ++;
            panel.add(twoHandedWeaponButton, constraints);
            constraints.gridy ++;
            panel.add(poleWeaponButton, constraints);
            constraints.gridy ++;
            panel.add(choppingWeaponButton, constraints);
            constraints.gridy ++;
            panel.add(longRangeWeaponButton, constraints);
        }
        getContentPane().add(panel);
        pack();
        setVisible(true);
    }
}
