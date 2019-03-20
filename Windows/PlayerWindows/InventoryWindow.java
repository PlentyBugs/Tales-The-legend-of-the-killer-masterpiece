package Windows.PlayerWindows;

import Creatures.LiveCreature;
import Creatures.Player;
import Items.Armors.Armor;
import Items.Armors.Ring;
import Items.Item;
import Items.Alchemy.Potions.Potion;
import Items.Weapons.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class InventoryWindow extends JFrame implements Serializable {

    private LiveCreature player;
    private JPanel panel = new JPanel(new GridBagLayout());
    private JScrollPane scroll = new JScrollPane(panel);
    private GridBagConstraints constraints;
    private JPanel menuPanel = new JPanel();
    private int width = 720;
    private int height = 720;
    private JPanel panelz = new JPanel();
    private static final long serialVersionUID = -559721917387219997L;
    private String currentInventory = "All";
    private ArrayList<Item> uniqueInventory = new ArrayList<>();

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
        drawInventory(true);
    }

    public void drawInventory(Boolean isDrawMap){
        menuPanel = new JPanel();

        JButton allInventory = new JButton("Всё");
        JButton weaponInventory = new JButton("Оружие");
        JButton armorInventory = new JButton("Броня");
        JButton potionInventory = new JButton("Зелья");
        JButton ingredientInventory = new JButton("Ингредиенты");

        menuPanel.add(allInventory);
        menuPanel.add(weaponInventory);
        menuPanel.add(armorInventory);
        menuPanel.add(potionInventory);
        menuPanel.add(ingredientInventory);

        allInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentInventory = "All";
                drawInventory();
                if(player.getClass().toString().contains("Player")){
                    if(isDrawMap)
                        ((Player)player).getFieldWindow().drawAllPlayerWindow();
                }
            }
        });

        weaponInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentInventory = "Weapon";
                drawInventory();
                if(player.getClass().toString().contains("Player")){
                    if(isDrawMap)
                        ((Player)player).getFieldWindow().drawAllPlayerWindow();
                }
            }
        });

        armorInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentInventory = "Armor";
                drawInventory();
                if(player.getClass().toString().contains("Player")){
                    if(isDrawMap)
                        ((Player)player).getFieldWindow().drawAllPlayerWindow();
                }
            }
        });

        potionInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentInventory = "Potion";
                drawInventory();
                if(player.getClass().toString().contains("Player")){
                    if(isDrawMap)
                        ((Player)player).getFieldWindow().drawAllPlayerWindow();
                }
            }
        });

        ingredientInventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentInventory = "Ingredient";
                drawInventory();
                if(player.getClass().toString().contains("Player")){
                    if(isDrawMap)
                        ((Player)player).getFieldWindow().drawAllPlayerWindow();
                }
            }
        });

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        uniqueInventory.clear();
        for (Item item : player.getInventory()){
            if(!uniqueInventoryContains(item)){
                uniqueInventory.add(item);
            } else if(player.countOfItemInInventory(item) > 1 && item.getStackable()){
                continue;
            }

            if(!currentInventory.equals("All") && !item.getClass().toString().contains(currentInventory)){
                continue;
            }

            JPanel itemPanel = new JPanel(new GridBagLayout());
            itemPanel.setToolTipText(item.getEnchantDescription());
            itemPanel.setPreferredSize(new Dimension(width, 40));
            GridBagConstraints itemConstraints = new GridBagConstraints();
            itemConstraints.anchor = GridBagConstraints.WEST;
            itemConstraints.insets = new Insets(0, 2, 0, 2);
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
            JLabel itemQuality = new JLabel("Прочность: " + Double.toString(item.getQuality()));
            JLabel property = new JLabel();

            if (item.getClass().toString().contains("Weapons")){
                property.setText("Урон: ");
                propertyCount.setText(Integer.toString(((Weapon)item).getClearDamage()));
            } else if (item.getClass().toString().contains("Torso") || item.getClass().toString().contains("Helmet")){
                property.setText("Защита: ");
                propertyCount.setText(Integer.toString(((Armor)item).getProtection()));
            } else if(item.getClass().toString().contains("Ring")){
                property.setText(((Ring)item).getStat() + ": ");
                propertyCount.setText(Integer.toString(((Ring)item).getStatPower()));
            } else if (item.getClass().toString().contains("Potions")){
                property.setText("Мощность: ");
                propertyCount.setText(Integer.toString(((Potion)item).getEffect().getPower()));
            }

            int count = player.countOfItemInInventory(item);
            JLabel ccunt = new JLabel();
            if(count > 1 && item.getStackable()){
                ccunt = new JLabel("Количество: " + count);
            }

            itemName.setFont(new Font("Serif", Font.PLAIN, 12));
            itemQuality.setFont(new Font("Serif", Font.PLAIN, 12));
            property.setFont(new Font("Serif", Font.PLAIN, 12));
            propertyCount.setFont(new Font("Serif", Font.PLAIN, 12));

            itemName.setForeground(colorForeground);
            itemQuality.setForeground(colorForeground);
            property.setForeground(colorForeground);
            propertyCount.setForeground(colorForeground);


            itemPanel.add(itemName, itemConstraints);
            itemConstraints.gridx ++;
            itemPanel.add(itemQuality, itemConstraints);
            itemConstraints.gridx ++;
            itemPanel.add(property, itemConstraints);
            itemConstraints.gridx ++;
            itemPanel.add(propertyCount, itemConstraints);
            itemConstraints.gridx ++;
            itemPanel.add(ccunt, itemConstraints);
            itemConstraints.gridx = 0;
            itemConstraints.gridy ++;

            JButton useButton = new JButton("Экипировать");

            if(item.getClass().toString().contains("Alchemy")){
                itemQuality.setText("");
                useButton.setText("Использовать");
            }
            useButton.setPreferredSize(new Dimension(width/4,20));
            useButton.setMaximumSize(new Dimension(width/4,20));
            useButton.setMinimumSize(new Dimension(width/4,20));

            if(!item.getClass().toString().contains("Alchemy")){
                useButton.addActionListener(e -> {
                    player.equip(item);
                    if(player.getClass().toString().contains("Player") && ((Player)player).getEquipmentWindow() != null){
                        ((Player)player).getEquipmentWindow().drawEquipment();
                        if(isDrawMap)
                            ((Player)player).getFieldWindow().drawAllPlayerWindow();
                    }
                });
            } else {
                useButton.addActionListener(e -> {
                    ((Potion)item).use(player);
                    player.removeItem(item);
                    drawInventory();
                    if(isDrawMap)
                        ((Player)player).getFieldWindow().drawAllPlayerWindow();
                });
            }

            JButton removeButton = new JButton("Выбросить");
            removeButton.addActionListener(e -> {
                player.removeItem(item);
                if(isDrawMap)
                    ((Player)player).getFieldWindow().drawAllPlayerWindow();
            });
            removeButton.setPreferredSize(new Dimension(width/4,20));
            removeButton.setMaximumSize(new Dimension(width/4,20));
            removeButton.setMinimumSize(new Dimension(width/4,20));
;
            itemPanel.add(useButton, itemConstraints);
            itemConstraints.gridx ++;
            itemPanel.add(removeButton, itemConstraints);

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
        panelz.add(scroll);
        return panelz;
    }

    private boolean uniqueInventoryContains(Item item){
        for(Item itm : uniqueInventory){
            if(itm.compareTo(item) == 0){
                return true;
            }
        }
        return false;
    }
}
