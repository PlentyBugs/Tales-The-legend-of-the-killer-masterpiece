package Windows.ConversationWindows;

import Creatures.LiveCreature;
import Creatures.Player;
import Items.Alchemy.Potions.Potion;
import Items.Armors.Armor;
import Items.Item;
import Items.Weapons.Weapon;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class ShopWindow extends JFrame implements Serializable {

    private Player player;
    private JPanel panel = new JPanel(new GridBagLayout());
    private LiveCreature seller;
    private JScrollPane scroll = new JScrollPane(panel);
    private GridBagConstraints constraints;
    private String chosenPanel;
    private int width;
    private int height;

    public ShopWindow(Player player, LiveCreature seller){
        super("Магазин");

        setAlwaysOnTop(true);

        this.player = player;
        width = 720;
        height = 720;
        chosenPanel = "buy";
        this.seller = seller;
        setMinimumSize(new Dimension(width, height));
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        drawWindow();
    }

    private void drawWindow(){

        getContentPane().remove(scroll);

        panel = new JPanel(new GridBagLayout());
        scroll = new JScrollPane(panel);
        scroll.setMinimumSize(new Dimension(width, height-90));
        scroll.setPreferredSize(new Dimension(width, height-90));
        scroll.setMaximumSize(new Dimension(width, height-90));
        constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        JPanel choose = new JPanel(new BorderLayout());
        JButton buyButton = new JButton("Купить");
        JButton sellButton = new JButton("Продать");
        buyButton.addActionListener(e -> {
            chosenPanel = "buy";
            drawWindow();
        });
        sellButton.addActionListener(e -> {
            chosenPanel = "sell";
            drawWindow();
        });
        buyButton.setMinimumSize(new Dimension(width/2, 50));
        buyButton.setPreferredSize(new Dimension(width/2, 50));
        buyButton.setMaximumSize(new Dimension(width/2, 50));
        sellButton.setMinimumSize(new Dimension(width/2, 50));
        sellButton.setPreferredSize(new Dimension(width/2, 50));
        sellButton.setMaximumSize(new Dimension(width/2, 50));

        choose.add(buyButton, BorderLayout.WEST);
        choose.add(sellButton, BorderLayout.EAST);

        getContentPane().add(choose, BorderLayout.NORTH);
        constraints.gridy ++;

        if(chosenPanel.equals("buy")){

            for(Item item : seller.getInventory()){

                item.countCost();
                int price = (int)(item.getCost()*Math.min(12, Math.pow(1.005, seller.getStats().getEloquence() - player.getStats().getEloquence())));

                JPanel itemPanel = new JPanel(new GridBagLayout());
                GridBagConstraints itemConstraints = new GridBagConstraints();

                itemConstraints.anchor = GridBagConstraints.WEST;
                itemConstraints.insets = new Insets(5, 30, 0, 0);
                itemConstraints.gridx = 0;
                itemConstraints.gridy = 0;

                JLabel itemName = new JLabel(item.getName());
                itemPanel.add(itemName, itemConstraints);
                itemConstraints.gridx ++;

                if(item instanceof Weapon){
                    JLabel itemProperty = new JLabel("Урон: " + Double.toString(((Weapon)item).getDamage()));
                    itemPanel.add(itemProperty, itemConstraints);
                    itemConstraints.gridx ++;
                }

                if(item instanceof Armor){
                    JLabel itemProperty = new JLabel("Защита: " + Integer.toString(((Armor)item).getProtection()));
                    itemPanel.add(itemProperty, itemConstraints);
                    itemConstraints.gridx ++;
                }

                if(item instanceof Potion){
                    JLabel itemProperty = new JLabel("Мощность: " + Integer.toString(((Potion)item).getEffect().getPower()));
                    itemPanel.add(itemProperty, itemConstraints);
                    itemConstraints.gridx ++;
                }

                JLabel itemCost = new JLabel("Стоимость: " + Integer.toString(price) + " золотых");
                itemPanel.add(itemCost, itemConstraints);
                itemConstraints.gridx ++;
                JButton buy = new JButton("Купить");

                buy.addActionListener(e -> {
                    seller.addMoney(price);
                    seller.removeItem(item);
                    player.reduceMoney(price);
                    player.addItemToInventory(item);
                    drawWindow();
                    player.getFieldWindow().drawAllPlayerWindow();
                });
                itemPanel.add(buy, itemConstraints);

                panel.add(itemPanel, constraints);
                constraints.gridy ++;
            }
        } else {
            for(Item item : player.getInventory()){

                item.countCost();
                int price = (int)(item.getCost()*Math.min(10, Math.pow(1.005, player.getStats().getEloquence() - seller.getStats().getEloquence())));

                JPanel itemPanel = new JPanel(new GridBagLayout());
                GridBagConstraints itemConstraints = new GridBagConstraints();

                itemConstraints.anchor = GridBagConstraints.WEST;
                itemConstraints.insets = new Insets(5, 30, 0, 0);
                itemConstraints.gridx = 0;
                itemConstraints.gridy = 0;

                JLabel itemName = new JLabel(item.getName());
                itemPanel.add(itemName, itemConstraints);
                itemConstraints.gridx ++;

                if(item instanceof Weapon){
                    JLabel itemProperty = new JLabel("Урон: " + Double.toString(((Weapon)item).getDamage()));
                    itemPanel.add(itemProperty, itemConstraints);
                    itemConstraints.gridx ++;
                }

                if(item instanceof Armor){
                    JLabel itemProperty = new JLabel("Защита: " + Integer.toString(((Armor)item).getProtection()));
                    itemPanel.add(itemProperty, itemConstraints);
                    itemConstraints.gridx ++;
                }

                if(item instanceof Potion){
                    JLabel itemProperty = new JLabel("Мощность: " + Integer.toString(((Potion)item).getEffect().getPower()));
                    itemPanel.add(itemProperty, itemConstraints);
                    itemConstraints.gridx ++;
                }

                JLabel itemCost = new JLabel("Стоимость: " + Integer.toString(price) + " золотых");
                itemPanel.add(itemCost, itemConstraints);
                itemConstraints.gridx ++;
                JButton sell = new JButton("Продать");

                sell.addActionListener(e -> {
                    player.addMoney(price);
                    player.removeItem(item);
                    drawWindow();
                    player.getFieldWindow().drawAllPlayerWindow();
                });
                itemPanel.add(sell, itemConstraints);

                panel.add(itemPanel, constraints);
                constraints.gridy ++;
            }
        }

        getContentPane().add(scroll, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
