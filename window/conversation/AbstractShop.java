package window.conversation;

import creature.LiveCreature;
import creature.Player;
import window.Screen;
import window.Switcher;
import window.player.UnfocusedButton;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractShop extends AbstractInventory {
    protected LiveCreature seller;
    protected Switcher switcher;
    protected final JPanel choose = new JPanel(new BorderLayout());

    public AbstractShop(
                int anchorWidth,
                Player player,
                LiveCreature seller,
                Switcher switcher,
                String[] COLUMN_NAMES,
                Class<?>[] COLUMN_TYPES
            ) {
        super(anchorWidth, player, COLUMN_NAMES, COLUMN_TYPES);
        this.seller = seller;
        this.switcher = switcher;

        if (this instanceof ShopPanel || this instanceof TrainShopPanel) {
            UnfocusedButton backButton = new UnfocusedButton("←");
            customizeButton(backButton);
            backButton.addActionListener((e) -> switcher.switchScreen(Screen.CONVERSATION));
            Dimension backButtonSize = new Dimension((int) (width * 0.1), HEIGHT / 15);
            backButton.setMinimumSize(backButtonSize);
            backButton.setPreferredSize(backButtonSize);
            backButton.setMaximumSize(backButtonSize);
            choose.add(backButton, BorderLayout.WEST);
        }
    }

    public AbstractShop(int anchorWidth, Player player, LiveCreature seller, Switcher switcher) {
        this(
                anchorWidth,
                player,
                seller,
                switcher,
                new String[] {"Staff", "Property", "Price", "Count", ""},
                new Class<?>[] {String.class, String.class, String.class, String.class,  JButton.class}
        );
    }

    public void setPlayer(Player player) {
        this.player = player;
        printItems();
    }
}
