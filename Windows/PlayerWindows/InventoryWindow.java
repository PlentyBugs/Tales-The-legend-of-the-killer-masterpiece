package Windows.PlayerWindows;

import Creatures.Player;
import Items.Alchemy.Potions.Potion;
import Items.Armors.Armor;
import Items.Armors.Ring;
import Items.BlackSmith.Resource.Resource;
import Items.Item;
import Items.Weapons.Weapon;
import Windows.WindowInterface;
import support.GeneralProperty;
import support.ItemProperty;
import support.Property;
import support.ResourceProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Consumer;

public class InventoryWindow extends JFrame implements Serializable {
    @Serial
    private static final long serialVersionUID = -559721917387219997L;

    private final Player player;
    private JPanel panel = new JPanel(new GridBagLayout());
    private JScrollPane scroll = new JScrollPane(panel);
    private JPanel menuPanel = new JPanel();
    private final int width = 720;
    private final int height = 720;
    private JPanel panelz = new JPanel();
    private Property currentInventory = GeneralProperty.ALL;
    // Вообще костыль, надо будет переделать потом
    private final ArrayList<Item> uniqueInventory = new ArrayList<>();

    public InventoryWindow(Player player){
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

    public void setIsVisible() {
        drawInventory();
    }

    public void drawInventory(){
        drawInventory(true);
    }

    public void drawInventory(Boolean isDrawMap){
        menuPanel = new JPanel();

        JButton allInventory = new UnfocusedButton("Всё");
        JButton weaponInventory = new UnfocusedButton("Оружие");
        JButton armorInventory = new UnfocusedButton("Броня");
        JButton potionInventory = new UnfocusedButton("Зелья");
        JButton ingredientInventory = new UnfocusedButton("Ингредиенты");
        JButton resourceInventory = new UnfocusedButton("Ресурсы");

        menuPanel.add(allInventory);
        menuPanel.add(weaponInventory);
        menuPanel.add(armorInventory);
        menuPanel.add(potionInventory);
        menuPanel.add(ingredientInventory);
        menuPanel.add(resourceInventory);

        // Я к этому коду 2 года не притрагивался, вообще не знаю что тут происходит, но он мне не нравится
        // Буду пытаться методом тыка делать его лучше
        Consumer<Property> draw = (inv) -> {
            currentInventory = inv;
            drawInventory();
            if(player != null){
                if(isDrawMap) {
                    WindowInterface windowInterface = player.getWindowInterface();
                    windowInterface.drawAllPlayerWindow(player, windowInterface);
                }
            }
        };

        allInventory.addActionListener((ActionListener & Serializable) e -> draw.accept(GeneralProperty.ALL));
        weaponInventory.addActionListener((ActionListener & Serializable) e -> draw.accept(ItemProperty.WEAPON));
        armorInventory.addActionListener((ActionListener & Serializable) e -> draw.accept(ItemProperty.ARMOR));
        potionInventory.addActionListener((ActionListener & Serializable) e -> draw.accept(ItemProperty.POTION));
        ingredientInventory.addActionListener((ActionListener & Serializable) e -> draw.accept(ItemProperty.INGREDIENT));
        resourceInventory.addActionListener((ActionListener & Serializable) e -> draw.accept(ResourceProperty.RESOURCE));

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        uniqueInventory.clear();
        for (Item item : player.getInventory()) {
            if(!uniqueInventoryContains(item)) {
                uniqueInventory.add(item);
            } else if(player.countOfItemInInventory(item) > 1 && item.getStackable()) {
                continue;
            }

            if(!item.getProperties().contains(currentInventory)) {
                continue;
            }

            JPanel itemPanel = new JPanel(new GridBagLayout());

            if(item instanceof Resource resource)
                itemPanel.setToolTipText("<html><p>Температура: <b>" + resource.getTemperature() + "</b></p><br><p>Температура плавления: <b>" + resource.getMaxTemperature() + "</b></p></html>");
            else
                itemPanel.setToolTipText(item.getEnchantDescription());

            itemPanel.setPreferredSize(new Dimension(width, 40));
            itemPanel.setMinimumSize(new Dimension(width, 40));
            itemPanel.setMaximumSize(new Dimension(width, 40));
            GridBagConstraints itemConstraints = new GridBagConstraints();
            itemConstraints.anchor = GridBagConstraints.WEST;
            itemConstraints.insets = new Insets(0, 2, 0, 2);
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 0;
            JLabel propertyCount = new JLabel();

            new Color(0, 0, 0);
            Color colorForeground = item.getGrade().color;

            Color colorBackground;
            colorBackground = item.getRarity().color;

            JLabel itemName = new JLabel(item.getName());
            JLabel itemQuality = new JLabel("Прочность: " + item.getQuality());
            JLabel property = new JLabel();
            
            if (item instanceof Weapon weapon){
                property.setText("Урон: ");
                propertyCount.setText(Integer.toString(weapon.getClearDamage()));
            } else if(item instanceof Ring ring){
                property.setText(ring.getStat() + ": ");
                propertyCount.setText(Integer.toString(ring.getStatPower()));
            } else if (item instanceof Armor armor){
                property.setText("Защита: ");
                propertyCount.setText(Integer.toString(armor.getProtection()));
            } else if (item instanceof Potion potion){
                property.setText("Мощность: ");
                propertyCount.setText(Integer.toString(potion.getEffect().getPower()));
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


            JPanel labels = new JPanel(new GridBagLayout());
            labels.setPreferredSize(new Dimension(width,20));
            labels.setMaximumSize(new Dimension(width,20));
            labels.setMinimumSize(new Dimension(width,20));
            labels.setBackground(colorBackground);
            GridBagConstraints labelsConstraints = new GridBagConstraints();
            labelsConstraints.anchor = GridBagConstraints.WEST;
            labelsConstraints.insets = new Insets(0, 0, 0, 5);
            labelsConstraints.gridx = 0;
            labelsConstraints.gridy = 0;

            labels.add(itemName, labelsConstraints);
            labelsConstraints.gridx ++;
            labels.add(itemQuality, labelsConstraints);
            labelsConstraints.gridx ++;
            labels.add(property, labelsConstraints);
            labelsConstraints.gridx ++;
            labels.add(propertyCount, labelsConstraints);
            labelsConstraints.gridx ++;
            labels.add(ccunt, labelsConstraints);
            itemPanel.add(labels, itemConstraints);
            itemConstraints.gridy ++;

            JButton useButton = new UnfocusedButton("Экипировать");

            if(!(item instanceof Weapon || item instanceof Armor)){
                itemQuality.setText("");
                useButton.setText("Использовать");
            }
            useButton.setPreferredSize(new Dimension(width/3,20));
            useButton.setMaximumSize(new Dimension(width/3,20));
            useButton.setMinimumSize(new Dimension(width/3,20));

            WindowInterface windowInterface = player.getWindowInterface();
            if(item instanceof Potion potion){
                useButton.addActionListener((ActionListener & Serializable)e -> {
                    potion.use(player);
                    player.removeItem(item);
                    drawInventory();
                    if(isDrawMap) {
                        windowInterface.drawAllPlayerWindow(player, windowInterface);
                    }
                });
            } else {
                useButton.addActionListener((ActionListener & Serializable) e -> {
                    player.equip(item);
                    if(player.getEquipmentWindow() != null) {
                        player.getEquipmentWindow().drawEquipment();
                        if(isDrawMap) {
                            windowInterface.drawAllPlayerWindow(player, windowInterface);
                        }
                    }
                });
            }

            JButton removeButton = new UnfocusedButton("Выбросить");
            removeButton.addActionListener((ActionListener & Serializable)e -> {
                player.removeItem(item);
                if(isDrawMap) {
                    windowInterface.drawAllPlayerWindow(player, windowInterface);
                }
            });
            removeButton.setPreferredSize(new Dimension(width/3,20));
            removeButton.setMaximumSize(new Dimension(width/3,20));
            removeButton.setMinimumSize(new Dimension(width/3,20));

            JPanel buttons = new JPanel(new GridBagLayout());
            buttons.setPreferredSize(new Dimension(width,20));
            buttons.setMaximumSize(new Dimension(width,20));
            buttons.setMinimumSize(new Dimension(width,20));
            GridBagConstraints buttonsConstraints = new GridBagConstraints();
            buttonsConstraints.anchor = GridBagConstraints.WEST;
            buttonsConstraints.insets = new Insets(0, 0, 0, 5);
            buttonsConstraints.gridx = 0;
            buttonsConstraints.gridy = 0;

            buttons.add(useButton, buttonsConstraints);
            buttonsConstraints.gridx ++;
            buttons.add(removeButton, buttonsConstraints);
            itemPanel.add(buttons, itemConstraints);

            buttons.setBackground(colorBackground);

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
