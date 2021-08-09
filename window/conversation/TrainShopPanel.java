package window.conversation;

import abilities.Ability;
import conversation.CatalogItem;
import conversation.TablePart;
import creature.LiveCreature;
import creature.Player;
import support.Sellable;
import window.Switcher;
import window.player.UnfocusedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class TrainShopPanel extends AbstractShop implements Serializable {

    public TrainShopPanel(int anchorWidth, Player player, LiveCreature seller, Switcher switcher){
        super(anchorWidth, player, seller, switcher);
        scroll = new JScrollPane(itemStock);
        Dimension scrollSize = new Dimension(width, HEIGHT * 14 / 15);
        scroll.setMinimumSize(scrollSize);
        scroll.setPreferredSize(scrollSize);
        scroll.setMaximumSize(scrollSize);
        customizeScroll(scroll);

        add(choose, BorderLayout.NORTH);
        add(scroll, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void printItems() {
        dtm.clear();

        for(CatalogItem cat : seller.getAbilitiesForSale().getStock()) {
            Sellable sellable = cat.sellable();
            int price = cat.price();
            int count = cat.count();
            if (count == 0) {
                continue;
            }

            UnfocusedButton buy = new UnfocusedButton("Выучить");
            buy.addActionListener((ActionListener & Serializable)  e -> {
                if (count > 0 && player.getMoney() >= price && sellable instanceof Ability ability && !player.hasAbility(ability.getLastProperty())) {
                    player.reduceMoney(price);
                    cat.setCount(count - 1);
                    player.addAbility(ability);
                    printItems();
                }
            });

            buy.setBackground(STYLED_COLOR);
            buy.setFont(FONT_MEDIUM);

            TablePart tablePart = new TablePart(
                    sellable.getName(),
                    sellable.getItemProperty(),
                    price,
                    count,
                    buy
            );

            dtm.addRow(tablePart);
        }
        revalidate();
        repaint();
        itemStock.revalidate();
        itemStock.repaint();
    }
}