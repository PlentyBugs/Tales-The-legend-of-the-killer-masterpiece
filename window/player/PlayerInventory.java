package window.player;

import conversation.TablePart;
import creature.Player;
import item.Item;
import item.alchemy.potion.Potion;
import item.armor.Armor;
import item.blacksmith.resource.Resource;
import item.weapon.Weapon;
import support.ItemProperty;
import support.Property;
import support.ResourceProperty;
import utils.Constants;
import utils.Pair;
import window.conversation.AbstractInventory;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerInventory extends AbstractInventory implements Serializable, PlayerPanel {
    @Serial
    private static final long serialVersionUID = -559721917387219997L;

    private Property currentInventory = ItemProperty.ITEM;

    public PlayerInventory(Player player) {
        super(
                Constants.getFULLSCREEN().height,
                player,
                new String[] {"Item", "Property", "Price", "Count", ""},
                new Class<?>[] {String.class, String.class, String.class, String.class,  JButton.class}
        );
        itemStock.setFont(FONT_SMALL);
        JTableHeader tableHeader = itemStock.getTableHeader();
        TableColumnModel columnModel = tableHeader.getColumnModel();
        TableColumn nameColumn = columnModel.getColumn(0);
        TableColumn countColumn = columnModel.getColumn(3);
        nameColumn.setPreferredWidth((int) (nameColumn.getWidth() * 2.5));
        countColumn.setPreferredWidth((int) (countColumn.getWidth() * 0.05));
        tableHeader.repaint();

        Box menuPanel = Box.createHorizontalBox();
        final int buttonWidth = width / 6;
        final int buttonHeight = HEIGHT / 20;
        Stream.of(
                new Pair<String, Property>("All", ItemProperty.ITEM),
                new Pair<String, Property>("Weapon", ItemProperty.WEAPON),
                new Pair<String, Property>("Armor", ItemProperty.ARMOR),
                new Pair<String, Property>("Potion", ItemProperty.POTION),
                new Pair<String, Property>("Ingredient", ItemProperty.INGREDIENT),
                new Pair<String, Property>("Resource", ResourceProperty.RESOURCE)
        ).forEachOrdered(tab -> {
            UnfocusedButton tabButton = new UnfocusedButton(tab.first());
            customizeButton(tabButton);
            tabButton.setFont(FONT_MEDIUM);
            Dimension BUTTON_SIZE = new Dimension(buttonWidth, buttonHeight);
            tabButton.setMinimumSize(BUTTON_SIZE);
            tabButton.setMaximumSize(BUTTON_SIZE);
            tabButton.setPreferredSize(BUTTON_SIZE);
            tabButton.addActionListener(e -> {
                currentInventory = tab.second();
                printItems();
            });
            menuPanel.add(tabButton);
        });

        add(menuPanel);

        scroll = new JScrollPane(itemStock);
        Dimension scrollSize = new Dimension(width, HEIGHT * 14 / 15);
        scroll.setMinimumSize(scrollSize);
        scroll.setPreferredSize(scrollSize);
        scroll.setMaximumSize(scrollSize);
        customizeScroll(scroll);

        add(scroll, BorderLayout.SOUTH);
        setVisible(true);
        printItems();
    }

    public void printItems() {
        dtm.clear();
        Map<Item, Integer> uniqueItemsWithCount =
                getUniqueItemsWithCount(player)
                .entrySet()
                .stream().filter((set) -> set.getKey().getProperties().contains(currentInventory))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        JTableHeader tableHeader = itemStock.getTableHeader();
        TableColumnModel columnModel = tableHeader.getColumnModel();
        TableColumn column = columnModel.getColumn(1);
        if (currentInventory == ItemProperty.WEAPON) {
            column.setHeaderValue("Damage");
        } else if (currentInventory == ItemProperty.RING) {
            column.setHeaderValue("Stat");
        } else if (currentInventory == ItemProperty.ARMOR) {
            column.setHeaderValue("Protection");
        } else if (currentInventory == ItemProperty.POTION || currentInventory == ItemProperty.INGREDIENT) {
            column.setHeaderValue("Power");
        } else {
            column.setHeaderValue("Property");
        }
        tableHeader.repaint();

        uniqueItemsWithCount.forEach((item, count) -> {
            UnfocusedButton button = getItemAction(item);
            button.addActionListener(e -> {});

            button.setBackground(STYLED_COLOR);
            button.setFont(FONT_MEDIUM);

            TablePart tablePart = new TablePart(
                    item.getFullName(),
                    item.getItemProperty(),
                    item.getCost(),
                    count,
                    button
            );

            dtm.addRow(tablePart);
            dtm.addToolTipToLastAddedRow(
                    item instanceof Resource resource
                    ? "<html><p>Температура: <b>" + resource.getTemperature() + "</b></p><br><p>Температура плавления: <b>" + resource.getMaxTemperature() + "</b></p></html>"
                    : item.getEnchantDescription()
            );
        });

        revalidate();
        repaint();
        itemStock.revalidate();
        itemStock.repaint();
    }

    private UnfocusedButton getItemAction(Item item) {
        UnfocusedButton useButton = new UnfocusedButton("");
        if(item instanceof Potion potion){
            useButton.setText("Use");
            useButton.addActionListener((ActionListener & Serializable) e -> {
                potion.use(player);
                player.removeItem(item);
                printItems();
            });
        } else if (item instanceof Resource) {
            useButton.setText("Inspect");
        } else if (item instanceof Armor || item instanceof Weapon) {
            useButton.setText("Equip");
            useButton.addActionListener((ActionListener & Serializable) e -> player.equip(item));
        }
        return useButton;
    }

    @Override
    public JPanel drawWindow() {
        printItems();
        return this;
    }
}
