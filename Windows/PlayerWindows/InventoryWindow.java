package Windows.PlayerWindows;

import Items.Armors.Armor;
import Items.Item;
import Items.Potions.Potion;
import Items.Weapons.Weapon;
import Creatures.LiveCreature;
import Creatures.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

public class InventoryWindow extends JFrame implements Serializable {

    private LiveCreature player;
    private JPanel panel = new JPanel(new GridBagLayout());
    private JScrollPane scroll = new JScrollPane(panel);
    private GridBagConstraints constraints;
    private JPanel menuPanel = new JPanel();
    private int width = 720;
    private int height = 720;
    private JPanel panelz = new JPanel();;
    private static final long serialVersionUID = -559721917387219997L;
    private String currentInventory = "All";

    public InventoryWindow(LiveCreature player){
        super("Инвентарь");

        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));

        this.player = player;
        drawInventory();
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        drawInventory();
    }

    public void drawInventory(){
        menuPanel = new JPanel();

        JButton allInventory = new JButton("Всё");
        JButton weaponInventory = new JButton("Оружие");
        JButton armorInventory = new JButton("Броня");
        JButton potionInventory = new JButton("Зелья");

        menuPanel.add(allInventory);
        menuPanel.add(weaponInventory);
        menuPanel.add(armorInventory);
        menuPanel.add(potionInventory);

        allInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentInventory = "All";
                drawInventory();
                if(player.getClass().toString().contains("Player")){
                    ((Player)player).getFieldWindow().drawMap();
                }
            }
        });

        weaponInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentInventory = "Weapon";
                drawInventory();
                if(player.getClass().toString().contains("Player")){
                    ((Player)player).getFieldWindow().drawMap();
                }
            }
        });

        armorInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentInventory = "Armor";
                drawInventory();
                if(player.getClass().toString().contains("Player")){
                    ((Player)player).getFieldWindow().drawMap();
                }
            }
        });

        potionInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentInventory = "Potion";
                drawInventory();
                if(player.getClass().toString().contains("Player")){
                    ((Player)player).getFieldWindow().drawMap();
                }
            }
        });

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        for (Item item : player.getInventory()){

            if(!currentInventory.equals("All") && !item.getClass().toString().contains(currentInventory)){
                continue;
            }

            JPanel itemPanel = new JPanel();
            itemPanel.setPreferredSize(new Dimension(width, 40));
            GridBagConstraints itemConstraints = new GridBagConstraints();
            itemConstraints.anchor = GridBagConstraints.WEST;
            itemConstraints.insets = new Insets(20, 10, 20, 10);
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 0;
            JLabel propertyCount = new JLabel();

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

            if (item.getClass().toString().contains("Weapons")){
                property.setText("Урон: ");
                propertyCount.setText(Integer.toString(((Weapon)item).getDamage()));
            } else if (item.getClass().toString().contains("Torso") || item.getClass().toString().contains("Helmet") || item.getClass().toString().contains("Ring")){
                property.setText("Защита: ");
                propertyCount.setText(Integer.toString(((Armor)item).getProtection()));
            }

            itemName.setFont(new Font("Serif", Font.PLAIN, 12));
            itemQuality.setFont(new Font("Serif", Font.PLAIN, 12));
            property.setFont(new Font("Serif", Font.PLAIN, 12));
            propertyCount.setFont(new Font("Serif", Font.PLAIN, 12));

            itemName.setForeground(colorForeground);
            itemQuality.setForeground(colorForeground);
            property.setForeground(colorForeground);
            propertyCount.setForeground(colorForeground);

            itemConstraints.gridx = 4;
            JButton useButton = new JButton("Экипировать");

            if(item.getClass().toString().contains("Potions")){
                itemQuality.setText("");
                useButton.setText("Использовать");
            }
            useButton.setSize(100,40);

            if(!item.getClass().toString().contains("Potions")){
                useButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        player.equip(item);
                        if(player.getClass().toString().contains("Player") && ((Player)player).getEquipmentWindow() != null){
                            ((Player)player).getEquipmentWindow().drawEquipment();
                            ((Player)player).getFieldWindow().drawMap();
                        }
                    }
                });
            } else {
                useButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ((Potion)item).use(player);
                        player.removeItem(item);
                        drawInventory();
                        ((Player)player).getFieldWindow().drawMap();
                    }
                });
            }
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
        scroll.setPreferredSize(new Dimension(width,height-80));
    }

    public JPanel getPanel() {
        drawInventory();
        panelz.removeAll();
        panelz = new JPanel();
        panelz.add(menuPanel);
        panelz.add(this.panel);
        return panelz;
    }
}
