package Windows.ConversationWindows;

import Abilities.Ability;
import Conversations.CatalogStockTypeOfItem;
import Creatures.Player;
import Items.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class ShopAbilityWindow extends JFrame implements Serializable {

    private Player player;
    private ArrayList<Object> catalog = new ArrayList<Object>();
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints;

    public ShopAbilityWindow(Player player, ArrayList<Object> catalog){
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
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = 0;

        for(Object cat : catalog){
            for(Object obj : (ArrayList)cat){

                int price = (int)(((Object[])obj)[1]);
                int count = (int)(((Object[])obj)[2]);

                JPanel itemPanel = new JPanel(new GridBagLayout());
                GridBagConstraints itemConstraints = new GridBagConstraints();

                itemConstraints.anchor = GridBagConstraints.WEST;
                itemConstraints.insets = new Insets(0, 30, 0, 0);
                itemConstraints.gridx = 0;
                itemConstraints.gridy = 0;


                if (((Object[])obj)[3] == CatalogStockTypeOfItem.ITEM){
                    JLabel itemName = new JLabel(((Item)(((Object[])obj)[0])).getName());
                    itemPanel.add(itemName, itemConstraints);
                    itemConstraints.gridx = 1;
                } else if (((Object[])obj)[3] == CatalogStockTypeOfItem.ABILITY){
                    JLabel abilityName = new JLabel(((Ability)(((Object[])obj)[0])).getName());
                    itemPanel.add(abilityName, itemConstraints);
                    itemConstraints.gridx = 1;
                }
                JLabel itemCost = new JLabel(Integer.toString(price));
                itemPanel.add(itemCost, itemConstraints);
                itemConstraints.gridx = 2;
                JLabel itemCount = new JLabel(Integer.toString(count));
                itemPanel.add(itemCount, itemConstraints);
                itemConstraints.gridx = 3;
                JButton buy = new JButton("Купить");

                buy.addActionListener((ActionListener & Serializable)  e -> {
                    if (count > 0 && player.getMoney() >= price){
                        Ability ability = ((Ability)(((Object[])obj)[0]));
                        if (!player.hasAbility(ability)){
                            player.reduceMoney(price);
                            (((Object[])obj)[2]) = ((int)(((Object[])obj)[2]) - 1);
                            drawWindow();
                            player.addAbility(ability);
                            player.getFieldWindow().drawAllPlayerWindow();
                        }
                    }
                });
                itemPanel.add(buy, itemConstraints);

                panel.add(itemPanel, constraints);
                constraints.gridy ++;
            }
        }

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}