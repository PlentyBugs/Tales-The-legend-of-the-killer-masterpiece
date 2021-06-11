package Windows.ConversationWindows;

import Items.Armors.Armor;
import Items.Item;
import Items.Weapons.Weapon;
import Creatures.LiveCreature;
import Creatures.Player;
import Windows.BattleWindows.FightWindow;
import Windows.PlayerWindows.UnfocusedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;

public class ThiefWindow extends JFrame implements Serializable {

    private final LiveCreature player;
    private final Player thief;
    private JPanel panel = new JPanel(new GridBagLayout());
    JScrollPane scroll = new JScrollPane(panel);

    public ThiefWindow(LiveCreature player, Player thief) {
        super("Инвентарь " + player.getName());
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.player = player;
        this.thief = thief;
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        if (b) drawInventory();
        setVisible(b);
    }

    public void drawInventory(){

        getContentPane().remove(scroll);

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        int width = 720;
        for (Item item : player.getInventory()){

            JPanel itemPanel = new JPanel();
            itemPanel.setPreferredSize(new Dimension(width, 40));
            GridBagConstraints itemConstraints = new GridBagConstraints();
            itemConstraints.anchor = GridBagConstraints.WEST;
            itemConstraints.insets = new Insets(20, 10, 20, 10);
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 0;

            Color colorBackground = new Color(255,255,255,255);
            new Color(0, 0, 0);
            Color colorForeground = switch (item.getGrade()) {
                case COMMON -> new Color(0, 0, 0);
                case MAGIC -> new Color(67, 162, 255);
                case CURSE -> new Color(1, 155, 24);
                case ARTIFACT -> new Color(255, 0, 18);
                case HEROIC -> new Color(255, 96, 0);
                case ABOVETHEGODS -> new Color(255, 0, 197);
            };

            colorBackground = switch (item.getRarity()) {
                case COMMON -> new Color(255, 255, 255, 100);
                case UNCOMMON -> new Color(0, 115, 255, 100);
                case RARE -> new Color(12, 0, 255, 100);
                case MYSTICAL -> new Color(255, 0, 119, 100);
                case LEGENDARY -> new Color(255, 232, 0, 100);
                case DRAGON -> new Color(255, 9, 0, 100);
                case DIVINE -> new Color(255, 169, 0, 100);
            };

            JLabel itemName = new JLabel(item.getName());
            itemConstraints.gridx = 1;
            JLabel itemQuality = new JLabel("Прочность: " + Double.toString(item.getQuality()));

            itemConstraints.gridx = 2;
            JLabel property = new JLabel();
            itemConstraints.gridx = 3;
            JLabel propertyCount = new JLabel();

            double cost = 1;

            if (item.getClass().toString().contains("Weapons")){
                property.setText("Урон: ");
                propertyCount.setText(Double.toString(((Weapon)item).getDamage()));
                cost = ((Weapon)item).getDamage();
            } else if (item.getClass().toString().contains("Torso") || item.getClass().toString().contains("Helmet") || item.getClass().toString().contains("Ring")){
                property.setText("Защита: ");
                propertyCount.setText(Integer.toString(((Armor)item).getProtection()));
                cost = ((Armor)item).getProtection();
            }

            itemName.setFont(new Font("Serif", Font.PLAIN, 16));
            itemQuality.setFont(new Font("Serif", Font.PLAIN, 16));
            property.setFont(new Font("Serif", Font.PLAIN, 16));
            propertyCount.setFont(new Font("Serif", Font.PLAIN, 16));

            itemName.setForeground(colorForeground);
            itemQuality.setForeground(colorForeground);
            property.setForeground(colorForeground);
            propertyCount.setForeground(colorForeground);

            double chance = thief.getStats().getTheft()*47.87/(cost);

            if(chance > 100.0){
                chance = 100.0;
            }

            itemConstraints.gridx = 4;
            JButton useButton = new UnfocusedButton("Украсть(Шанс " + Math.round(chance*100.0)/100.0 + "%)");
            useButton.setSize(100,40);

            double finalChance = chance;
            useButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    double randomChance = Math.ceil(Math.random()*10000.0)/100.0;
                    if(randomChance <= finalChance){
                        thief.addItemToInventory(item);
                        player.removeItem(item);
                        drawInventory();
                        thief.getInventoryWindow().drawInventory();
                    } else {
                        FightWindow fightWindow = new FightWindow(thief, player, thief.getWindowInterface());
                        close();
                    }
                }
            });
            itemPanel.add(itemName, itemConstraints);
            itemPanel.add(itemQuality, itemConstraints);
            itemPanel.add(property, itemConstraints);
            itemPanel.add(propertyCount, itemConstraints);
            itemPanel.add(useButton, itemConstraints);

            itemPanel.setBackground(colorBackground);

            panel.add(itemPanel, constraints);

            constraints.gridy ++;
        }

        scroll = new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        int height = 720;
        scroll.setPreferredSize(new Dimension(width, height));
        getContentPane().add(scroll);
        pack();
//        setVisible(true);
    }
}
