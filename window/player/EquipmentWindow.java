package window.player;

import creature.Player;
import item.Item;
import item.armor.Armor;
import item.armor.Helmet;
import item.armor.Ring;
import item.armor.Torso;
import item.weapon.Weapon;
import utils.ColoringProfile;
import window.menu.AbstractMenu;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class EquipmentWindow extends AbstractMenu implements Serializable, PlayerPanel, ColoringProfile {

    private final Player player;

    @Serial
    private static final long serialVersionUID = 3557302482173437655L;

    public EquipmentWindow(Player player) {
        this.player = player;
        setLayout(new GridBagLayout());
        drawWindow();
    }

    public JPanel drawWindow() {
        removeAll();
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
            int width = 480;
            itemPanel.setPreferredSize(new Dimension(width, 40));
            GridBagConstraints itemConstraints = new GridBagConstraints();
            itemConstraints.anchor = GridBagConstraints.WEST;
            itemConstraints.insets = new Insets(20, 10, 20, 10);
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 0;

            Color colorForeground = getColorByGrade(item);
            Color colorBackground = getColorByRarity(item);

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

            add(itemPanel, constraints);

            constraints.gridy ++;
        }
        return this;
    }
}
