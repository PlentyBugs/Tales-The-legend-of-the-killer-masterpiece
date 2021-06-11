package Windows.PlayerWindows;

import Creatures.Player;
import Items.Alchemy.Potions.Potion;
import Items.Armors.Armor;
import Items.Armors.Ring;
import Items.BlackSmith.Resource.Resource;
import Items.Item;
import Items.Weapons.Weapon;
import Windows.WindowInterface;

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
    private String currentInventory = "All";
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
        Consumer<String> draw = (inv) -> {
            currentInventory = inv;
            drawInventory();
            if(player.getClass().toString().contains("Player")){
                if(isDrawMap) {
                    WindowInterface windowInterface = player.getWindowInterface();
                    windowInterface.drawAllPlayerWindow(player, windowInterface);
                }
            }
        };

        allInventory.addActionListener((ActionListener & Serializable) e -> draw.accept("All"));
        weaponInventory.addActionListener((ActionListener & Serializable) e -> draw.accept("Weapon"));
        armorInventory.addActionListener((ActionListener & Serializable) e -> draw.accept("Armor"));
        potionInventory.addActionListener((ActionListener & Serializable) e -> draw.accept("Potion"));
        ingredientInventory.addActionListener((ActionListener & Serializable) e -> draw.accept("Ingredient"));
        resourceInventory.addActionListener((ActionListener & Serializable) e -> draw.accept("Resource"));

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

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
            Color colorForeground = switch (item.getGrade()) {
                case COMMON -> new Color(0, 0, 0);
                case MAGIC -> new Color(128, 255, 80);
                case CURSE -> new Color(1, 155, 24);
                case ARTIFACT -> new Color(255, 0, 18);
                case HEROIC -> new Color(255, 96, 0);
                case ABOVETHEGODS -> new Color(255, 0, 197);
            };

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

            JLabel itemName = new JLabel(item.getName());
            JLabel itemQuality = new JLabel("Прочность: " + item.getQuality());
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
            if(!item.getClass().toString().contains("Alchemy")){
                useButton.addActionListener((ActionListener & Serializable) e -> {
                    player.equip(item);
                    if(player.getClass().toString().contains("Player") && player.getEquipmentWindow() != null){
                        player.getEquipmentWindow().drawEquipment();
                        if(isDrawMap)
                            windowInterface.drawAllPlayerWindow(player, windowInterface);
                    }
                });
            } else {
                useButton.addActionListener((ActionListener & Serializable)e -> {
                    ((Potion) item).use(player);
                    player.removeItem(item);
                    drawInventory();
                    if(isDrawMap)
                        windowInterface.drawAllPlayerWindow(player, windowInterface);
                });
            }

            JButton removeButton = new UnfocusedButton("Выбросить");
            removeButton.addActionListener((ActionListener & Serializable)e -> {
                player.removeItem(item);
                if(isDrawMap)
                    windowInterface.drawAllPlayerWindow(player, windowInterface);
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
