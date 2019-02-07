package Windows.SupportWindows;

import Items.Armors.Armor;
import Items.Item;
import Items.Weapons.Weapon;
import LiveCreatures.Player;
import Things.Chest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class InventoryWindowChest extends JFrame {

    private Chest chest;
    private Player player;
    private JPanel panel = new JPanel(new GridBagLayout());
    private JScrollPane scroll = new JScrollPane(panel);
    private GridBagConstraints constraints;
    private int width = 600;
    private int height = 480;
    private static final long serialVersionUID = -3364742123084557236L;

    public InventoryWindowChest(Chest chest){
        super("Сундук");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.chest = chest;
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

        getContentPane().remove(scroll);

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        for (Item item : chest.getInventory()){

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
                default:  colorForeground = new Color(0,0,0); break;
            }

            switch(item.getRarity()){
                case COMMON: colorBackground = new Color(255,255,255,100); break;
                case UNCOMMON: colorBackground = new Color(0, 115,255,100); break;
                case RARE: colorBackground = new Color(12, 0,255,100); break;
                case MYSTICAL: colorBackground = new Color(255, 0, 119,100); break;
                case LEGENDARY: colorBackground = new Color(255, 232, 0,100); break;
                case DRAGON: colorBackground = new Color(255, 9, 0,100); break;
                case DIVINE: colorBackground = new Color(255, 169, 0,100); break;
                default:  colorBackground = new Color(255,255,255,100); break;
            }

            JLabel itemName = new JLabel(item.getName());
            itemConstraints.gridx = 1;
            JLabel itemQuality = new JLabel("Прочность: " + Double.toString(item.getQuality()));

            itemConstraints.gridx = 2;
            JLabel property = new JLabel();
            itemConstraints.gridx = 3;
            JLabel propertyCount = new JLabel();

            if (item.getClass().toString().split("\\.")[item.getClass().toString().split("\\.").length-1].equals("Sword")){
                property.setText("Урон: ");
                propertyCount.setText(Integer.toString(((Weapon)item).getDamage()));
            } else if (item.getClass().toString().split("\\.")[item.getClass().toString().split("\\.").length-1].equals("Torso") || item.getClass().toString().split("\\.")[item.getClass().toString().split("\\.").length-1].equals("Helmet")){
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

            itemConstraints.gridx = 4;
            JButton useButton = new JButton("Взять");

            useButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    player.addItemToInventory(item);
                    chest.removeItemFromInventory(item);
                    drawInventory();
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
        scroll.setPreferredSize(new Dimension(width,height));
        getContentPane().add(scroll);
        pack();
        setVisible(true);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
