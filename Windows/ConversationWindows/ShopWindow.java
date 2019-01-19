package Windows.ConversationWindows;

import Items.Item;
import LiveCreatures.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShopWindow extends JFrame {

    private Player player;
    private ArrayList<Object> catalog = new ArrayList<Object>();
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints;

    public ShopWindow(Player player, ArrayList<Object> catalog){
        super("Магазин");

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

                JLabel itemName = new JLabel(((Item)(((Object[])obj)[0])).getName());
                itemPanel.add(itemName, itemConstraints);
                itemConstraints.gridx = 1;
                JLabel itemCost = new JLabel(Integer.toString(price));
                itemPanel.add(itemCost, itemConstraints);
                itemConstraints.gridx = 2;
                JLabel itemCount = new JLabel(Integer.toString(count));
                itemPanel.add(itemCount, itemConstraints);
                itemConstraints.gridx = 3;
                JButton itemBuy = new JButton("Купить");

                itemBuy.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (count > 0 && player.getMoney() >= price){
                            Item item = ((Item)(((Object[])obj)[0]));
                            player.addItemToInventory(item);
                            player.reduceMoney(price);
                            (((Object[])obj)[2]) = ((int)(((Object[])obj)[2]) - 1);
                            drawWindow();
                        }
                    }
                });
                itemPanel.add(itemBuy, itemConstraints);

                panel.add(itemPanel, constraints);
                constraints.gridy ++;
            }
        }

        getContentPane().add(panel);
        pack();
        setVisible(true);
    }
}
