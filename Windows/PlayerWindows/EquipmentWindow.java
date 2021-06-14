package Windows.PlayerWindows;

import Creatures.Player;
import Items.Armors.Armor;
import Items.Armors.Helmet;
import Items.Armors.Ring;
import Items.Armors.Torso;
import Items.Item;
import Items.Weapons.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.io.Serializable;

public class EquipmentWindow extends JFrame implements Serializable {

    private final Player player;
    private JPanel panel = new JPanel();
    private final int width = 480;

    @Serial
    private static final long serialVersionUID = 3557302482173437655L;

    public EquipmentWindow(Player player){
        super("Экипировка");
        int height = 240;
        setMinimumSize(new Dimension(width, height));

        this.player = player;
        drawEquipment();
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void setIsVisible(boolean b) {
        drawEquipment();
    }

    public void drawEquipment(){
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for(Item item : player.getEquipment().getListOfEquipment()){

            if (item == null){
                continue;
            }

            JPanel itemPanel = new JPanel();
            itemPanel.setToolTipText(item.getEnchantDescription());
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
                case MAGIC -> new Color(128, 255, 80);
                case CURSE -> new Color(1, 155, 24);
                case ARTIFACT -> new Color(255, 0, 18);
                case HEROIC -> new Color(255, 96, 0);
                case ABOVE_THE_GODS -> new Color(255, 0, 197);
            };

            colorBackground = getColorByRarity(item);

            JLabel equipmentItem = new JLabel();

            itemConstraints.gridx = 1;
            JLabel itemName = new JLabel(item.getName());
            itemConstraints.gridx = 2;
            JLabel itemQuality = new JLabel(Double.toString(item.getQuality()));

            itemConstraints.gridx = 2;
            JLabel property = new JLabel();
            itemConstraints.gridx = 3;
            JLabel propertyCount = new JLabel();

            if (item instanceof Weapon weapon) {
                equipmentItem.setText("Оружие: ");
                property.setText("Урон: ");
                propertyCount.setText(Integer.toString(weapon.getClearDamage()));
            } else if(item instanceof Ring ring) {
                equipmentItem.setText("Кольцо: ");
                property.setText(ring.getStat() + ": ");
                propertyCount.setText(Integer.toString(ring.getStatPower()));
            } else if (item instanceof Armor armor) {
                if (item instanceof Torso) {
                    equipmentItem.setText("Торс: ");
                } else if (item instanceof Helmet){
                    equipmentItem.setText("Шлем: ");
                }
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

            itemPanel.add(equipmentItem, itemConstraints);
            itemPanel.add(itemName, itemConstraints);
            itemPanel.add(itemQuality, itemConstraints);
            itemPanel.add(property, itemConstraints);
            itemPanel.add(propertyCount, itemConstraints);

            itemPanel.setBackground(colorBackground);

            panel.add(itemPanel, constraints);

            constraints.gridy ++;
        }
        pack();
        if(player.getWindowInterface() != null) player.getWindowInterface().drawMap();
    }

    public static Color getColorByRarity(Item item) {
        Color colorBackground;
        colorBackground = switch (item.getRarity()) {
            case COMMON -> new Color(255, 255, 255, 100);
            case UNCOMMON -> new Color(0, 115, 255, 100);
            case RARE -> new Color(12, 0, 255, 100);
            case MYSTICAL -> new Color(255, 0, 119, 100);
            case LEGENDARY -> new Color(255, 232, 0, 100);
            case DRAGON -> new Color(255, 9, 0, 100);
            case DIVINE -> new Color(255, 169, 0, 100);
        };
        return colorBackground;
    }

    public JPanel getPanel() {
        return panel;
    }
}
