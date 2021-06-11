package Windows.ConversationWindows;

import Creatures.LiveCreature;
import Creatures.Player;
import Items.Alchemy.Potions.Potion;
import Items.Armors.Armor;
import Items.Item;
import Items.Weapons.Weapon;
import Windows.PlayerWindows.UnfocusedButton;
import Windows.WindowInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class ShopWindow extends JFrame implements Serializable {

    private final LiveCreature seller;
    private final int height;
    private final int width;
    private Player player;
    private JPanel panel = new JPanel(new GridBagLayout());
    private JScrollPane scroll = new JScrollPane(panel);
    private String chosenPanel;

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
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;

        JPanel choose = new JPanel(new BorderLayout());
        JButton buyButton = new UnfocusedButton("Купить");
        JButton sellButton = new UnfocusedButton("Продать");
        buyButton.addActionListener((ActionListener & Serializable)e -> {
            chosenPanel = "buy";
            drawWindow();
        });
        sellButton.addActionListener((ActionListener & Serializable)e -> {
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

        WindowInterface windowInterface = player.getWindowInterface();
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

                if(item instanceof Weapon weapon) {
                    JLabel itemProperty = new JLabel("Урон: " + weapon.getDamage());
                    itemPanel.add(itemProperty, itemConstraints);
                    itemConstraints.gridx ++;
                }

                if(item instanceof Armor armor) {
                    JLabel itemProperty = new JLabel("Защита: " + armor.getProtection());
                    itemPanel.add(itemProperty, itemConstraints);
                    itemConstraints.gridx ++;
                }

                if(item instanceof Potion potion) {
                    JLabel itemProperty = new JLabel("Мощность: " + potion.getEffect().getPower());
                    itemPanel.add(itemProperty, itemConstraints);
                    itemConstraints.gridx ++;
                }

                JLabel itemCost = new JLabel("Стоимость: " + price + " золотых");
                itemPanel.add(itemCost, itemConstraints);
                itemConstraints.gridx ++;
                JButton buy = new UnfocusedButton("Купить");

                buy.addActionListener((ActionListener & Serializable)e -> {
                    if (player.getMoney() >= price) {
                        seller.addMoney(price);
                        seller.removeItem(item);
                        player.reduceMoney(price);
                        player.addItemToInventory(item);
                        drawWindow();
                        windowInterface.drawAllPlayerWindow(player, windowInterface);
                    }
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

                if(item instanceof Weapon weapon){
                    JLabel itemProperty = new JLabel("Урон: " + weapon.getDamage());
                    itemPanel.add(itemProperty, itemConstraints);
                    itemConstraints.gridx ++;
                }

                if(item instanceof Armor armor){
                    JLabel itemProperty = new JLabel("Защита: " + armor.getProtection());
                    itemPanel.add(itemProperty, itemConstraints);
                    itemConstraints.gridx ++;
                }

                if(item instanceof Potion potion) {
                    JLabel itemProperty = new JLabel("Мощность: " + potion.getEffect().getPower());
                    itemPanel.add(itemProperty, itemConstraints);
                    itemConstraints.gridx ++;
                }

                JLabel itemCost = new JLabel("Стоимость: " + price + " золотых");
                itemPanel.add(itemCost, itemConstraints);
                itemConstraints.gridx ++;
                JButton sell = new UnfocusedButton("Продать");

                sell.addActionListener((ActionListener & Serializable) e -> {
                    player.addMoney(price);
                    player.removeItem(item);
                    drawWindow();
                    windowInterface.drawAllPlayerWindow(player, windowInterface);
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
