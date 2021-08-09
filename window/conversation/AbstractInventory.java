package window.conversation;

import conversation.TablePart;
import creature.LiveCreature;
import creature.Player;
import item.Item;
import window.menu.AbstractMenu;
import window.player.UnfocusedButton;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractInventory extends AbstractMenu {
    protected int width;
    protected final JTableModel dtm;
    protected final JTable itemStock = new JTable() {
        public String getToolTipText(MouseEvent e) {
            return dtm.getToolTipTextAtRow(rowAtPoint(e.getPoint()));
        }
    };
    protected JScrollPane scroll = new JScrollPane(itemStock);
    protected Player player;

    protected AbstractInventory(
            int anchorValue,
            Player player,
            String[] COLUMN_NAMES,
            Class<?>[] COLUMN_TYPES
    ) {
        this.player = player;
        this.width = WIDTH - anchorValue;
        dtm = new JTableModel(COLUMN_NAMES, COLUMN_TYPES);

        Dimension size = new Dimension(width, HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        itemStock.setShowGrid(false);
        itemStock.setModel(dtm);
        itemStock.setDefaultRenderer(JButton.class, new JTableButtonRenderer());
        itemStock.setFont(FONT_MEDIUM);
        itemStock.setIntercellSpacing(new Dimension(WIDTH / 100, HEIGHT / 100));
        itemStock.setRowHeight(HEIGHT / 20);
        itemStock.addMouseListener(new JTableButtonMouseListener());
        itemStock.setBackground(STYLED_COLOR_LIGHT);
        Dimension preferredScrollableViewportSize = itemStock.getPreferredScrollableViewportSize();
        preferredScrollableViewportSize.height = HEIGHT * 14 / 15;
        itemStock.setPreferredScrollableViewportSize(preferredScrollableViewportSize);
        itemStock.setMinimumSize(preferredScrollableViewportSize);
        itemStock.setFillsViewportHeight(true);
        JTableHeader tableHeader = itemStock.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setBackground(STYLED_COLOR_LIGHT);
        tableHeader.setFont(FONT_MEDIUM);
        setBackground(STYLED_COLOR_LIGHT);
    }

    private static class JTableButtonRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return (JButton) value;
        }
    }

    public static class JTableModel extends AbstractTableModel {
        @Serial
        private static final long serialVersionUID = 1L;
        private String[] COLUMN_NAMES;
        private Class<?>[] COLUMN_TYPES;
        private final List<TablePart> data = new ArrayList<>();
        private final Map<Integer, String> toolTipText = new HashMap<>();

        public JTableModel() {}

        public JTableModel(String[] COLUMN_NAMES, Class<?>[] COLUMN_TYPES) {
            this.COLUMN_NAMES = COLUMN_NAMES;
            this.COLUMN_TYPES = COLUMN_TYPES;
        }

        @Override
        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public String getColumnName(int columnIndex) {
            return COLUMN_NAMES[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return COLUMN_TYPES[columnIndex];
        }

        @Override
        public Object getValueAt(final int rowIndex, final int columnIndex) {
            return switch (columnIndex) {
                case 0 -> data.get(rowIndex).staff();
                case 1 -> data.get(rowIndex).property();
                case 2 -> data.get(rowIndex).price();
                case 3 -> data.get(rowIndex).count();
                case 4 -> data.get(rowIndex).action();
                default -> "Error";
            };
        }

        public void addRow(TablePart tablePart) {
            data.add(tablePart);
        }

        public void removeRow(TablePart tablePart) {
            data.remove(tablePart);
        }

        public void addToolTipToLastAddedRow(String text) {
            toolTipText.put(data.size() - 1, text);
        }

        public String getToolTipTextAtRow(int row) {
            return toolTipText.getOrDefault(row, "");
        }

        public void clear() {
            data.clear();
            toolTipText.clear();
        }
    }

    private class JTableButtonMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            int column = itemStock.getColumnModel().getColumnIndexAtX(e.getX());
            int row = e.getY() / itemStock.getRowHeight();

            if (row < itemStock.getRowCount() && row >= 0 && column < itemStock.getColumnCount() && column >= 0) {
                Object value = itemStock.getValueAt(row, column);
                if (value instanceof JButton button) {
                    button.doClick();
                }
            }
        }
    }

    public void printItems() {
        Map<Item, Integer> uniqueItemsWithCount = getUniqueItemsWithCount(player);

        UnfocusedButton button = new UnfocusedButton("Button");

        button.addActionListener((ActionListener & Serializable) e -> {});

        button.setBackground(STYLED_COLOR);
        button.setFont(FONT_MEDIUM);

        uniqueItemsWithCount.forEach((item, count) -> {
            TablePart tablePart = new TablePart(
                    item.getName(),
                    item.getItemProperty(),
                    item.getCost(),
                    count,
                    button
            );

            dtm.addRow(tablePart);
        });

        revalidate();
        repaint();
        itemStock.revalidate();
        itemStock.repaint();
    }

    protected Map<Item, Integer> getUniqueItemsWithCount(LiveCreature creature) {
        Map<Item, Integer> map = new HashMap<>();
        for(Item item : creature.getInventory()) {
            if (item.getCost() == 0) {
                item.countCost();
            }
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        return map;
    }
}
