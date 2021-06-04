package Windows.CraftWindow;

import Creatures.Player;
import Things.Craft.EnchantTable;
import Windows.PlayerWindows.UnfocusedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.io.Serializable;

public class EnchantTableWindow extends CraftWindow {

    private EnchantTable parent;
    private final int width = 720;
    private final int height = 480;
    private Player player;
    private JPanel panel;
    private GridBagConstraints constraints;

    public EnchantTableWindow(EnchantTable parent){
        super("Стол зачарования");
        setAlwaysOnTop(true);
        this.parent = parent;

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {}

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {
                parent.setCraftTableWindowOpen(true);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                parent.setCraftTableWindowOpen(false);
            }
        });

        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));

        panel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(0, 10, 0, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;

        drawWindow();
    }

    public void drawWindow(){

        JTabbedPane tab = new JTabbedPane();

        tab.add("Снять зачарование", buildRemoveEnchantmentPanel());
        tab.add("Наложить зачарование", buildAddEnchantmentPanel());

        panel.add(tab);
        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    private JPanel buildRemoveEnchantmentPanel(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        panel.setPreferredSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        JPanel buttons = new JPanel(new BorderLayout());

        JButton itemEnchantRemoveButton = new UnfocusedButton("Пусто(Предмет)");
        itemEnchantRemoveButton.addActionListener((ActionListener & Serializable) e -> {

        });
        itemEnchantRemoveButton.setPreferredSize(new Dimension(width/2, height/2));
        itemEnchantRemoveButton.setMaximumSize(new Dimension(width/2, height/2));
        itemEnchantRemoveButton.setMinimumSize(new Dimension(width/2, height/2));

        JButton enchantStoneButton = new UnfocusedButton("Пусто(Камень зачарования)");
        enchantStoneButton.setPreferredSize(new Dimension(width/2, height/2));
        enchantStoneButton.setMaximumSize(new Dimension(width/2, height/2));
        enchantStoneButton.setMinimumSize(new Dimension(width/2, height/2));

        JButton removeButton = new UnfocusedButton("Снять зачарование");
        removeButton.setBackground(Color.MAGENTA);
        removeButton.setPreferredSize(new Dimension(width, height/2));
        removeButton.setMaximumSize(new Dimension(width, height/2));
        removeButton.setMinimumSize(new Dimension(width, height/2));

        buttons.add(itemEnchantRemoveButton, BorderLayout.WEST);
        buttons.add(enchantStoneButton, BorderLayout.EAST);
        panel.add(buttons, constraints);
        constraints.gridy = 1;
        panel.add(removeButton, constraints);

        return panel;
    }

    private JPanel buildAddEnchantmentPanel(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        panel.setPreferredSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));

        JPanel buttons = new JPanel(new BorderLayout());

        JButton itemEnchantRemoveButton = new UnfocusedButton("Пусто(Предмет)");
        itemEnchantRemoveButton.setPreferredSize(new Dimension(width/2, height/2));
        itemEnchantRemoveButton.setMaximumSize(new Dimension(width/2, height/2));
        itemEnchantRemoveButton.setMinimumSize(new Dimension(width/2, height/2));

        JButton enchantStoneButton = new UnfocusedButton("Пусто(Камень зачарования)");
        enchantStoneButton.setPreferredSize(new Dimension(width/2, height/2));
        enchantStoneButton.setMaximumSize(new Dimension(width/2, height/2));
        enchantStoneButton.setMinimumSize(new Dimension(width/2, height/2));

        JButton addButton = new UnfocusedButton("Зачаровать");
        addButton.setBackground(Color.cyan);
        addButton.setPreferredSize(new Dimension(width, height/2));
        addButton.setMaximumSize(new Dimension(width, height/2));
        addButton.setMinimumSize(new Dimension(width, height/2));

        buttons.add(itemEnchantRemoveButton, BorderLayout.WEST);
        buttons.add(enchantStoneButton, BorderLayout.EAST);
        panel.add(buttons, constraints);
        constraints.gridy = 1;
        panel.add(addButton, constraints);

        return panel;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setIsVisible(boolean visible){
        drawWindow();
        setVisible(visible);
    }

    public void close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
