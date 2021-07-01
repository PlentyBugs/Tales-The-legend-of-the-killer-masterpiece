package window.conversation;

import conversation.TablePart;
import creature.LiveCreature;
import creature.Player;
import window.Screen;
import window.Switcher;
import window.menu.AbstractMenu;
import window.player.UnfocusedButton;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractShop extends AbstractMenu {
    protected LiveCreature seller;
    protected Switcher switcher;
    protected int width;
    protected final JTableModel dtm = new JTableModel();
    protected final JTable itemStock = new JTable();
    protected final JPanel choose = new JPanel(new BorderLayout());
    protected JScrollPane scroll = new JScrollPane(itemStock);
    protected Player player;

    public AbstractShop(int anchorWidth, Player player, LiveCreature seller, Switcher switcher) {
        this.player = player;
        this.seller = seller;
        this.switcher = switcher;
        this.width = WIDTH - anchorWidth;
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
        JButton backButton = new UnfocusedButton("←");
        customizeButton(backButton);
        backButton.addActionListener((e) -> switcher.switchScreen(Screen.CONVERSATION));
        Dimension backButtonSize = new Dimension((int) (width * 0.1), HEIGHT / 15);
        backButton.setMinimumSize(backButtonSize);
        backButton.setPreferredSize(backButtonSize);
        backButton.setMaximumSize(backButtonSize);
        choose.add(backButton, BorderLayout.WEST);
        setBackground(STYLED_COLOR_LIGHT);
    }

    protected abstract void printItems();

    public void setPlayer(Player player) {
        boolean playerIsNull = this.player == null;
        this.player = player;
        printItems();
    }

    private static class JTableButtonRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return (JButton)value;
        }
    }

    public static class JTableModel extends AbstractTableModel {
        @Serial
        private static final long serialVersionUID = 1L;
        private static final String[] COLUMN_NAMES = new String[] {"Staff", "Property", "Price", "Count", ""};
        private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {String.class, String.class, String.class, String.class,  JButton.class};
        private final List<TablePart> data = new ArrayList<>();

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

        public void clear() {
            data.clear();
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
}
