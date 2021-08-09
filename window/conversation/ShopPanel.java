package window.conversation;

import conversation.TablePart;
import creature.LiveCreature;
import creature.Player;
import item.Item;
import item.alchemy.potion.Potion;
import item.armor.Armor;
import item.weapon.Weapon;
import window.Switcher;
import window.player.UnfocusedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ShopPanel extends AbstractShop implements Serializable {

    private String chosenPanel;

    public ShopPanel(int anchorWidth, Player player, LiveCreature seller, Switcher switcher) {
        super(anchorWidth, player, seller, switcher);
        chosenPanel = "buy";
        UnfocusedButton buyButton = new UnfocusedButton("Купить");
        customizeButton(buyButton);
        UnfocusedButton sellButton = new UnfocusedButton("Продать");
        customizeButton(sellButton);

        buyButton.addActionListener((ActionListener & Serializable)e -> {
            chosenPanel = "buy";
            printItems();
        });
        sellButton.addActionListener((ActionListener & Serializable)e -> {
            chosenPanel = "sell";
            printItems();
        });

        Dimension buttonSize = new Dimension((int) (width * 0.45), HEIGHT / 15);
        buyButton.setMinimumSize(buttonSize);
        buyButton.setPreferredSize(buttonSize);
        buyButton.setMaximumSize(buttonSize);
        sellButton.setMinimumSize(buttonSize);
        sellButton.setPreferredSize(buttonSize);
        sellButton.setMaximumSize(buttonSize);

        choose.add(buyButton, BorderLayout.CENTER);
        choose.add(sellButton, BorderLayout.EAST);

        add(choose, BorderLayout.NORTH);
        scroll = new JScrollPane(itemStock);
        Dimension scrollSize = new Dimension(width, HEIGHT * 14 / 15);
        scroll.setMinimumSize(scrollSize);
        scroll.setPreferredSize(scrollSize);
        scroll.setMaximumSize(scrollSize);
        customizeScroll(scroll);

        add(scroll, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void printItems() {
        dtm.clear();
        LiveCreature seller = chosenPanel.equals("buy") ? this.seller: this.player;
        LiveCreature customer = chosenPanel.equals("buy") ? this.player: this.seller;
        Map<Item, Integer> map = getUniqueItemsWithCount(seller);
        map.forEach((item, count) -> {

            int price = (int)(item.getCost() * Math.min(12, Math.pow(1.005, seller.getStats().getEloquence() - player.getStats().getEloquence())));
            String property = "";

            if(item instanceof Weapon weapon) {
                property = "Урон: " + weapon.getDamage();
            }

            if(item instanceof Armor armor) {
                property = "Защита: " + armor.getProtection();
            }

            if(item instanceof Potion potion) {
                property = "Мощность: " + potion.getEffect().getPower();
            }

            UnfocusedButton button = new UnfocusedButton(
                    chosenPanel.equals("buy") ? "Купить" : "Продать"
            );

            button.addActionListener((ActionListener & Serializable)e -> {
                if (chosenPanel.equals("sell") || customer.getMoney() >= price) {
                    seller.addMoney(price);
                    seller.removeItem(item);
                    customer.reduceMoney(price);
                    customer.addItemToInventory(item);
                    printItems();
                }
            });

            button.setBackground(STYLED_COLOR);
            button.setFont(FONT_MEDIUM);

            TablePart tablePart = new TablePart(
                    item.getName(),
                    property,
                    price,
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
}
