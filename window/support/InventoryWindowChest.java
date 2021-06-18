package window.support;

import creature.Player;
import item.armor.Armor;
import item.armor.Ring;
import item.Item;
import item.weapon.Weapon;
import thing.chest.Chest;
import window.player.UnfocusedButton;
import window.WindowInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class InventoryWindowChest extends JFrame {

    private final Chest chest;
    private Player player;
    private JPanel panel = new JPanel(new GridBagLayout());
    private JScrollPane scroll = new JScrollPane(panel);
    private final ArrayList<Item> uniqueInventory = new ArrayList<>();
    @Serial
    private static final long serialVersionUID = -3364742123084557236L;

    public InventoryWindowChest(Chest chest){
        super("Сундук");
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {}

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {
                chest.setInventoryChestOpen(true);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                chest.setInventoryChestOpen(false);
            }
        });
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
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        int width = 600;
        for (Item item : chest.getInventory()){
            JPanel itemPanel = new JPanel();
            itemPanel.setToolTipText(item.getEnchantDescription());
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
                case ABOVE_THE_GODS: colorForeground = new Color(255, 0, 197); break;
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

            if (item instanceof Weapon weapon) {
                property.setText("Урон: ");
                propertyCount.setText(Integer.toString(weapon.getClearDamage()));
            } else if(item instanceof Ring ring){
                property.setText(ring.getStat() + ": ");
                propertyCount.setText(Integer.toString(ring.getStatPower()));
            } else if (item instanceof Armor armor){
                property.setText("Защита: ");
                propertyCount.setText(Integer.toString(armor.getProtection()));
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
            JButton useButton = new UnfocusedButton("Взять");

            useButton.addActionListener((ActionListener & Serializable) e -> {
                player.addItemToInventory(item);
                chest.removeItemFromInventory(item);
                drawInventory();
                WindowInterface windowInterface = player.getWindowInterface();
                windowInterface.drawAllPlayerWindow(player, windowInterface);
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
        int height = 480;
        scroll.setPreferredSize(new Dimension(width, height));
        getContentPane().add(scroll);
        pack();
        setVisible(true);
    }

    public void setPlayer(Player player) {
        this.player = player;
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
