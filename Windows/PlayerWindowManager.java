package Windows;

import LiveCreatures.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class PlayerWindowManager extends JFrame {

    private Player player;

    public PlayerWindowManager(Player player){
        super("Менеджер окон игрока");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(440,60));

        this.player = player;

        JPanel panel = new JPanel(new GridBagLayout());
        JButton inventory = new JButton("Инвентарь");
        JButton uoStats = new JButton("Прокачка");
        JButton equipment = new JButton("Экипировка");
        JButton info = new JButton("Информация");

        inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!player.getIsInventoryOpen()){
                    player.setInventoryWindowIsVisible(true);
                    player.setInventoryOpen(true);
                } else {
                    player.setInventoryWindowIsVisible(false);
                    player.setInventoryOpen(false);
                }
            }
        });

        uoStats.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!player.getIsUpStatsOpen()){
                    player.setUpStatsWindowIsVisible(true);
                    player.setUpStatsOpen(true);
                } else {
                    player.setUpStatsWindowIsVisible(false);
                    player.setUpStatsOpen(false);
                }
            }
        });

        equipment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!player.getIsEquipmentOpen()){
                    player.setEquipmentIsVisible(true);
                    player.setEquipmentOpen(true);
                } else {
                    player.setEquipmentIsVisible(false);
                    player.setEquipmentOpen(false);
                }
            }
        });

        info.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!player.getIsInfoWindowOpen()){
                    player.setInfoWindowIsVisible(true);
                    player.setInfoWindowOpen(true);
                } else {
                    player.setInfoWindowIsVisible(false);
                    player.setInfoWindowOpen(false);
                }
            }
        });

        panel.add(inventory);
        panel.add(uoStats);
        panel.add(equipment);
        panel.add(info);

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        setVisible(b);
    }
}
