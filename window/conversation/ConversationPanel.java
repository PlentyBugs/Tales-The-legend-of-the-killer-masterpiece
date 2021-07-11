package window.conversation;

import conversation.*;
import creature.LiveCreature;
import creature.Player;
import creature.peaceful.Peaceful;
import window.Screen;
import window.Switcher;
import window.menu.AbstractMenu;
import window.player.UnfocusedButton;
import window.support.component.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.List;

public class ConversationPanel extends AbstractMenu {
    private final Dimension buttonSize;
    private final Box buttons;
    private final Console dialog;
    private final LiveCreature opponent;
    private final Switcher switcher;
    private Player player;

    public ConversationPanel(int anchorWidth, Console dialog, Player player, LiveCreature opponent, Switcher switcher) {
        this.player = player;
        this.dialog = dialog;
        this.opponent = opponent;
        this.switcher = switcher;

        setLayout(new BorderLayout());
        int conversationWidth = WIDTH - anchorWidth;
        dialog.setSizeArea(conversationWidth, HEIGHT * 2 / 3);
        add(dialog, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        buttons = Box.createVerticalBox();
        Dimension buttonListSize = new Dimension(conversationWidth, HEIGHT / 3);
        buttons.setPreferredSize(buttonListSize);
        buttons.setMaximumSize(buttonListSize);
        buttons.setMinimumSize(buttonListSize);
        buttons.setOpaque(true);
        buttons.setBackground(STYLED_COLOR_LIGHT);
        setOpaque(true);
        setBackground(STYLED_COLOR_LIGHT);
        scrollPane.setPreferredSize(buttonListSize);
        customizeScroll(scrollPane);

        buttonSize = new Dimension(conversationWidth, HEIGHT / 20);
        updateButtonList();
        scrollPane.setViewportView(buttons);

/*        if(player != null && player.hasAbility(AbilityProperty.STEAL)) {
            JButton title = new UnfocusedButton("Обокрасть");

            title.setPreferredSize(new Dimension(width, 30));
            title.setMinimumSize(new Dimension(width, 30));
            title.setMaximumSize(new Dimension(width, 30));
            title.addActionListener(e -> {
//                ThiefWindow thiefWindow = new ThiefWindow(opponent, player);
//                close();
                close(Screen.THEFT);
            });

            buttons.add(title, constraints);
        }
*/

        add(scrollPane, BorderLayout.SOUTH);
    }

    private void printButtons(List<Conversation> conversations) {
        for (Conversation conversation : conversations) {
            if(conversation == null || !conversation.getIsVisible()){
                continue;
            }

            UnfocusedButton title = new UnfocusedButton(conversation.getTitle());
            customizeButton(title);
            title.setPreferredSize(buttonSize);
            title.setMinimumSize(buttonSize);
            title.setMaximumSize(buttonSize);
            title.addActionListener((ActionListener & Serializable) e -> {
                if (conversation instanceof TrainShopConversation) {
                    switcher.switchScreen(Screen.TRAN_SHOP);
                } else if (conversation instanceof ShopConversation) {
                    switcher.switchScreen(Screen.SHOP);
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
                    printButtons(conversation.getConversationTree());
                }
            });

            buttons.add(title);
        }
        buttons.revalidate();
        buttons.repaint();
    }

    public void updateButtonList() {
        buttons.removeAll();
        printButtons(opponent.getConversation().getConversationTree());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
