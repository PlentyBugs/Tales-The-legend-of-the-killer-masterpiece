package Windows.PlayerWindows;

import Items.Armors.Armor;
import Items.Item;
import Items.Weapons.Weapon;
import Creatures.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;

public class EquipmentWindow extends JFrame implements Serializable {

    private Player player;
    private JPanel panel = new JPanel();
    private GridBagConstraints constraints;
    private int width = 480;
    private int height = 240;
    private static final long serialVersionUID = 3557302482173437655L;

    public EquipmentWindow(Player player){
        super("Экипировка");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {}

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {
                player.setEquipmentOpen(true);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                player.setEquipmentOpen(false);
            }
        });
        setMinimumSize(new Dimension(width, height));

        this.player = player;
        drawEquipment();
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        if(b){
            drawEquipment();
        }
        setVisible(b);
    }

    public void drawEquipment(){

        getContentPane().remove(panel);

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for(Item item : player.getEquipment().getListOfEquipment()){

            if (item == null){
                continue;
            }

            JPanel itemPanel = new JPanel();
            itemPanel.setPreferredSize(new Dimension(width, 40));
            GridBagConstraints itemConstraints = new GridBagConstraints();
            itemConstraints.anchor = GridBagConstraints.WEST;
            itemConstraints.insets = new Insets(20, 10, 20, 10);
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 0;

            Color colorBackground = new Color(255,255,255,255);
            Color colorForeground = new Color(0,0,0);

            switch(item.getGrade()){
                case COMMON: colorForeground = new Color(0,0,0); break;
                case MAGIC: colorForeground = new Color(67, 162,255); break;
                case CURSE: colorForeground = new Color(1,155, 24); break;
                case ARTIFACT: colorForeground = new Color(255, 0, 18); break;
                case HEROIC: colorForeground = new Color(255, 96, 0); break;
                case ABOVETHEGODS: colorForeground = new Color(255, 0, 197); break;
            }

            switch(item.getRarity()){
                case COMMON: colorBackground = new Color(255,255,255,100); break;
                case UNCOMMON: colorBackground = new Color(0, 115,255,100); break;
                case RARE: colorBackground = new Color(12, 0,255,100); break;
                case MYSTICAL: colorBackground = new Color(255, 0, 119,100); break;
                case LEGENDARY: colorBackground = new Color(255, 232, 0,100); break;
                case DRAGON: colorBackground = new Color(255, 9, 0,100); break;
                case DIVINE: colorBackground = new Color(255, 169, 0,100); break;
            }
            JLabel equipmentItem = new JLabel(item.getClass().toString().split("\\.")[item.getClass().toString().split("\\.").length-1] + ": ");

            itemConstraints.gridx = 1;
            JLabel itemName = new JLabel(item.getName());
            itemConstraints.gridx = 2;
            JLabel itemQuality = new JLabel(Double.toString(item.getQuality()));

            itemConstraints.gridx = 2;
            JLabel property = new JLabel();
            itemConstraints.gridx = 3;
            JLabel propertyCount = new JLabel();

            if (item.getClass().toString().contains("Weapon")){
                property.setText("Урон: ");
                propertyCount.setText(Integer.toString(((Weapon)item).getDamage()));
            } else if (item.getClass().toString().contains("Torso") || item.getClass().toString().contains("Helmet") || item.getClass().toString().contains("Ring")){
                property.setText("Защита: ");
                propertyCount.setText(Integer.toString(((Armor)item).getProtection()));
            }

            itemName.setFont(new Font("Serif", Font.PLAIN, 16));
            itemQuality.setFont(new Font("Serif", Font.PLAIN, 16));
            property.setFont(new Font("Serif", Font.PLAIN, 16));
            propertyCount.setFont(new Font("Serif", Font.PLAIN, 16));

            itemName.setForeground(colorForeground);
            itemQuality.setForeground(colorForeground);
            property.setForeground(colorForeground);
            propertyCount.setForeground(colorForeground);

            itemPanel.add(equipmentItem, itemConstraints);
            itemPanel.add(itemName, itemConstraints);
            itemPanel.add(itemQuality, itemConstraints);
            itemPanel.add(property, itemConstraints);
            itemPanel.add(propertyCount, itemConstraints);

            itemPanel.setBackground(colorBackground);

            panel.add(itemPanel, constraints);

            constraints.gridy ++;
        }
        getContentPane().add(panel);
        pack();
        setVisible(true);
    }
}
