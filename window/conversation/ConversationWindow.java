package window.conversation;

import creature.LiveCreature;
import creature.Player;
import support.CreatureProperty;
import texture.Texture;
import texture.TextureFactory;
import window.MultiWindow;
import window.Screen;
import window.Switcher;
import window.menu.Menu;
import window.support.component.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class ConversationWindow extends Menu implements Serializable, Switcher {

    private final CardLayout manager = new CardLayout();
    private final LiveCreature opponent;
    private final Console dialog;
    private Player player;
    private MultiWindow multiWindow;
    private JPanel rightPanel;
    private ConversationPanel conversationPanel;
    private ShopPanel shopPanel;
    private TrainShopPanel trainShopPanel;

    public ConversationWindow(LiveCreature opponent) {

        setLayout(new BorderLayout());

        this.opponent = opponent;
        int height = 720;
        int width = 720;
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));

        dialog = new Console();
        dialog.setSpeed(0);
        dialog.setSizeArea(width -30, height -240);

        bindKeys();
        drawWindow();
    }

    public void setPlayer(Player player) {
        this.player = player;
        if (conversationPanel != null) {
            conversationPanel.setPlayer(player);
        }
        if (shopPanel != null) {
            shopPanel.setPlayer(player);
        }
        if (trainShopPanel != null) {
            trainShopPanel.setPlayer(player);
        }
    }

    public void setMultiWindow(MultiWindow multiWindow) {
        this.multiWindow = multiWindow;
    }

    private void drawWindow() {
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        Texture texture = TextureFactory.get((CreatureProperty) opponent.getLastProperty());
        BufferedImage image = texture.getTexture();
        int height = image.getHeight();
        int newHeight = HEIGHT;
        double coefficient = (newHeight * 1.0) / height;
        int newWidth = (int) (image.getWidth() * coefficient);
        Image scaledInstance = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        imagePanel.add(new JLabel(new ImageIcon(scaledInstance)), BorderLayout.CENTER);
        add(imagePanel, BorderLayout.WEST);

        rightPanel = new JPanel();
        rightPanel.setLayout(manager);

        conversationPanel = new ConversationPanel(newWidth, dialog, player, opponent, this);
        shopPanel = new ShopPanel(newWidth, player, opponent, this);
        trainShopPanel = new TrainShopPanel(newWidth, player, opponent, this);

        rightPanel.add(conversationPanel, Screen.CONVERSATION.name());
        rightPanel.add(shopPanel, Screen.SHOP.name());
        rightPanel.add(trainShopPanel, Screen.TRAN_SHOP.name());

        add(rightPanel, BorderLayout.EAST);
    }

    private void close(Screen screen) {
        multiWindow.removeWindow(this);
        multiWindow.switchScreen(screen);
    }

    public void bindKeys() {
        KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape,   "escape");
        getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close(Screen.GAME);
            }
        });
    }

    public void updateButtonList() {
        conversationPanel.updateButtonList();
        switchScreen(Screen.CONVERSATION);
    }

    @Override
    public void switchScreen(Screen screen) {
        conversationPanel.setVisible(false);
        if (screen == Screen.SHOP) {
            shopPanel.setVisible(true);
            conversationPanel.setVisible(false);
            trainShopPanel.setVisible(false);
        } else if (screen == Screen.TRAN_SHOP) {
            trainShopPanel.setVisible(true);
            shopPanel.setVisible(false);
            conversationPanel.setVisible(false);
        } else if (screen == Screen.CONVERSATION) {
            conversationPanel.setVisible(true);
            shopPanel.setVisible(false);
            trainShopPanel.setVisible(false);
        }
        manager.show(rightPanel, screen.name());
        rightPanel.revalidate();
        rightPanel.repaint();
    }
}
