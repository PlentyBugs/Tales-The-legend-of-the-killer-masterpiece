package window.conversation;

import conversation.*;
import creature.LiveCreature;
import creature.Player;
import creature.peaceful.Peaceful;
import support.CreatureProperty;
import texture.Texture;
import texture.TextureFactory;
import window.MultiWindow;
import window.Screen;
import window.menu.Menu;
import window.player.UnfocusedButton;
import window.support.component.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.List;

public class ConversationWindow extends Menu implements Serializable {

    private Player player;
    private final LiveCreature opponent;
    private final Console dialog;
    private MultiWindow multiWindow;

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
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMultiWindow(MultiWindow multiWindow) {
        this.multiWindow = multiWindow;
    }

    private void drawWindow() {
        removeAll();
        revalidate();
        repaint();
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

        JPanel conversationPanel = new JPanel();
        conversationPanel.setLayout(new BorderLayout());
        int conversationWidth = WIDTH - newWidth;
        dialog.setSizeArea(conversationWidth, HEIGHT * 2 / 3);
        conversationPanel.add(dialog, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        Box buttons = Box.createVerticalBox();
        Dimension buttonListSize = new Dimension(conversationWidth, HEIGHT / 3);
        buttons.setPreferredSize(buttonListSize);
        buttons.setMaximumSize(buttonListSize);
        buttons.setMinimumSize(buttonListSize);
        scrollPane.setPreferredSize(buttonListSize);

        Dimension buttonSize = new Dimension(conversationWidth, HEIGHT / 20);
        printButtons(buttonSize, buttons, opponent.getConversation().getConversationTree());
        scrollPane.setViewportView(buttons);

//        if(player != null && player.hasAbility(AbilityProperty.STEAL)) {
//            JButton title = new UnfocusedButton("Обокрасть");
//
//            title.setPreferredSize(new Dimension(width, 30));
//            title.setMinimumSize(new Dimension(width, 30));
//            title.setMaximumSize(new Dimension(width, 30));
//            title.addActionListener(e -> {
////                ThiefWindow thiefWindow = new ThiefWindow(opponent, player);
////                close();
//                close(Screen.THEFT);
//            });
//
//            buttons.add(title, constraints);
//        }

        conversationPanel.add(scrollPane, BorderLayout.SOUTH);

        add(conversationPanel, BorderLayout.EAST);
        setVisible(true);
    }

    private void close(Screen screen) {
        multiWindow.removeWindow(this);
        multiWindow.switchScreen(screen);
    }

    public void setIsVisible(boolean b) {
        setVisible(b);
        if(b){
            drawWindow();
        }
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

    private void printButtons(Dimension buttonSize, Box buttons, List<Conversation> conversations) {
        for (Conversation conversation : conversations) {
            if(conversation == null || !conversation.getIsVisible()){
                continue;
            }

            JButton title = new UnfocusedButton(conversation.getTitle());
            customizeButton(title);
            title.setPreferredSize(buttonSize);
            title.setMinimumSize(buttonSize);
            title.setMaximumSize(buttonSize);
            title.addActionListener((ActionListener & Serializable) e -> {
                if (conversation instanceof TrainShop trainShop) {
                    trainShop.setPlayer(player);
                    close(Screen.SHOP);
                    trainShop.run();
                } else if (conversation instanceof Shop shop) {
                    shop.setPlayer(player);
                    close(Screen.SHOP);
                    shop.run();
                } else if (conversation instanceof DialogConversation dialogConversation) {
                    dialogConversation.setPlayerName(player.getName());
                    dialogConversation.setOpponentName(opponent.getName());
                    if(dialogConversation instanceof QuestDialogConversation questDialogConversation){
                        questDialogConversation.setPlayer(player);
                        questDialogConversation.setPeaceful((Peaceful) opponent);
                        questDialogConversation.run();
                    }
                    dialogConversation.writeToConsole(dialog);

                    opponent.getConversation().getConversationTree().remove(dialogConversation);
                    buttons.remove(title);
                    opponent.getConversation().getConversationTree().addAll(conversation.getConversationTree());
                    printButtons(buttonSize, buttons, conversation.getConversationTree());
                }
            });

            buttons.add(title);
        }
        buttons.revalidate();
        buttons.repaint();
    }
}
