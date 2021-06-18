package window.conversation;

import abilities.Ability;
import conversation.CatalogItem;
import creature.Player;
import window.player.UnfocusedButton;
import window.WindowInterface;
import support.Sellable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class ShopAbilityWindow extends JFrame implements Serializable {

    private final ArrayList<CatalogItem> catalog;
    private JPanel panel = new JPanel(new GridBagLayout());
    private Player player;

    public ShopAbilityWindow(Player player, ArrayList<CatalogItem> catalog){
        super("Магазин");
        setAlwaysOnTop(true);

        this.player = player;
        this.catalog = catalog;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        drawWindow();
    }

    private void drawWindow(){

        getContentPane().remove(panel);

        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for(CatalogItem cat : catalog) {
            Sellable sellable = cat.sellable();
            int price = cat.price();
            int count = cat.count();

            JPanel itemPanel = new JPanel(new GridBagLayout());
            GridBagConstraints itemConstraints = new GridBagConstraints();

            itemConstraints.anchor = GridBagConstraints.WEST;
            itemConstraints.insets = new Insets(0, 30, 0, 0);
            itemConstraints.gridx = 0;
            itemConstraints.gridy = 0;

            JLabel sellablePanel = new JLabel(sellable.getName());
            itemPanel.add(sellablePanel, itemConstraints);
            itemConstraints.gridx = 1;

            JLabel itemCost = new JLabel(Integer.toString(price));
            itemPanel.add(itemCost, itemConstraints);
            itemConstraints.gridx = 2;

            JLabel itemCount = new JLabel(Integer.toString(count));
            itemPanel.add(itemCount, itemConstraints);
            itemConstraints.gridx = 3;
            JButton buy = new UnfocusedButton("Купить");

            buy.addActionListener((ActionListener & Serializable)  e -> {
                if (count > 0 && player.getMoney() >= price && sellable instanceof Ability ability){
                    if (!player.hasAbility(ability.getLastProperty())){
                        player.reduceMoney(price);
                        cat.setCount(count - 1);
                        drawWindow();
                        player.addAbility(ability);
                        WindowInterface windowInterface = player.getWindowInterface();
                        windowInterface.drawAllPlayerWindow(player, windowInterface);
                    }
                }
            });
            itemPanel.add(buy, itemConstraints);

            panel.add(itemPanel, constraints);
            constraints.gridy ++;
        }

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}