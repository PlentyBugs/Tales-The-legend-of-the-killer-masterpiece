package Windows.PlayerWindows;

import LiveCreatures.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Date;

public class PlayerWindowManager extends JFrame implements Serializable {

    private Player player;
    private static final long serialVersionUID = 3259430673044045936L;

    public PlayerWindowManager(Player player){
        super("Менеджер окон игрока");setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {}

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {
                player.setManagerOpen(true);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                player.setManagerOpen(false);
            }
        });
        setPreferredSize(new Dimension(660,60));

        this.player = player;

        JPanel panel = new JPanel(new GridBagLayout());
        JButton inventory = new JButton("Инвентарь");
        JButton uoStats = new JButton("Прокачка");
        JButton equipment = new JButton("Экипировка");
        JButton info = new JButton("Информация");
        JButton abilities = new JButton("Умения");
        JButton quests = new JButton("Квесты");
        JButton save = new JButton("Сохранить");

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

        abilities.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!player.getIsAbilityWindowOpen()){
                    player.setAbilityWindowIsVisible(true);
                    player.setAbilityWindowOpen(true);
                } else {
                    player.setAbilityWindowIsVisible(false);
                    player.setAbilityWindowOpen(false);
                }
            }
        });

        quests.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!player.getIsQuestWindowOpen()){
                    player.setQuestWindowIsVisible(true);
                    player.setQuestWindowOpen(true);
                } else {
                    player.setQuestWindowIsVisible(false);
                    player.setQuestWindowOpen(false);
                }
            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    Date date = new Date();
                    try{
                        FileOutputStream fos = new FileOutputStream("./Saves/save" + date.getTime() + ".txt");
                        ObjectOutputStream outStream = new ObjectOutputStream(fos);
                        outStream.writeObject(player.getFieldWindow());
                        outStream.flush();
                        outStream.close();
                    } catch (FileNotFoundException ex){
                        File dir = new File("./Saves");
                        dir.mkdir();
                        FileOutputStream fos = new FileOutputStream("./Saves/save" + date.getTime() + ".txt");
                        ObjectOutputStream outStream = new ObjectOutputStream(fos);
                        outStream.writeObject(player.getFieldWindow());
                        outStream.flush();
                        outStream.close();
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        panel.add(inventory);
        panel.add(uoStats);
        panel.add(equipment);
        panel.add(info);
        panel.add(abilities);
        panel.add(quests);
        panel.add(save);

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
