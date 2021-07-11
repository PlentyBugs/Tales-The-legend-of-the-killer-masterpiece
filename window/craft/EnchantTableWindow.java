package window.craft;

import creature.Player;
import thing.craft.EnchantTable;
import utils.PanelProvider;
import window.Screen;
import window.player.UnfocusedButton;

import javax.swing.*;
import java.awt.*;

public class EnchantTableWindow extends CraftWindow {

    private final int width = 720;
    private final int height = 480;
    private final JPanel panel;
    private Player player;

    public EnchantTableWindow(EnchantTable parent) {

        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

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
        add(panel);
        setVisible(true);
    }

    private JPanel buildRemoveEnchantmentPanel(){
        return buildPanel("Зачаровать", Color.MAGENTA);
    }

    private JPanel buildAddEnchantmentPanel(){
        return buildPanel("Снять зачарование", Color.CYAN);
    }

    private JPanel buildPanel(
            String functionalButton,
            Color buttonColor
    ) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = PanelProvider.getEmptyGBC();

        panel.setPreferredSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));

        JPanel buttons = new JPanel(new BorderLayout());

        JButton itemEnchantButton = new UnfocusedButton("Пусто(Предмет)");
        Dimension dimension = new Dimension(width / 2, height / 2);
        itemEnchantButton.setPreferredSize(dimension);
        itemEnchantButton.setMaximumSize(dimension);
        itemEnchantButton.setMinimumSize(dimension);

        JButton enchantStoneButton = new UnfocusedButton("Пусто(Камень зачарования)");
        enchantStoneButton.setPreferredSize(dimension);
        enchantStoneButton.setMaximumSize(dimension);
        enchantStoneButton.setMinimumSize(dimension);

        JButton addButton = new UnfocusedButton(functionalButton);
        addButton.setBackground(buttonColor);
        Dimension preferredSize = new Dimension(width, height / 2);
        addButton.setPreferredSize(preferredSize);
        addButton.setMaximumSize(preferredSize);
        addButton.setMinimumSize(preferredSize);

        buttons.add(itemEnchantButton, BorderLayout.WEST);
        buttons.add(enchantStoneButton, BorderLayout.EAST);
        panel.add(buttons, constraints);
        constraints.gridy = 1;
        panel.add(addButton, constraints);

        return panel;
    }

    public void setIsVisible(boolean visible){
        drawWindow();
        setVisible(visible);
    }

    protected void close(Screen screen) {}

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
